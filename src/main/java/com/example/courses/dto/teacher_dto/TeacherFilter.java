package com.example.courses.dto.teacher_dto;

public record TeacherFilter(
     String firstName,
     String lastName,
     String middleName,
     String specialization,
     String email,
     String phoneNumber){}