package com.example.courses.entity_status;

import lombok.Getter;

@Getter
public enum EnrollmentStatus {
    ACTIVE("Студент проходит курс"),
    COMPLETED("Курс завершён"),
    CANCELLED("Запись отменена");

    private String description;

    EnrollmentStatus(String description) {
        this.description = description;
    }

}
