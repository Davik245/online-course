package com.example.courses.controller;

import com.example.courses.dto.course_dto.CourseFilter;
import com.example.courses.dto.course_dto.CourseResponse;
import com.example.courses.dto.course_dto.CreateCoureseRequest;
import com.example.courses.dto.course_dto.UpdateCourse;
import com.example.courses.entity_status.CourseStatus;
import com.example.courses.service.CourseService;
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
            @RequestParam(required = false) String name,
            @RequestParam(required = false) CourseStatus status,
            @RequestParam(required = false) BigDecimal priceFrom,
            @RequestParam(required = false) BigDecimal priceTo,
            @RequestParam(required = false) LocalDate startDateFrom,
            @RequestParam(required = false) LocalDate startDateTo,
            @RequestParam(required = false) Long teacherId,
            Pageable pageable
    ) {
        CourseFilter filter = new CourseFilter(
                name, status, priceFrom, priceTo,
                startDateFrom, startDateTo, teacherId
        );

        log.info("Запрос курсов через контроллер с фильтром: {}", filter);

        return service.getCourses(filter, pageable);
    }


    @GetMapping("/{id}")
    public CourseResponse get(@PathVariable Long id) {
        return service.getCourseById(id);
    }


    @PostMapping
    public CourseResponse create(@RequestBody CreateCoureseRequest dto) {
        return service.create(dto);
    }


    @PutMapping("/{id}")
    public CourseResponse update(@PathVariable Long id,
                                 @RequestBody UpdateCourse dto) {
        return service.update(id, dto);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}
