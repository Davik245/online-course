package com.example.courses.dto.student_dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStudent {
    private String firstName;
    private String middleName;
    private String lastName;
    private String phoneNumber;
    private String email;
}
