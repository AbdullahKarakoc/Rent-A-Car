package io.reflectoring.rentAcar.service;


import io.reflectoring.rentAcar.domain.model.Payments;
import io.reflectoring.rentAcar.domain.model.Rentals;
import io.reflectoring.rentAcar.domain.request.PaymentsRequestDto;
import io.reflectoring.rentAcar.domain.response.PaymentsResponseDto;
import io.reflectoring.rentAcar.exception.DataNotFoundException;
import io.reflectoring.rentAcar.repository.PaymentsRepository;
import io.reflectoring.rentAcar.repository.RentalsRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentsService {

    private final PaymentsRepository paymentRepository;
    private final ModelMapper modelMapper;

    public List<PaymentsResponseDto> getAllPayments() {
        List<Payments> payments = paymentRepository.findAll();
        return payments.stream()
                .map(payment -> modelMapper.map(payment, PaymentsResponseDto.class))
                .collect(Collectors.toList());
    }

    public PaymentsResponseDto getPaymentById(UUID id) {
        Payments payment = findById(id);
        return modelMapper.map(payment, PaymentsResponseDto.class);
    }

    public PaymentsResponseDto savePayment(PaymentsRequestDto paymentRequestDto) {
        Payments payment = modelMapper.map(paymentRequestDto, Payments.class);
        Payments savedPayment = save(payment);
        return modelMapper.map(savedPayment, PaymentsResponseDto.class);
    }

    public PaymentsResponseDto updatePayment(UUID id, PaymentsRequestDto paymentRequestDto) {
        Payments existingPayment = findById(id);
        modelMapper.map(paymentRequestDto, existingPayment);
        Payments updatedPayment = save(existingPayment);
        return modelMapper.map(updatedPayment, PaymentsResponseDto.class);
    }

    public void deletePayment(UUID id) {
        Payments payment = findById(id);
        paymentRepository.delete(payment);
    }

    public Payments save(Payments payment) {
        return paymentRepository.saveAndFlush(payment);
    }

    public Payments findById(UUID id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Payment not found"));
    }
}
