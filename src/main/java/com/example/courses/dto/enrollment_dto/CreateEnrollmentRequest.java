package com.example.courses.dto.enrollment_dto;

import com.example.courses.entity_status.EnrollmentStatus;

import java.time.LocalDate;

public record CreateEnrollmentRequest(Long studentId, Long courseId, LocalDate enrollDate,
                                      LocalDate endDate, EnrollmentStatus status) {
}
