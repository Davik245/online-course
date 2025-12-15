package com.example.courses.map;

import com.example.courses.dto.student_dto.CreateStudent;
import com.example.courses.dto.student_dto.StudentResponse;
import com.example.courses.dto.student_dto.UpdateStudent;
import com.example.courses.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target = "id" , ignore = true)
    @Mapping(target = "enrollments", ignore = true)
    @Mapping(target = "payments" , ignore = true)
    Student toEntity(CreateStudent dto);

    StudentResponse toResponse(Student entity);

    @Mapping(target = "id" , ignore = true)
    @Mapping(target = "enrollments", ignore = true)
    @Mapping(target = "payments" , ignore = true)
    void updateStudent(UpdateStudent dto, @MappingTarget Student entity);

}
