package com.example.courses.specification;

import com.example.courses.entity.Payment;
import com.example.courses.entity_status.PaymentStatus;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PaymentSpecification {

    public static Specification<Payment> studentIdEquals(Long studentId) {
        return (root, query, cb) -> {
            if (studentId == null) return null;
            return cb.equal(root.get("student").get("id"), studentId);
        };
    }

    public static Specification<Payment> courseIdEquals(Long courseId) {
        return (root, query, cb) -> {
            if (courseId == null) return null;
            return cb.equal(root.get("course").get("id"), courseId);
        };
    }

    public static Specification<Payment> paymentDateFrom(LocalDate dateFrom) {
        return (root, query, cb) -> {
            if (dateFrom == null) return null;
            return cb.greaterThanOrEqualTo(root.get("paymentDate"), dateFrom);
        };
    }

    public static Specification<Payment> paymentDateTo(LocalDate dateTo) {
        return (root, query, cb) -> {
            if (dateTo == null) return null;
            return cb.lessThanOrEqualTo(root.get("paymentDate"), dateTo);
        };
    }

    public static Specification<Payment> statusEquals(PaymentStatus status) {
        return (root, query, cb) -> {
            if (status == null) return null;
            return cb.equal(root.get("status"), status);
        };
    }

    public static Specification<Payment> amountEquals(BigDecimal amount) {
        return (root, query, cb) -> {
            if (amount == null) return null;
            return cb.equal(root.get("amount"), amount);
        };
    }

    public static Specification<Payment> paymentDateEquals(LocalDate paymentDate) {
        return (root, query, cb) -> {
            if (paymentDate == null) return null;
            return cb.equal(root.get("paymentDate"), paymentDate);
        };
    }
}