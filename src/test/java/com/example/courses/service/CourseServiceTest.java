package com.example.courses.service;

import com.example.courses.dto.course_dto.CourseFilter;
import com.example.courses.dto.course_dto.CourseResponse;
import com.example.courses.dto.course_dto.CreateCoureseRequest;
import com.example.courses.dto.course_dto.UpdateCourse;
import com.example.courses.entity.Course;
import com.example.courses.entity.Teacher;
import com.example.courses.entity_status.CourseStatus;
import com.example.courses.exceptions.custom.EntityNotFoundException;
import com.example.courses.map.CourseMapperImpl;
import com.example.courses.repo.CourseRepo;
import com.example.courses.repo.TeacherRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@Import({CourseService.class, CourseMapperImpl.class})
@TestPropertySource(properties = "spring.liquibase.enabled=false")
public class CourseServiceTest {

    @Autowired
    private CourseService service;

    @Autowired
    private CourseRepo repo;

    @Autowired
    private TeacherRepo teacherRepo;

    @BeforeEach
    void cleanDb() {
        repo.deleteAll();
        teacherRepo.deleteAll();
    }

    @Test
    void createCourse() {

        Teacher teacher = new Teacher();
        teacher.setFirstName("John");
        teacher.setLastName("Doe");
        teacher.setMiddleName("A");
        teacher.setEmail("john.doe@test.com");
        teacher.setPhoneNumber("89994116592");
        teacher.setSpecialization("Java");
        teacherRepo.saveAndFlush(teacher); // cразу записываем в БД

        CreateCoureseRequest createDto = new CreateCoureseRequest(
                "Java", 10, BigDecimal.valueOf(500),
                CourseStatus.ACTIVE,
                LocalDate.now(),
                LocalDate.now().plusDays(15),
                teacher.getId()
        );

        CourseResponse response = service.create(createDto);

        assertThat(response).isNotNull();
        assertThat(response.name()).isEqualTo("Java");
        assertThat(response.teacherId()).isEqualTo(teacher.getId());
    }

    @Test
    void getByIdExisting(){
        Course entity = new Course();
        entity.setName("Python");
        repo.save(entity);

        CourseResponse response = service.getCourseById(entity.getId());

        assertThat(response).isNotNull();
        assertThat(response.name()).isEqualTo("Python");
    }

    @Test
    void getByIdNotExisting() throws Exception{
        assertThatThrownBy(() -> service.getCourseById(-1L)).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void updateCourseExisting() {
        Course entity = new Course();
        entity.setName("Old");
        repo.save(entity);

        UpdateCourse update = new UpdateCourse("New", 10, BigDecimal.valueOf(690), CourseStatus.ACTIVE);
        CourseResponse response = service.update(entity.getId(), update);

        assertThat(response.name()).isEqualTo("New");
        assertThat(response.status()).isEqualTo(CourseStatus.ACTIVE);
        assertThat(response.hours()).isEqualTo(10);
        assertThat(response.price()).isEqualTo(BigDecimal.valueOf(690));
    }

    @Test
    void updateCourseNotExisting() {
            UpdateCourse update = new UpdateCourse("X", 10, BigDecimal.valueOf(690), CourseStatus.ACTIVE);
            assertThatThrownBy(() -> service.update(999L, update)).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void deletesSuccessfully(){
        Course entity = new Course();
        entity.setName("Python");
        repo.save(entity);

        service.deleteById(entity.getId());

        assertThat(repo.existsById(entity.getId())).isFalse();
    }

    @Test
    void deletesNotFound(){
        assertThatThrownBy(() -> service.deleteById(-1L)).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void findAllCourses() {
        Course c1 = new Course(null, "Java", 30, BigDecimal.valueOf(500),
                CourseStatus.ACTIVE, LocalDate.now(), LocalDate.now().plusDays(15),
                null, new ArrayList<>(), new ArrayList<>());

        Course c2= new Course(null, "Python", 25, BigDecimal.valueOf(400),
                CourseStatus.ACTIVE, LocalDate.now(), LocalDate.now().plusDays(10),
                null, new ArrayList<>(), new ArrayList<>());

        repo.save(c1);
        repo.save(c2);

        CourseFilter filter = new CourseFilter();
        filter.setName("Java");

        Page<CourseResponse> page = service.getCourses(filter, PageRequest.of(0, 10));

        assertThat(page.getTotalElements()).isEqualTo(1);
        assertThat(page.getContent().get(0).name()).isEqualTo("Java");
    }
}
