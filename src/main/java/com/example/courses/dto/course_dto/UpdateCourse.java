package com.example.courses.dto.course_dto;

import com.example.courses.entity_status.CourseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCourse {
    private String name;
    private Integer hours;
    private BigDecimal price;
    private CourseStatus status;
}
