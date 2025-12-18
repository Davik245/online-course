package com.example.courses.dto.payment_dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PaymentFilter(Long studentId, Long courseId, BigDecimal amount,
                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                            LocalDate paymentDate) {
}
