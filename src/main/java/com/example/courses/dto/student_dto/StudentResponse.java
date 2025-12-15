package com.example.courses.dto.student_dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record StudentResponse(Long id, String firstName, String middleName, String lastName,
                              String phoneNumber, BigDecimal amount, LocalDate registrationDate) {
}
