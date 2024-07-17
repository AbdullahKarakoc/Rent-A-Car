package io.reflectoring.rentAcar.controller;

import io.reflectoring.rentAcar.domain.request.CustomersRequestDto;
import io.reflectoring.rentAcar.domain.response.CustomersResponseDto;
import io.reflectoring.rentAcar.service.CustomersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/customers")
@Tag(name = "Customers-Controller", description = "Controller managing operations related to customers")
@SecurityRequirement(name = "bearerAuth")
public class CustomersController {
    @Autowired
    private CustomersService customerService;

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
            summary = "Get customer by ID",
            description = "An endpoint used to get details of a customer by their ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved customer"),
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
            summary = "Save a new customer",
            description = "An endpoint used to save a new customer.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Customer successfully saved"),
                    @ApiResponse(responseCode = "400", description = "Invalid input"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized access")
            }
    )
    @PostMapping
    public ResponseEntity<CustomersResponseDto> saveCustomer(@Valid @RequestBody CustomersRequestDto customerRequestDto) {
        CustomersResponseDto savedCustomer = customerService.saveCustomer(customerRequestDto);
        return ResponseEntity.ok(savedCustomer);
    }

    @Operation(
            summary = "Update customer",
            description = "An endpoint used to update an existing customer by their ID.",
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
            summary = "Delete customer",
            description = "An endpoint used to delete an existing customer by their ID.",
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
