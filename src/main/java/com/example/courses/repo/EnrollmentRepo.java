package com.example.courses.repo;

import com.example.courses.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EnrollmentRepo extends JpaRepository<Enrollment, Long>, JpaSpecificationExecutor<Enrollment> {
}
