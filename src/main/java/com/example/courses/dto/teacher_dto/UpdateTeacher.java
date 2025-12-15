package com.example.courses.dto.teacher_dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTeacher {
    private String firstName;
    private String middleName;
    private String lastName;
    private String specialization;
    private String phoneNumber;
    private String email;
}
