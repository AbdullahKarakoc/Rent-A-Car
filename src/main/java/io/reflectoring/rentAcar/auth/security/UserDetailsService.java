package io.reflectoring.rentAcar.auth.security;

import io.reflectoring.rentAcar.auth._auth_customer.customer.CustomerRepository;
import io.reflectoring.rentAcar.auth.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        UserDetails userDetails = userRepository.findByEmail(userEmail).orElse(null);
        if (userDetails == null) {
            userDetails = customerRepository.findByEmail(userEmail).orElse(null);
        }
        if (userDetails == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return userDetails;
    }
}

