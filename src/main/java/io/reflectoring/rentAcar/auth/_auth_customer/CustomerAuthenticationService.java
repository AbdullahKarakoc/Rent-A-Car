package io.reflectoring.rentAcar.auth._auth_customer;

import io.reflectoring.rentAcar.auth._auth_customer.customer.Customer;
import io.reflectoring.rentAcar.auth._auth_customer.customer.CustomerRepository;
import io.reflectoring.rentAcar.auth.email.EmailService;
import io.reflectoring.rentAcar.auth.email.EmailTemplateName;
import io.reflectoring.rentAcar.auth.handler.ActivationTokenExpiredException;
import io.reflectoring.rentAcar.auth.handler.InvalidTokenException;
import io.reflectoring.rentAcar.auth.handler.UserAlreadyExistsException;
import io.reflectoring.rentAcar.auth.role.RoleRepository;
import io.reflectoring.rentAcar.auth.security.JwtService;
import io.reflectoring.rentAcar.auth.user.*;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerAuthenticationService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final EmailService emailService;
    private final TokenRepository tokenRepository;

    @Value("${application.mailing.frontend.activation-url}")
    private String activationUrl;

    public void register(CustomerRegistrationRequest request) throws MessagingException {
        // Check if the user already exists
        if (customerRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User with email " + request.getEmail() + " already exists");
        }

        // Get the USER role
        var userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new IllegalStateException("ROLE USER was not initiated"));

        // Create new customer
        var customer = Customer.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountLocked(false)
                .enabled(false)
                .roles(List.of(userRole))
                .build();

        // Save customer and send validation email
        customerRepository.save(customer);
        sendValidationEmail(customer);
    }

    public CustomerAuthenticationResponse authenticate(CustomerAuthenticationRequest request) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var claims = new HashMap<String, Object>();
        var customer = ((Customer) auth.getPrincipal());
        claims.put("fullName", customer.getFullName());

        var jwtToken = jwtService.generateToken(claims, customer);
        return CustomerAuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public void activateAccount(String token) throws MessagingException {
        Token savedToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new InvalidTokenException("Invalid token"));
        if (LocalDateTime.now().isAfter(savedToken.getExpiresAt())) {
            sendValidationEmail(savedToken.getCustomer());
            throw new ActivationTokenExpiredException("Activation token has expired. A new token has been sent to the same email address");
        }

        var customer = customerRepository.findById(savedToken.getCustomer().getCustomerUUID())
                .orElseThrow(() -> new UsernameNotFoundException("Customer not found"));
        customer.setEnabled(true);
        customerRepository.save(customer);

        savedToken.setValidatedAt(LocalDateTime.now());
        tokenRepository.save(savedToken);
    }

    private String generateAndSaveActivationToken(Customer customer) {
        // Token oluştur
        String generatedToken = generateActivationCode(6);
        var token = Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .customer(customer) // customer alanı dolduruluyor
                .build();
        tokenRepository.save(token);

        return generatedToken;
    }

    private void sendValidationEmail(Customer customer) throws MessagingException {
        var newToken = generateAndSaveActivationToken(customer);

        emailService.sendEmail(
                customer.getEmail(),
                customer.getFullName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationUrl,
                newToken,
                "Account activation"
        );
    }

    private String generateActivationCode(int length) {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();

        SecureRandom secureRandom = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));
        }

        return codeBuilder.toString();
    }
}