package com.example.courses.exceptions.custom;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entity, Long id) {
        super(entity + " с id=" + id + " не найден");
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
