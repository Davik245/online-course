package com.example.courses.dto.enrollment_dto;

import com.example.courses.entity_status.EnrollmentStatus;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record EnrollFilter(Long studentId, Long courseId,
                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                           LocalDate enrollDate,
                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                           LocalDate endDate,
                           EnrollmentStatus status) {
}
