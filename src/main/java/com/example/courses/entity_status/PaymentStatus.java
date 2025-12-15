package com.example.courses.entity_status;

import lombok.Getter;

@Getter
public enum PaymentStatus {
    PENDING("Ожидает оплаты"),
    PAID("Оплачено"),
    FAILED("Ошибка оплаты"),
    CANCELING("Отколено");

    private String description;

    PaymentStatus(String description) {
        this.description = description;
    }
}
