package com.example.courses.entity_status;

import lombok.Getter;

@Getter
public enum CourseStatus {
    ACTIVE("Курс активен"),
    ARCHIVED("Курс в архиве");

    private String description;

    CourseStatus(String description) {
        this.description = description;
    }
}
