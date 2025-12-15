package com.example.courses.service;

import com.example.courses.map.EnrollmentMapper;
import com.example.courses.repo.EnrollmentRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EnrollmentService {
    private final EnrollmentRepo repo;
    private final EnrollmentMapper mapper;
}
