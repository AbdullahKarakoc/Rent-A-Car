package io.reflectoring.rentAcar.auth._auth_customer;

import io.reflectoring.rentAcar.auth._auth_customer.customer.Customer;
import io.reflectoring.rentAcar.auth._auth_customer.customer.CustomerRepository;
import io.reflectoring.rentAcar.auth._auth_customer.customer.CustomersRequestDto;
import io.reflectoring.rentAcar.auth._auth_customer.customer.CustomersResponseDto;
import io.reflectoring.rentAcar.exception.DataNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<CustomersResponseDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customer -> modelMapper.map(customer, CustomersResponseDto.class))
                .collect(Collectors.toList());
    }

    public CustomersResponseDto getCustomerById(UUID id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Customer not found"));
        return modelMapper.map(customer, CustomersResponseDto.class);
    }



    public CustomersResponseDto updateCustomer(UUID id, CustomersRequestDto customerRequestDto) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Customer not found"));

        modelMapper.map(customerRequestDto, existingCustomer);
        Customer updatedCustomer = customerRepository.save(existingCustomer);
        return modelMapper.map(updatedCustomer, CustomersResponseDto.class);
    }

    public void deleteCustomer(UUID id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Customer not found"));
        customerRepository.delete(customer);
    }
}

