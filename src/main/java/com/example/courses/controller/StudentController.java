package com.example.courses.controller;

import com.example.courses.dto.student_dto.CreateStudent;
import com.example.courses.dto.student_dto.StudentFilter;
import com.example.courses.dto.student_dto.StudentResponse;
import com.example.courses.dto.student_dto.UpdateStudent;
import com.example.courses.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/students")
@Tag(name = "Студент", description = "Управление студентами")
public class StudentController {
    private final StudentService service;

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление студента по ID")
    public void delete(@PathVariable Long id) {
        service.deleteStudent(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновление данных о студенте", description = "Можно обновить некоторые данные")
    public StudentResponse update(@PathVariable Long id, @Valid @RequestBody UpdateStudent dto){
        return service.update(id, dto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Поиск студента по ID")
    public StudentResponse findById(@PathVariable Long id) {
        return service.getStudent(id);
    }

    @GetMapping
    @Operation(summary = "Список всех студентов")
    public Page<StudentResponse> findAll(Pageable pageable, @ModelAttribute StudentFilter filter) {
        return service.getStudents(pageable, filter);
    }

    @PostMapping
    @Operation(summary = "Добавление студента")
    public StudentResponse createStudent(@Valid @RequestBody CreateStudent dto) {
        return service.create(dto);
    }
}
