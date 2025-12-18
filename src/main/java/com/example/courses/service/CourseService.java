package com.example.courses.service;

import com.example.courses.dto.course_dto.CourseFilter;
import com.example.courses.dto.course_dto.CourseResponse;
import com.example.courses.dto.course_dto.CreateCoureseRequest;
import com.example.courses.dto.course_dto.UpdateCourse;
import com.example.courses.entity.Course;
import com.example.courses.entity.Teacher;
import com.example.courses.exceptions.custom.EntityNotFoundException;
import com.example.courses.map.CourseMapper;
import com.example.courses.repo.CourseRepo;
import com.example.courses.specification.CourseSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepo repo;
    private final CourseMapper courseMapper;

    public void deleteById(Long id) {
        log.info("Удаляем курс с айди: " + id);

        if(!repo.existsById(id)) {
            log.warn("Курс с айди: " + id + " не существует");
            throw new EntityNotFoundException("Курс с айди: " + id + " не существует");
        }

        repo.deleteById(id);
    }

    @Transactional(readOnly = true)
    public CourseResponse getCourseById(Long id) {
        log.info("Получаем курс с айди: " + id);

        Course course = repo.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Курс с айди: " + id + " не существует"));

        return courseMapper.toResponse(course);
    }

    @Transactional(readOnly = true)
    public Page<CourseResponse> getCourses(CourseFilter filter, Pageable pageable) {
        log.info("Получаем фильтр => {}", filter);

        Specification<Course> spec = Specification.allOf(
                CourseSpecification.nameContains(filter.getName()),
                CourseSpecification.hasStatus(filter.getStatus()),
                CourseSpecification.priceFrom(filter.getPriceFrom()),
                CourseSpecification.priceTo(filter.getPriceTo()),
                CourseSpecification.startDateFrom(filter.getStartDateFrom()),
                CourseSpecification.startDateTo(filter.getStartDateTo()),
                CourseSpecification.teacherId(filter.getTeacherId())
        );


        Page<Course> page = repo.findAll(spec, pageable);

        return page.map(courseMapper::toResponse);
    }

    public CourseResponse create(CreateCoureseRequest request) {
        log.info("Создаем курс: {}", request);

        Course course = courseMapper.toEntity(request);

        // Привязать курс к существующему учителю, с помощью это части загружаем только айди учителя, а не весь объект
        if (request.teacher_id() != null) {
            Teacher teacher = new Teacher();
            teacher.setId(request.teacher_id());
            course.setTeacher(teacher);
        }

        Course saved = repo.save(course);

        return courseMapper.toResponse(saved);

    }

    public CourseResponse update(Long id, UpdateCourse updateCourse) {
        log.info("Обновляем курс, айди: {}", id);

        Course entity = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Курс не найден"));

        courseMapper.updateEntity(updateCourse, entity);

        Course saved = repo.save(entity);

        return courseMapper.toResponse(saved);
    }


}
