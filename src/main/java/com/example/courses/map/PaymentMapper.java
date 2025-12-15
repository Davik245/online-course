package com.example.courses.map;

import com.example.courses.dto.payment_dto.CreatePaymentRequest;
import com.example.courses.dto.payment_dto.PaymentResponse;
import com.example.courses.dto.payment_dto.UpdatePayment;
import com.example.courses.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "course", ignore = true)
    @Mapping(target = "student", ignore = true)
    Payment toEntity(CreatePaymentRequest dto);

    @Mapping(target = "studentId", source = "student.id")
    @Mapping(target = "courseId", source = "course.id")
    PaymentResponse toResponse(Payment entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "course", ignore = true)
    @Mapping(target = "student", ignore = true)
    void updatePayment(UpdatePayment dto, @MappingTarget Payment entity);

}
