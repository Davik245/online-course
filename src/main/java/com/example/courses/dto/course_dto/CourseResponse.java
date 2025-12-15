package com.example.courses.dto.course_dto;

import com.example.courses.entity_status.CourseStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CourseResponse(Long id, String name, Integer hours, BigDecimal price,
                             CourseStatus status, LocalDate startDate, LocalDate endDate,
                             Long teacherId) {
}
