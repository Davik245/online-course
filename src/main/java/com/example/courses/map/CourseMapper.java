package com.example.courses.map;

import com.example.courses.dto.course_dto.CourseResponse;
import com.example.courses.dto.course_dto.CreateCoureseRequest;
import com.example.courses.dto.course_dto.UpdateCourse;
import com.example.courses.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    // Игнорируем те поля, которых нет в дто и игнорируем чужие сущности, например учителей, оплаты и прочее
    // так же должны игнорировать связи

    // Create DTO → Entity
    @Mapping(target = "id", ignore = true) // id должно генерироваться само
    @Mapping(target = "teacher", ignore = true)
    @Mapping(target = "enrollments", ignore = true)
    @Mapping(target = "payments", ignore = true)
    Course toEntity(CreateCoureseRequest dto);

    // Entity → Response DTO
    @Mapping(target = "teacherId", source = "teacher.id")
    CourseResponse toResponse(Course entity);

    // Update DTO → обновление Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "teacher", ignore = true)
    @Mapping(target = "enrollments", ignore = true)
    @Mapping(target = "payments", ignore = true)
    void updateEntity(UpdateCourse dto, @MappingTarget Course entity);

}
