package com.example.courses.controller;

import com.example.courses.dto.teacher_dto.CreateTeacher;
import com.example.courses.dto.teacher_dto.TeacherFilter;
import com.example.courses.dto.teacher_dto.TeacherResponse;
import com.example.courses.dto.teacher_dto.UpdateTeacher;
import com.example.courses.service.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
@Tag(name = "Учителя/Преподаватели", description = "Управление учителями")
public class TeacherController {

    private final TeacherService service;

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление учителя по ID")
    public void delete(@PathVariable Long id){
        service.deleteTeacher(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Поиск учителя по ID")
    public TeacherResponse findById(@PathVariable Long id){
        return service.getTeacherById(id);
    }

    @GetMapping
    @Operation(summary = "Список учителей")
    public Page<TeacherResponse> findAllTeachers(@ModelAttribute TeacherFilter filter, Pageable pageable){
        return service.getTeachers(pageable, filter);
    }

    @PostMapping
    @Operation(summary = "Добавление учителей")
    public TeacherResponse create(@Valid @RequestBody CreateTeacher dto){
        return service.createTeacher(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновление учителей по ID", description = "Можно обновить некоторые данные о учителях")
    public TeacherResponse update(@PathVariable Long id, @Valid @RequestBody UpdateTeacher dto){
        return service.updateTeacher(id, dto);
    }
}
