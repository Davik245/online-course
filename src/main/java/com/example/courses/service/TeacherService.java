package com.example.courses.service;

import com.example.courses.dto.teacher_dto.CreateTeacher;
import com.example.courses.dto.teacher_dto.TeacherFilter;
import com.example.courses.dto.teacher_dto.TeacherResponse;
import com.example.courses.dto.teacher_dto.UpdateTeacher;
import com.example.courses.entity.Teacher;
import com.example.courses.exceptions.custom.EntityNotFoundException;
import com.example.courses.map.TeacherMapper;
import com.example.courses.repo.TeacherRepo;
import com.example.courses.specification.TeacherSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepo teacherRepo;
    private final TeacherMapper teacherMapper;

    public Page<TeacherResponse> getTeachers(Pageable pageable, TeacherFilter filter) {
        log.info("Получаем учителей..");

        Specification<Teacher> spec = Specification.allOf(
                TeacherSpecification.firstNameContains(filter.firstName()),
                TeacherSpecification.lastNameContains(filter.lastName()),
                TeacherSpecification.middleNameContains(filter.middleName()),
                TeacherSpecification.emailContains(filter.email()),
                TeacherSpecification.phoneContains(filter.phoneNumber()),
                TeacherSpecification.specializationContains(filter.specialization())
        );

        log.info("Получение списка учителей..");
        Page<Teacher> page = teacherRepo.findAll(spec, pageable);

        return page.map(teacherMapper::toResponse);
    }

    public TeacherResponse getTeacherById(Long id) {
        log.info("Ищем учителя с айди {}", id);

        Teacher teacher = teacherRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Учитель не найден"));

        return teacherMapper.toResponse(teacher);
    }

    public TeacherResponse createTeacher(CreateTeacher dto) {
        log.info("Добавление учителя..");

        Teacher teacher = teacherMapper.toEntity(dto);
        log.info("Сохраняем учителя..");
        teacherRepo.save(teacher);
        return teacherMapper.toResponse(teacher);
    }

    public TeacherResponse updateTeacher(Long id, UpdateTeacher dto) {
        log.info("Обновляем данные учителя под айди {}", id);

        Teacher teacher = teacherRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Учитель не найден"));

        teacherMapper.updateTeacher(dto, teacher);
        log.info("Сохраняем изменения..");
        teacherRepo.save(teacher);
        return teacherMapper.toResponse(teacher);
    }

    public void deleteTeacher(Long id) {
        log.info("Удаляем учителя с айди {}", id);

        if(!teacherRepo.existsById(id)) {
            log.warn("Учителя с айди {} не существует", id);
            throw new EntityNotFoundException("Учитель не найден");
        }

        teacherRepo.deleteById(id);
    }

}
