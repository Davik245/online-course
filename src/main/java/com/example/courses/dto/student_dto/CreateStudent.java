package com.example.courses.dto.student_dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateStudent(String firstName, String middleName, String lastName,
                            String phoneNumber, String email, LocalDate registrationDate) {
}
