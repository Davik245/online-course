package com.example.courses.specification;

import com.example.courses.entity.Course;
import com.example.courses.entity_status.CourseStatus;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CourseSpecification {
    public static Specification<Course> nameContains(String name) {
        return (root, query, cb) -> {
            if (name == null || name.isBlank()) return null;
            return cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
        };
    }

    public static Specification<Course> hasStatus(CourseStatus status) {
        return (root, query, cb) -> {
            if (status == null) return null;
            return cb.equal(root.get("status"), status);
        };
    }

    public static Specification<Course> priceFrom(BigDecimal priceFrom) {
        return (root, query, cb) -> {
            if (priceFrom == null) return null;
            return cb.greaterThanOrEqualTo(root.get("price"), priceFrom);
        };
    }

    public static Specification<Course> priceTo(BigDecimal priceTo) {
        return (root, query, cb) -> {
            if (priceTo == null) return null;
            return cb.lessThanOrEqualTo(root.get("price"), priceTo);
        };
    }

    public static Specification<Course> startDateFrom(LocalDate date) {
        return (root, query, cb) -> {
            if (date == null) return null;
            return cb.greaterThanOrEqualTo(root.get("startDate"), date);
        };
    }

    public static Specification<Course> startDateTo(LocalDate date) {
        return (root, query, cb) -> {
            if (date == null) return null;
            return cb.lessThanOrEqualTo(root.get("startDate"), date);
        };
    }

    public static Specification<Course> teacherId(Long teacherId) {
        return (root, query, cb) -> {
            if (teacherId == null) return null;
            return cb.equal(root.get("teacher").get("id"), teacherId);
        };
    }
}
