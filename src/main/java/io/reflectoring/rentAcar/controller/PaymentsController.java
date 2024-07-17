package io.reflectoring.rentAcar.controller;


import io.reflectoring.rentAcar.domain.request.PaymentsRequestDto;
import io.reflectoring.rentAcar.domain.response.PaymentsResponseDto;
import io.reflectoring.rentAcar.service.PaymentsService;
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
@RequestMapping("/payments")
@Tag(name = "Payments-Controller", description = "Controller managing operations related to payments")
@SecurityRequirement(name = "bearerAuth")
public class PaymentsController {
    @Autowired
    private PaymentsService paymentService;

    @Operation(
            summary = "Get all payments",
            description = "An endpoint used to list all payments.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized access")
            }
    )
    @GetMapping
    public ResponseEntity<List<PaymentsResponseDto>> getAllPayments() {
        List<PaymentsResponseDto> allPayments = paymentService.getAllPayments();
        return ResponseEntity.ok(allPayments);
    }

    @Operation(
            summary = "Get payment by ID",
            description = "An endpoint used to get details of a payment by its ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved payment"),
                    @ApiResponse(responseCode = "404", description = "Payment not found"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized access")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<PaymentsResponseDto> getPaymentById(@PathVariable UUID id) {
        PaymentsResponseDto payment = paymentService.getPaymentById(id);
        return ResponseEntity.ok(payment);
    }

    @Operation(
            summary = "Save a new payment",
            description = "An endpoint used to save a new payment.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Payment successfully saved"),
                    @ApiResponse(responseCode = "400", description = "Invalid input"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized access")
            }
    )
    @PostMapping
    public ResponseEntity<PaymentsResponseDto> savePayment(@Valid @RequestBody PaymentsRequestDto paymentRequestDto) {
        PaymentsResponseDto savedPayment = paymentService.savePayment(paymentRequestDto);
        return ResponseEntity.ok(savedPayment);
    }

    @Operation(
            summary = "Update payment",
            description = "An endpoint used to update an existing payment by its ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Payment successfully updated"),
                    @ApiResponse(responseCode = "404", description = "Payment not found"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized access")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<PaymentsResponseDto> updatePayment(@Valid @PathVariable UUID id, @RequestBody PaymentsRequestDto paymentRequestDto) {
        PaymentsResponseDto updatedPayment = paymentService.updatePayment(id, paymentRequestDto);
        return ResponseEntity.ok(updatedPayment);
    }

    @Operation(
            summary = "Delete payment",
            description = "An endpoint used to delete an existing payment by its ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Payment successfully deleted"),
                    @ApiResponse(responseCode = "404", description = "Payment not found"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized access")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable UUID id) {
        paymentService.deletePayment(id);
        return ResponseEntity.ok().build();
    }
}
