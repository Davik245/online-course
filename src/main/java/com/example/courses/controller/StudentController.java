package com.example.courses.controller;

import com.example.courses.dto.student_dto.CreateStudent;
import com.example.courses.dto.student_dto.StudentFilter;
import com.example.courses.dto.student_dto.StudentResponse;
import com.example.courses.dto.student_dto.UpdateStudent;
import com.example.courses.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService service;

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteStudent(id);
    }

    @PutMapping("/{id}")
    public StudentResponse update(@PathVariable Long id, @Valid @RequestBody UpdateStudent dto){
        return service.update(id, dto);
    }

    @GetMapping("/{id}")
    public StudentResponse findById(@PathVariable Long id) {
        return service.getStudent(id);
    }

    @GetMapping
    public Page<StudentResponse> findAll(Pageable pageable, @ModelAttribute StudentFilter filter) {
        return service.getStudents(pageable, filter);
    }

    @PostMapping
    public StudentResponse createStudent(@Valid @RequestBody CreateStudent dto) {
        return service.create(dto);
    }
}
