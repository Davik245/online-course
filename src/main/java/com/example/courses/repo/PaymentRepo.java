package com.example.courses.repo;

import com.example.courses.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PaymentRepo extends JpaRepository<Payment, Long>, JpaSpecificationExecutor<Payment> {
}
