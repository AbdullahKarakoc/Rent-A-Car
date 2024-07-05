package io.reflectoring.rentAcar.service;


import io.reflectoring.rentAcar.domain.model.Payments;
import io.reflectoring.rentAcar.domain.model.Rentals;
import io.reflectoring.rentAcar.domain.request.PaymentsRequestDto;
import io.reflectoring.rentAcar.domain.response.PaymentsResponseDto;
import io.reflectoring.rentAcar.exception.DataNotFoundException;
import io.reflectoring.rentAcar.repository.PaymentsRepository;
import io.reflectoring.rentAcar.repository.RentalsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PaymentsService {
    @Autowired
    private PaymentsRepository paymentRepository;

    @Autowired
    private RentalsRepository rentalRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<PaymentsResponseDto> getAllPayments() {
        List<Payments> payments = paymentRepository.findAll();
        return payments.stream()
                .map(payment -> modelMapper.map(payment, PaymentsResponseDto.class))
                .collect(Collectors.toList());
    }

    public PaymentsResponseDto getPaymentById(UUID id) {
        Payments payment = paymentRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Payment not found"));
        return modelMapper.map(payment, PaymentsResponseDto.class);
    }

    public PaymentsResponseDto savePayment(PaymentsRequestDto paymentRequestDto) {
        Rentals rental = rentalRepository.findById(paymentRequestDto.getRentalUUID())
                .orElseThrow(() -> new DataNotFoundException("Rental not found"));

        Payments payment = modelMapper.map(paymentRequestDto, Payments.class);
        payment.setRental(rental);
        payment = paymentRepository.save(payment);
        return modelMapper.map(payment, PaymentsResponseDto.class);
    }

    public PaymentsResponseDto updatePayment(UUID id, PaymentsRequestDto paymentRequestDto) {
        Payments existingPayment = paymentRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Payment not found"));

        Rentals rental = rentalRepository.findById(paymentRequestDto.getRentalUUID())
                .orElseThrow(() -> new DataNotFoundException("Rental not found"));

        modelMapper.map(paymentRequestDto, existingPayment);
        existingPayment.setRental(rental);
        Payments updatedPayment = paymentRepository.save(existingPayment);
        return modelMapper.map(updatedPayment, PaymentsResponseDto.class);
    }

    public void deletePayment(UUID id) {
        Payments payment = paymentRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Payment not found"));
        paymentRepository.delete(payment);
    }
}
