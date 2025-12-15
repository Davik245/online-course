package com.example.courses.maps;

import com.example.courses.dto.course_dto.CourseResponse;
import com.example.courses.dto.course_dto.CreateCoureseRequest;
import com.example.courses.dto.course_dto.UpdateCourse;
import com.example.courses.entity.Course;
import com.example.courses.entity.Teacher;
import com.example.courses.entity_status.CourseStatus;
import com.example.courses.map.CourseMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class CourseMapperTest {

    @Autowired
    private CourseMapper mapper;

    private static final BigDecimal PRICE = BigDecimal.valueOf(500);
    private static final LocalDate START = LocalDate.now();
    private static final LocalDate END = LocalDate.now().plusDays(30);

    private Course createCourse(Long id, String name, int hours, CourseStatus status, Teacher teacher) {
        Course course = new Course();
        course.setId(id);
        course.setName(name);
        course.setHours(hours);
        course.setPrice(PRICE);
        course.setStatus(status);
        course.setStartDate(START);
        course.setEndDate(END);
        course.setTeacher(teacher);
        return course;
    }

    private void assertCourseFields(Course course, String name, int hours, BigDecimal price, CourseStatus status) {
        assertThat(course.getName()).isEqualTo(name);
        assertThat(course.getHours()).isEqualTo(hours);
        assertThat(course.getPrice()).isEqualByComparingTo(price);
        assertThat(course.getStatus()).isEqualTo(status);
    }

    @Test
    void testToEntity(){
        CreateCoureseRequest dto = new CreateCoureseRequest(
                "Java", 40, PRICE, CourseStatus.ACTIVE, START, END, 1L
        );

        Course entity = mapper.toEntity(dto);

        assertThat(entity.getId()).isNull();
        assertCourseFields(entity, "Java", 40, PRICE, CourseStatus.ACTIVE);
        assertThat(entity.getEnrollments()).isEmpty();
        assertThat(entity.getPayments()).isEmpty();
        assertThat(entity.getTeacher()).isNull();
    }

    @Test
    void testToResponse(){
        Teacher teacher = new Teacher();
        teacher.setId(5L);

        Course entity = createCourse(10L, "Java", 40, CourseStatus.ACTIVE, teacher);

        CourseResponse dto = mapper.toResponse(entity);

        assertThat(dto.id()).isNotNull();
        assertThat(dto.id()).isEqualTo(10L);
        assertThat(dto.name()).isEqualTo("Java");
        assertThat(dto.hours()).isEqualTo(40);
        assertThat(dto.status()).isEqualTo(CourseStatus.ACTIVE);
        assertThat(dto.teacherId()).isEqualTo(5L);
    }

    @Test
    void testUpdateEntity(){
        Course oldCourse = createCourse(null, "Old", 20, CourseStatus.ARCHIVED, null);

        UpdateCourse newCourse = new UpdateCourse("New", 40, PRICE, CourseStatus.ACTIVE);

        mapper.updateEntity(newCourse, oldCourse);

        assertThat(newCourse.getName()).isEqualTo("New");
        assertThat(newCourse.getHours()).isEqualTo(40);
        assertThat(newCourse.getPrice()).isNotNull();
        assertThat(newCourse.getStatus()).isEqualTo(CourseStatus.ACTIVE);
    }
}
