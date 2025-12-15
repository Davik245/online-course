package com.example.courses.map;

import com.example.courses.dto.teacher_dto.CreateTeacher;
import com.example.courses.dto.teacher_dto.TeacherResponse;
import com.example.courses.dto.teacher_dto.UpdateTeacher;
import com.example.courses.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "courses", ignore = true)
    Teacher toEntity(CreateTeacher dto);

    TeacherResponse toResponse(Teacher entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "courses", ignore = true)
    void updateTeacher(UpdateTeacher dto, @MappingTarget Teacher entity);
}
