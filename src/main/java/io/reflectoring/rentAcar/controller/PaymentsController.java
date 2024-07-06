package io.reflectoring.rentAcar.controller;


import io.reflectoring.rentAcar.domain.request.PaymentsRequestDto;
import io.reflectoring.rentAcar.domain.response.PaymentsResponseDto;
import io.reflectoring.rentAcar.service.PaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/V1/payments")
public class PaymentsController {
    @Autowired
    private PaymentsService paymentService;

    @GetMapping
    public List<PaymentsResponseDto> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/{id}")
    public PaymentsResponseDto getPaymentById(@PathVariable UUID id) {
        return paymentService.getPaymentById(id);
    }

    @PostMapping
    public PaymentsResponseDto savePayment(@RequestBody PaymentsRequestDto paymentRequestDto) {
        return paymentService.savePayment(paymentRequestDto);
    }

    @PutMapping("/{id}")
    public PaymentsResponseDto updatePayment(@PathVariable UUID id, @RequestBody PaymentsRequestDto paymentRequestDto) {
        return paymentService.updatePayment(id, paymentRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deletePayment(@PathVariable UUID id) {
        paymentService.deletePayment(id);
    }
}
