package com.example.courses.dto.payment_dto;

import com.example.courses.entity_status.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PaymentResponse(Long id, Long studentId, Long courseId,
                              BigDecimal amount, LocalDate paymentDate, PaymentStatus status) {
}
