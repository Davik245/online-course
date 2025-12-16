package com.example.courses.controller;

import com.example.courses.dto.teacher_dto.CreateTeacher;
import com.example.courses.dto.teacher_dto.TeacherResponse;
import com.example.courses.dto.teacher_dto.UpdateTeacher;
import com.example.courses.service.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @PostMapping
    public TeacherResponse create(@RequestBody CreateTeacher dto){
        return service.createTeacher(dto);
    }

    @PutMapping("/{id}")
    public TeacherResponse update(@PathVariable Long id, @RequestBody UpdateTeacher dto){
        return service.updateTeacher(id, dto);
    }
}
