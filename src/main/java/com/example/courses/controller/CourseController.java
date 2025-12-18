package com.example.courses.controller;

import com.example.courses.dto.course_dto.CourseFilter;
import com.example.courses.dto.course_dto.CourseResponse;
import com.example.courses.dto.course_dto.CreateCoureseRequest;
import com.example.courses.dto.course_dto.UpdateCourse;
import com.example.courses.entity_status.CourseStatus;
import com.example.courses.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
@Slf4j
public class CourseController {

    private final CourseService service;

    @GetMapping
    public Page<CourseResponse> findAll(
            @ModelAttribute CourseFilter filter,
            Pageable pageable
    ) {
        log.info("Запрос курсов через контроллер с фильтром: {}", filter);

        return service.getCourses(filter, pageable);
    }


    @GetMapping("/{id}")
    public CourseResponse get(@PathVariable Long id) {
        return service.getCourseById(id);
    }


    @PostMapping
    public CourseResponse create(@Valid @RequestBody CreateCoureseRequest dto) {
        return service.create(dto);
    }


    @PutMapping("/{id}")
    public CourseResponse update(@PathVariable Long id,
                                 @Valid @RequestBody UpdateCourse dto) {
        return service.update(id, dto);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}
