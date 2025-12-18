package com.example.courses.controller;

import com.example.courses.dto.enrollment_dto.EnrollmentResponse;
import com.example.courses.dto.payment_dto.CreatePaymentRequest;
import com.example.courses.dto.payment_dto.PaymentFilter;
import com.example.courses.dto.payment_dto.PaymentResponse;
import com.example.courses.dto.payment_dto.UpdatePayment;
import com.example.courses.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@Tag(name = "Оплата", description = "Просмотр финансов")
public class PaymentController {
    private final PaymentService paymentService;

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление оплаты по ID")
    public void delete(@PathVariable Long id) {
        paymentService.delete(id);
    }

    @PostMapping
    @Operation(summary = "Запись оплаты")
    public PaymentResponse create(@Valid @RequestBody CreatePaymentRequest request) {
        return paymentService.create(request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение оплаты по ID")
    public PaymentResponse get(@PathVariable Long id) {
        return paymentService.get(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка оплат")
    public Page<PaymentResponse> getAll(Pageable pageable, @ModelAttribute PaymentFilter filter) {
        return paymentService.findAll(filter, pageable);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновление оплаты по ID", description = "Можно обновить некоторые данные")
    public PaymentResponse update(@PathVariable Long id, @Valid @RequestBody UpdatePayment dto){
        return paymentService.update(id, dto);
    }
}
