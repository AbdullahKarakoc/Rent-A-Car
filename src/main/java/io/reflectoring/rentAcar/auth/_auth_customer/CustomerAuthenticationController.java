package io.reflectoring.rentAcar.auth._auth_customer;

import io.reflectoring.rentAcar.domain.request.CustomersRequestDto;
import io.reflectoring.rentAcar.domain.response.CustomersResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("auth/customer")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class CustomerAuthenticationController {

    private final CustomerAuthenticationService service;

    @Autowired
    private CustomerService customerService;




    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> register(
            @RequestBody @Valid CustomerRegistrationRequest request
    ) throws MessagingException {
        service.register(request);
        return ResponseEntity.accepted().build();
    }


    @PostMapping("/authenticate")
    public ResponseEntity<CustomerAuthenticationResponse> authenticate(
            @RequestBody @Valid CustomerAuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }


    @GetMapping("/activate-account")
    public void confirm(
            @RequestParam String token
    ) throws MessagingException {
        service.activateAccount(token);
    }





    @Operation(
            summary = "Get all customers",
            description = "An endpoint used to list all customers.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized access")
            }
    )
    @GetMapping
    public ResponseEntity<List<CustomersResponseDto>> getAllCustomers() {
        List<CustomersResponseDto> allCustomers = customerService.getAllCustomers();
        return ResponseEntity.ok(allCustomers);
    }

    @Operation(
            summary = "Get Customer by ID",
            description = "An endpoint used to get details of a Customer by their ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved Customer"),
                    @ApiResponse(responseCode = "404", description = "Customer not found"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized access")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<CustomersResponseDto> getCustomerById(@PathVariable UUID id) {
        CustomersResponseDto customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }



    @Operation(
            summary = "Update Customer",
            description = "An endpoint used to update an existing Customer by their ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Customer successfully updated"),
                    @ApiResponse(responseCode = "404", description = "Customer not found"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized access")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<CustomersResponseDto> updateCustomer(@Valid @PathVariable UUID id, @RequestBody CustomersRequestDto customerRequestDto) {
        CustomersResponseDto updatedCustomer = customerService.updateCustomer(id, customerRequestDto);
        return ResponseEntity.ok(updatedCustomer);
    }

    @Operation(
            summary = "Delete Customer",
            description = "An endpoint used to delete an existing Customer by their ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Customer successfully deleted"),
                    @ApiResponse(responseCode = "404", description = "Customer not found"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized access")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable UUID id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }




}