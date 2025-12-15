package com.example.courses.dto.payment_dto;

import com.example.courses.entity_status.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePayment {
    private BigDecimal amount;
    private LocalDate paymentDate;
    private PaymentStatus status;
}
