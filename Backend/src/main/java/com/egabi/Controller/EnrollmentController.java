package com.egabi.Controller;

import com.egabi.Main.Enrollment;
import com.egabi.Service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/enrollments")
@CrossOrigin(origins = "*")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping
    public List<Enrollment> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }

    @GetMapping("/{id}")
    public Optional<Enrollment> getEnrollmentById(@PathVariable Integer id) {
        return enrollmentService.getEnrollmentById(id);
    }

    @PostMapping
    public Enrollment addEnrollment(@RequestBody Enrollment enrollment) {
        return enrollmentService.addEnrollment(enrollment);
    }

    @DeleteMapping("/{id}")
    public void deleteEnrollment(@PathVariable Integer id) {
        enrollmentService.deleteEnrollment(id);
    }

    @PutMapping("/{id}")
    public Enrollment updateEnrollment(@PathVariable Integer id, @RequestBody Enrollment enrollment) {
        return enrollmentService.updateEnrollment(id, enrollment);
    }
}
