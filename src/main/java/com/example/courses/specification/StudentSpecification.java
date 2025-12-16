package com.example.courses.specification;

import com.example.courses.entity.Student;
import org.springframework.data.jpa.domain.Specification;

public class StudentSpecification {
    public static Specification<Student> fristNameContains(String firstName) {
        return ((root, query, cb) ->{
           if(firstName == null || firstName.isBlank()) return null;
           return cb.like(root.get("firstName"), "%" + firstName.toLowerCase() + "%");
        });
    }

    public static Specification<Student> lastNameContains(String lastName) {
        return ((root, query, cb) -> {
           if(lastName == null || lastName.isBlank()) return null;
           return cb.like(root.get("lastName"), "%" + lastName.toLowerCase() + "%");
        });
    }

    public static Specification<Student> middleNameContains(String middleName) {
        return((root, query, cb) -> {
            if(middleName == null || middleName.isBlank()) return null;
            return cb.like(root.get("middleName"), "%" + middleName.toLowerCase() + "%");
        });
    }

    public static Specification<Student> phoneNumberContains(String phoneNumber) {
        return ((root, query, cb) -> {
           if(phoneNumber == null || phoneNumber.isBlank()) return null;
           return cb.like(root.get("phoneNumber"), "%" + phoneNumber.toLowerCase() + "%");
        });
    }

    public static Specification<Student> emailContains(String email) {
        return ((root, query, cb) -> {
           if(email == null || email.isBlank()) return null;
           return cb.like(root.get("email"), "%" + email.toLowerCase() + "%");
        });
    }

}
