package com.example.courses.specification;

import com.example.courses.entity.Enrollment;
import com.example.courses.entity_status.EnrollmentStatus;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class EnrollSpecification {
    public static Specification<Enrollment> studentIdEquals(Long studentId) {
        return (root, query, cb) -> {
            if (studentId == null) return null;
            return cb.equal(root.get("student").get("id"), studentId);
        };
    }

    public static Specification<Enrollment> courseIdEquals(Long courseId) {
        return (root, query, cb) -> {
            if (courseId == null) return null;
            return cb.equal(root.get("course").get("id"), courseId);
        };
    }

    public static Specification<Enrollment> enrollDateFrom(LocalDate enrollDate) {
        return (root, query, cb) -> {
            if (enrollDate == null) return null;
            return cb.greaterThanOrEqualTo(root.get("enrollDate"), enrollDate);
        };
    }

    public static Specification<Enrollment> endDateTo(LocalDate endDate) {
        return (root, query, cb) -> {
            if (endDate == null) return null;
            return cb.lessThanOrEqualTo(root.get("endDate"), endDate);
        };
    }

    public static Specification<Enrollment> statusEquals(EnrollmentStatus status) {
        return (root, query, cb) -> {
            if (status == null) return null;
            return cb.equal(root.get("status"), status);
        };
    }
}
