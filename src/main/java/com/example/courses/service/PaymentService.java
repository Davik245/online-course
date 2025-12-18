package com.example.courses.service;

import com.example.courses.dto.payment_dto.CreatePaymentRequest;
import com.example.courses.dto.payment_dto.PaymentFilter;
import com.example.courses.dto.payment_dto.PaymentResponse;
import com.example.courses.dto.payment_dto.UpdatePayment;
import com.example.courses.entity.Course;
import com.example.courses.entity.Payment;
import com.example.courses.entity.Student;
import com.example.courses.exceptions.custom.EntityNotFoundException;
import com.example.courses.map.PaymentMapper;
import com.example.courses.repo.CourseRepo;
import com.example.courses.repo.PaymentRepo;
import com.example.courses.repo.StudentRepo;
import com.example.courses.specification.PaymentSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {
     private final CourseRepo courseRepo;
     private final StudentRepo studentRepo;
     private final PaymentRepo paymentRepo;
     private final PaymentMapper paymentMapper;

     public void delete(Long id){
          log.info("Удаляем платеж с id={}", id);

          if(!paymentRepo.existsById(id)){
              log.warn("Платеж {} не найден", id);
              throw new EntityNotFoundException("Платеж не найден");
          }

          paymentRepo.deleteById(id);
     }

     public PaymentResponse create(CreatePaymentRequest request){
          log.info("Добавляем оплату для студента с id={}..", request.studentId());

          Payment payment = paymentMapper.toEntity(request);

          Student student = studentRepo.findById(request.studentId()).orElseThrow(() ->{
               log.warn("Студент {} не найден..", request.studentId());
               throw new EntityNotFoundException("Студент не найден");
          });


          Course course = courseRepo.findById(request.courseId())
                  .orElseThrow(() -> {
                       log.warn("Курс не найден при создании платежа: id={}", request.courseId());
                       return new EntityNotFoundException("Курс не найден");
                  });

          payment.setStudent(student);
          payment.setCourse(course);

          Payment saved = paymentRepo.save(payment);

          log.info("Платёж успешно создан: id={}", saved.getId());
          return paymentMapper.toResponse(saved);
     }

     @Transactional(readOnly = true)
     public PaymentResponse get(Long id){
          log.info("Поиск платежа с id={}", id);

          Payment payment = paymentRepo.findById(id)
                  .orElseThrow(() -> {
                       log.warn("Платёж не найден: id={}", id);
                       return new EntityNotFoundException(
                               "Payment not found with id=" + id
                       );
                  });

          return paymentMapper.toResponse(payment);
     }

     @Transactional(readOnly = true)
     public Page<PaymentResponse> findAll(PaymentFilter filter, Pageable pageable) {
          log.debug("Поиск платежей с фильтром");

          Specification<Payment> spec = Specification.allOf(
                  PaymentSpecification.studentIdEquals(filter.studentId()),
                  PaymentSpecification.courseIdEquals(filter.courseId()),
                  PaymentSpecification.amountEquals(filter.amount()),
                  PaymentSpecification.paymentDateEquals(filter.paymentDate())
          );

          Page<PaymentResponse> result = paymentRepo.findAll(spec, pageable)
                  .map(paymentMapper::toResponse);

          log.debug("Найдено платежей: {}", result.getTotalElements());

          return result;
     }

     public PaymentResponse update(Long paymentId, UpdatePayment update) {
          log.info("Обновление платежа с id={}", paymentId);

          Payment payment = paymentRepo.findById(paymentId)
                  .orElseThrow(() -> {
                       log.warn("Платёж не найден для обновления: id={}", paymentId);
                       return new EntityNotFoundException(
                               "Payment not found with id=" + paymentId
                       );
                  });

          paymentMapper.updatePayment(update, payment);

          Payment saved = paymentRepo.save(payment);

          log.info("Платёж успешно обновлён: id={}", saved.getId());
          return paymentMapper.toResponse(saved);
     }
}
