package com.egabi.Repository;

import com.egabi.Main.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findByFacultyId(Integer facultyId);
    boolean existsByCourseNameAndFacultyId(String courseName, Integer facultyId);
}