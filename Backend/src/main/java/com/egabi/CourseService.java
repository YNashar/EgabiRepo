package com.egabi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    private CourseDTO toDTO(Course entity) {
        return new CourseDTO(
                entity.getCourseId(),
                entity.getCourseName(),
                entity.getFacultyId()
        );
    }

    private Course toEntity(CourseDTO dto) {
        return new Course(
                dto.getCourseId(),
                dto.getCourseName(),
                dto.getFacultyId()
        );
    }

    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<CourseDTO> getCourseById(Integer id) {
        return courseRepository.findById(id).map(this::toDTO);
    }

    public CourseDTO createCourse(CourseDTO courseDto) {
        Course course = toEntity(courseDto);
        Course saved = courseRepository.save(course);
        return toDTO(saved);
    }

    public CourseDTO updateCourse(Integer id, CourseDTO courseDto) {
        Course course = toEntity(courseDto);
        course.setCourseId(id);
        Course updated = courseRepository.save(course);
        return toDTO(updated);
    }

    public void deleteCourse(Integer id) {
        courseRepository.deleteById(id);
    }
}
