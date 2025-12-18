package com.example.courses.controller;

import com.example.courses.dto.enrollment_dto.CreateEnrollmentRequest;
import com.example.courses.dto.enrollment_dto.EnrollFilter;
import com.example.courses.dto.enrollment_dto.EnrollmentResponse;
import com.example.courses.dto.enrollment_dto.UpdateEnrollment;
import com.example.courses.entity.Enrollment;
import com.example.courses.service.EnrollmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
@Tag(name = "Платежи", description = "Можно посмотреть кто и какой студент зачислен, и на какой период")
public class EnrollmentContoller {
    private final EnrollmentService enrollmentService;

    @DeleteMapping
    @Operation(summary = "Удаление платежа по ID")
    public void delete(Long id){
        enrollmentService.delete(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение платежа по ID")
    public EnrollmentResponse get(@PathVariable Long id){
        return enrollmentService.findById(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка платежей")
    public Page<EnrollmentResponse> getAll(Pageable pageable, @ModelAttribute EnrollFilter filter){
        return enrollmentService.findAll(filter, pageable);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновление платежа по ID", description = "Можно обновить некоторые данные")
    public EnrollmentResponse update(@PathVariable Long id, @Valid @RequestBody UpdateEnrollment dto) {
        return enrollmentService.updateStatus(id, dto);
    }

    @PostMapping
    @Operation(summary = "Создание платежа")
    public EnrollmentResponse create(@Valid @RequestBody CreateEnrollmentRequest request) {
        return enrollmentService.create(request);
    }
}
