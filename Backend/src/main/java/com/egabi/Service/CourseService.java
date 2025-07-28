package com.egabi.Service;

import com.egabi.DTO.CourseDTO;
import com.egabi.Course;
import com.egabi.Repository.CourseRepository;
import com.egabi.Mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(CourseMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<CourseDTO> getCourseById(Integer id) {
        return courseRepository.findById(id).map(CourseMapper::toDTO);
    }

    public CourseDTO createCourse(CourseDTO courseDto) {
        Course course = CourseMapper.toEntity(courseDto);
        Course saved = courseRepository.save(course);
        return CourseMapper.toDTO(saved);
    }

    public CourseDTO updateCourse(Integer id, CourseDTO courseDto) {
        Course course = CourseMapper.toEntity(courseDto);
        course.setCourseId(id);
        Course updated = courseRepository.save(course);
        return CourseMapper.toDTO(updated);
    }

    public void deleteCourse(Integer id) {
        courseRepository.deleteById(id);
    }
}