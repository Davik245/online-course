package com.example.courses.controller;

import com.example.courses.dto.enrollment_dto.CreateEnrollmentRequest;
import com.example.courses.dto.enrollment_dto.EnrollFilter;
import com.example.courses.dto.enrollment_dto.EnrollmentResponse;
import com.example.courses.dto.enrollment_dto.UpdateEnrollment;
import com.example.courses.entity.Enrollment;
import com.example.courses.service.EnrollmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentContoller {
    private final EnrollmentService enrollmentService;

    @DeleteMapping
    public void delete(Long id){
        enrollmentService.delete(id);
    }

    @GetMapping("/{id}")
    public EnrollmentResponse get(@PathVariable Long id){
        return enrollmentService.findById(id);
    }

    @GetMapping
    public Page<EnrollmentResponse> getAll(Pageable pageable, @ModelAttribute EnrollFilter filter){
        return enrollmentService.findAll(filter, pageable);
    }

    @PutMapping("/{id}")
    public EnrollmentResponse update(@PathVariable Long id, @Valid @RequestBody UpdateEnrollment dto) {
        return enrollmentService.updateStatus(id, dto);
    }

    @PostMapping
    public EnrollmentResponse create(@Valid @RequestBody CreateEnrollmentRequest request) {
        return enrollmentService.create(request);
    }
}
