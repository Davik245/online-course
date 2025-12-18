package com.example.courses.controller;

import com.example.courses.dto.teacher_dto.CreateTeacher;
import com.example.courses.dto.teacher_dto.TeacherFilter;
import com.example.courses.dto.teacher_dto.TeacherResponse;
import com.example.courses.dto.teacher_dto.UpdateTeacher;
import com.example.courses.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService service;

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.deleteTeacher(id);
    }

    @GetMapping("/{id}")
    public TeacherResponse findById(@PathVariable Long id){
        return service.getTeacherById(id);
    }

    @GetMapping
    public Page<TeacherResponse> findAllTeachers(@ModelAttribute TeacherFilter filter, Pageable pageable){
        return service.getTeachers(pageable, filter);
    }

    @PostMapping
    public TeacherResponse create(@Valid @RequestBody CreateTeacher dto){
        return service.createTeacher(dto);
    }

    @PutMapping("/{id}")
    public TeacherResponse update(@PathVariable Long id, @Valid @RequestBody UpdateTeacher dto){
        return service.updateTeacher(id, dto);
    }
}
