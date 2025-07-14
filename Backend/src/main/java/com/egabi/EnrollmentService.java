package com.egabi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public Optional<Enrollment> getEnrollmentById(Integer id) {
        return enrollmentRepository.findById(id);
    }

    public Enrollment addEnrollment(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    public void deleteEnrollment(Integer id) {
        enrollmentRepository.deleteById(id);
    }

    public Enrollment updateEnrollment(Integer id, Enrollment enrollment) {
        enrollment.setEnrollmentId(id);
        return enrollmentRepository.save(enrollment);
    }
}
