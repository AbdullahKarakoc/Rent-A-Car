package io.reflectoring.rentAcar.service;

import io.reflectoring.rentAcar.domain.model.Customers;
import io.reflectoring.rentAcar.domain.request.CustomersRequestDto;
import io.reflectoring.rentAcar.domain.response.CustomersResponseDto;
import io.reflectoring.rentAcar.repository.CustomersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomersService {
    @Autowired
    private CustomersRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<CustomersResponseDto> getAllCustomers() {
        List<Customers> customers = customerRepository.findAll();
        return customers.stream()
                .map(customer -> modelMapper.map(customer, CustomersResponseDto.class))
                .collect(Collectors.toList());
    }

    public CustomersResponseDto getCustomerById(UUID id) {
        Customers customer = customerRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Customer not found"));
        return modelMapper.map(customer, CustomersResponseDto.class);
    }

    public CustomersResponseDto saveCustomer(CustomersRequestDto customerRequestDto) {
        Customers customer = modelMapper.map(customerRequestDto, Customers.class);
        customer = customerRepository.save(customer);
        return modelMapper.map(customer, CustomersResponseDto.class);
    }

    public CustomersResponseDto updateCustomer(UUID id, CustomersRequestDto customerRequestDto) {
        Customers existingCustomer = customerRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Customer not found"));
        modelMapper.map(customerRequestDto, existingCustomer);
        Customers updatedCustomer = customerRepository.save(existingCustomer);
        return modelMapper.map(updatedCustomer, CustomersResponseDto.class);
    }

    public void deleteCustomer(UUID id) {
        Customers customer = customerRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Customer not found"));
        customerRepository.delete(customer);
    }
}
}
