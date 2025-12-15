package com.example.courses.dto.enrollment_dto;

import com.example.courses.entity_status.EnrollmentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEnrollment {
    private EnrollmentStatus status;
}
