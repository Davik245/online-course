package com.example.courses.specification;

import com.example.courses.entity.Teacher;
import org.springframework.data.jpa.domain.Specification;

public class TeacherSpecification {
    public static Specification<Teacher> firstNameContains(String firstName) {
        return (root, query, cb) -> {
            if (firstName == null || firstName.isBlank()) return null;
            return cb.like(cb.lower(root.get("firstName")), "%" + firstName.toLowerCase() + "%");
        };
    }

    public static Specification<Teacher> lastNameContains(String lastName) {
        return (root, query, cb) -> {
            if (lastName == null || lastName.isBlank()) return null;
            return cb.like(cb.lower(root.get("lastName")), "%" + lastName.toLowerCase() + "%");
        };
    }

    public static Specification<Teacher> middleNameContains(String middleName) {
        return (root, query, cb) -> {
            if (middleName == null || middleName.isBlank()) return null;
            return cb.like(cb.lower(root.get("middleName")), "%" + middleName.toLowerCase() + "%");
        };
    }

    public static Specification<Teacher> specializationContains(String specialization) {
        return (root, query, cb) -> {
            if (specialization == null || specialization.isBlank()) return null;
            return cb.like(cb.lower(root.get("specialization")), "%" + specialization.toLowerCase() + "%");
        };
    }

    public static Specification<Teacher> emailContains(String email) {
        return (root, query, cb) -> {
            if (email == null || email.isBlank()) return null;
            return cb.like(cb.lower(root.get("email")), "%" + email.toLowerCase() + "%");
        };
    }

    public static Specification<Teacher> phoneContains(String phone) {
        return (root, query, cb) -> {
            if (phone == null || phone.isBlank()) return null;
            return cb.like(root.get("phoneNumber"), "%" + phone + "%");
        };
    }
}
