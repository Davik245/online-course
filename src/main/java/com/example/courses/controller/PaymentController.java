package com.example.courses.controller;

import com.example.courses.dto.enrollment_dto.EnrollmentResponse;
import com.example.courses.dto.payment_dto.CreatePaymentRequest;
import com.example.courses.dto.payment_dto.PaymentFilter;
import com.example.courses.dto.payment_dto.PaymentResponse;
import com.example.courses.dto.payment_dto.UpdatePayment;
import com.example.courses.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        paymentService.delete(id);
    }

    @PostMapping
    public PaymentResponse create(@Valid @RequestBody CreatePaymentRequest request) {
        return paymentService.create(request);
    }

    @GetMapping("/{id}")
    public PaymentResponse get(@PathVariable Long id) {
        return paymentService.get(id);
    }

    @GetMapping
    public Page<PaymentResponse> get(Pageable pageable, @ModelAttribute PaymentFilter filter) {
        return paymentService.findAll(filter, pageable);
    }

    @PutMapping("/{id}")
    public PaymentResponse update(@PathVariable Long id, @Valid @RequestBody UpdatePayment dto){
        return paymentService.update(id, dto);
    }
}
