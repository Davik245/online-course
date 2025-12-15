package com.example.courses.dto.course_dto;

import com.example.courses.entity_status.CourseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseFilter {
    private String name;
    private CourseStatus status;
    private BigDecimal priceFrom;
    private BigDecimal priceTo;
    private LocalDate startDateFrom;
    private LocalDate startDateTo;
    private Long teacherId;
}
