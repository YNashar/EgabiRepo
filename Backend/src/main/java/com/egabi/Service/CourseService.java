package com.egabi.Service;

import com.egabi.Course;
import com.egabi.Repository.CourseRepository;
import com.egabi.DTO.CourseDTO;
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

  @Autowired
  private CourseMapper courseMapper;

  public List<CourseDTO> getAllCourses() {
    return courseRepository.findAll().stream()
      .map(courseMapper::courseToCourseDTO)
      .collect(Collectors.toList());
  }

  public Optional<CourseDTO> getCourseById(Integer id) {
    return courseRepository.findById(id).map(courseMapper::courseToCourseDTO);
  }

  public CourseDTO createCourse(CourseDTO courseDto) {
    Course course = courseMapper.courseDTOToCourse(courseDto);
    Course saved = courseRepository.save(course);
    return courseMapper.courseToCourseDTO(saved);
  }

  public CourseDTO updateCourse(Integer id, CourseDTO courseDto) {
    Course course = courseMapper.courseDTOToCourse(courseDto);
    course.setCourseId(id);
    Course updated = courseRepository.save(course);
    return courseMapper.courseToCourseDTO(updated);
  }

  public void deleteCourse(Integer id) {
    courseRepository.deleteById(id);
  }
}
