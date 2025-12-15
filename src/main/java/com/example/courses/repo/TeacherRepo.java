package com.example.courses.repo;

import com.example.courses.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TeacherRepo extends JpaRepository<Teacher, Long>, JpaSpecificationExecutor<Teacher> {
}
