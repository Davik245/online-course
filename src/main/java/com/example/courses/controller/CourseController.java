package com.example.courses.controller;

import com.example.courses.dto.course_dto.CourseFilter;
import com.example.courses.dto.course_dto.CourseResponse;
import com.example.courses.dto.course_dto.CreateCoureseRequest;
import com.example.courses.dto.course_dto.UpdateCourse;
import com.example.courses.entity_status.CourseStatus;
import com.example.courses.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Courses", description = "Операции с курсами")
public class CourseController {

    private final CourseService service;

    @GetMapping
    @Operation(summary = "Получение списка курсов")
    public Page<CourseResponse> findAll(
            @ModelAttribute CourseFilter filter,
            Pageable pageable
    ) {
        log.info("Запрос курсов через контроллер с фильтром: {}", filter);

        return service.getCourses(filter, pageable);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Получить курс по ID")
    public CourseResponse get(@PathVariable Long id) {
        return service.getCourseById(id);
    }


    @PostMapping
    @Operation(summary = "Создание курса")
    public CourseResponse create(@Valid @RequestBody CreateCoureseRequest dto) {
        return service.create(dto);
    }


    @PutMapping("/{id}")
    @Operation(summary = "Обновление курса по ID", description = "Можно обновить некоторые данные о курсе")
    public CourseResponse update(@PathVariable Long id, @Valid @RequestBody UpdateCourse dto) {
        return service.update(id, dto);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление курса по ID")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}
