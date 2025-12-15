package com.example.courses.map;

import com.example.courses.dto.enrollment_dto.CreateEnrollmentRequest;
import com.example.courses.dto.enrollment_dto.EnrollmentResponse;
import com.example.courses.dto.enrollment_dto.UpdateEnrollment;
import com.example.courses.entity.Enrollment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {

    // create DTO -> entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "course", ignore = true)
    @Mapping(target = "student", ignore = true)
    Enrollment toEntity(CreateEnrollmentRequest dto);

    // entity -> response DTO
    @Mapping(target = "studentId", source = "student.id") // чтобы маппер понял, чему пренадлежат айдишники
    @Mapping(target = "courseId", source = "course.id")
    EnrollmentResponse toResponse(Enrollment entity);

    // update DTO -> entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "course", ignore = true)
    @Mapping(target = "student", ignore = true)
    void updateEntity(UpdateEnrollment updateEntity, @MappingTarget Enrollment target);
}
