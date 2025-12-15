package com.example.courses.dto.teacher_dto;

import java.time.LocalDate;

public record TeacherResponse(Long id, String firstName, String middleName, String lastName, String specialization,
                              String phoneNumber, String email) {
}
