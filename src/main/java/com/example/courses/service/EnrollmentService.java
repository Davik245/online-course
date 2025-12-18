package com.example.courses.service;

import com.example.courses.dto.enrollment_dto.CreateEnrollmentRequest;
import com.example.courses.dto.enrollment_dto.EnrollFilter;
import com.example.courses.dto.enrollment_dto.EnrollmentResponse;
import com.example.courses.dto.enrollment_dto.UpdateEnrollment;
import com.example.courses.entity.Course;
import com.example.courses.entity.Enrollment;
import com.example.courses.entity.Student;
import com.example.courses.exceptions.custom.EntityNotFoundException;
import com.example.courses.map.EnrollmentMapper;
import com.example.courses.repo.CourseRepo;
import com.example.courses.repo.EnrollmentRepo;
import com.example.courses.repo.StudentRepo;
import com.example.courses.specification.EnrollSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class EnrollmentService {

    private final EnrollmentRepo enrollmentRepo;
    private final StudentRepo studentRepo;
    private final CourseRepo courseRepo;
    private final EnrollmentMapper enrollmentMapper;

    public EnrollmentResponse create(CreateEnrollmentRequest request) {
        log.info("Создание записи о зачислении: studentId={}, courseId={}, status={}",
                request.studentId(),
                request.courseId(),
                request.status());

        Enrollment enrollment = enrollmentMapper.toEntity(request);

        Student student = studentRepo.findById(request.studentId())
                .orElseThrow(() -> {
                    log.warn("Студент не найден: id={}", request.studentId());
                    return new EntityNotFoundException(
                            "Студент с id=" + request.studentId() + " не найден"
                    );
                });

        Course course = courseRepo.findById(request.courseId())
                .orElseThrow(() -> {
                    log.warn("Курс не найден: id={}", request.courseId());
                    return new EntityNotFoundException(
                            "Курс с id=" + request.courseId() + " не найден"
                    );
                });

        enrollment.setStudent(student);
        enrollment.setCourse(course);

        Enrollment saved = enrollmentRepo.save(enrollment);

        log.info("Зачисление успешно создано: id={}", saved.getId());
        return enrollmentMapper.toResponse(saved);
    }

    public EnrollmentResponse updateStatus(Long enrollmentId, UpdateEnrollment update) {
        log.info(
                "Обновление статуса зачисления: enrollmentId={}, новыйСтатус={}",
                enrollmentId,
                update.getStatus()
        );

        Enrollment enrollment = enrollmentRepo.findById(enrollmentId)
                .orElseThrow(() -> {
                    log.warn("Зачисление не найдено для обновления: id={}", enrollmentId);
                    return new EntityNotFoundException(
                            "Зачисление с id=" + enrollmentId + " не найдено"
                    );
                });

        enrollmentMapper.updateEntity(update, enrollment);

        Enrollment saved = enrollmentRepo.save(enrollment);

        log.info("Статус зачисления успешно обновлён: id={}, status={}", saved.getId(), saved.getStatus());

        return enrollmentMapper.toResponse(saved);
    }

    @Transactional(readOnly = true)
    public EnrollmentResponse findById(Long id) {
        log.debug("Поиск зачисления по id={}", id);

        Enrollment enrollment = enrollmentRepo.findById(id)
                .orElseThrow(() -> {
                    log.warn("Зачисление не найдено: id={}", id);
                    return new EntityNotFoundException(
                            "Зачисление с id=" + id + " не найдено"
                    );
                });

        return enrollmentMapper.toResponse(enrollment);
    }

    @Transactional(readOnly = true)
    public Page<EnrollmentResponse> findAll(EnrollFilter filter, Pageable pageable) {
        log.debug(
                "Поиск зачислений с фильтром: studentId={}, courseId={}, status={}, pageable={}",
                filter.studentId(),
                filter.courseId(),
                filter.status(),
                pageable
        );

        Specification<Enrollment> spec = Specification.allOf(
                EnrollSpecification.studentIdEquals(filter.studentId()),
                EnrollSpecification.courseIdEquals(filter.courseId()),
                EnrollSpecification.enrollDateFrom(filter.enrollDate()),
                EnrollSpecification.endDateTo(filter.endDate()),
                EnrollSpecification.statusEquals(filter.status())
        );

        Page<EnrollmentResponse> result = enrollmentRepo.findAll(spec, pageable)
                .map(enrollmentMapper::toResponse);

        log.debug("Найдено зачислений: {}", result.getTotalElements());

        return result;
    }

    public void delete(Long id) {
        log.info("Удаление зачисления: id={}", id);

        if (!enrollmentRepo.existsById(id)) {
            log.warn("Зачисление не найдено для удаления: id={}", id);
            throw new EntityNotFoundException(
                    "Зачисление id=" + id + " не найдено"
            );
        }

        enrollmentRepo.deleteById(id);
        log.info("Зачисление успешно удалено: id={}", id);
    }
}

