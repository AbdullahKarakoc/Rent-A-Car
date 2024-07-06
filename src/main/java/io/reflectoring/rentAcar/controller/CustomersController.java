package io.reflectoring.rentAcar.controller;

import io.reflectoring.rentAcar.domain.request.CustomersRequestDto;
import io.reflectoring.rentAcar.domain.response.CustomersResponseDto;
import io.reflectoring.rentAcar.service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/V1/customers")
public class CustomersController {
    @Autowired
    private CustomersService customerService;

    @GetMapping
    public List<CustomersResponseDto> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public CustomersResponseDto getCustomerById(@PathVariable UUID id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping
    public CustomersResponseDto saveCustomer(@RequestBody CustomersRequestDto customerRequestDto) {
        return customerService.saveCustomer(customerRequestDto);
    }

    @PutMapping("/{id}")
    public CustomersResponseDto updateCustomer(@PathVariable UUID id, @RequestBody CustomersRequestDto customerRequestDto) {
        return customerService.updateCustomer(id, customerRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable UUID id) {
        customerService.deleteCustomer(id);
    }
}
