package com.example.courses.service;

import com.example.courses.dto.student_dto.CreateStudent;
import com.example.courses.dto.student_dto.StudentFilter;
import com.example.courses.dto.student_dto.StudentResponse;
import com.example.courses.dto.student_dto.UpdateStudent;
import com.example.courses.entity.Student;
import com.example.courses.exceptions.custom.EntityNotFoundException;
import com.example.courses.map.StudentMapper;
import com.example.courses.repo.StudentRepo;
import com.example.courses.specification.StudentSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepo studentRepo;
    private final StudentMapper studentMapper;

    public Page<StudentResponse> getStudents(Pageable pageable, StudentFilter filter) {
        Specification<Student> spec = Specification.allOf(
                StudentSpecification.fristNameContains(filter.firstName()),
                StudentSpecification.lastNameContains(filter.lastName()),
                StudentSpecification.middleNameContains(filter.middleName()),
                StudentSpecification.emailContains(filter.email()),
                StudentSpecification.phoneNumberContains(filter.phoneNumber())
        );

        log.info("Получение списка студентов..");
        Page<Student> students = studentRepo.findAll(spec, pageable);

        return students.map(studentMapper::toResponse);
    }

    public void deleteStudent(Long id) {
        log.info("Удаление студента под айди {}", id);
        studentRepo.deleteById(id);
    }

    public StudentResponse getStudent(Long id) {
        log.info("Ищем студента с айди {}", id);

        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Студент не найден.."));

        return studentMapper.toResponse(student);
    }

    public StudentResponse create(CreateStudent dto){
        log.info("Добавление студента..");

        Student student = studentMapper.toEntity(dto);
        studentRepo.save(student);

        return studentMapper.toResponse(student);
    }

    public StudentResponse update(Long id, UpdateStudent dto) {
        log.info("Обновляем студента с айди {}", id);

        Student student = studentRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Студент не найден"));
        studentRepo.save(student);

        return studentMapper.toResponse(student);
    }


}
