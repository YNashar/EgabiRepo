package com.egabi;

import com.egabi.Course;
import com.egabi.DTO.CourseDTO;
import com.egabi.Mapper.CourseMapper;
import com.egabi.Repository.CourseRepository;
import com.egabi.Service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CourseServiceTest {

  @Mock
  private CourseRepository courseRepository;

  @Mock
  private CourseMapper courseMapper;

  @InjectMocks
  private CourseService courseService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  @DisplayName("Should return all courses as DTOs")
  void shouldReturnAllCoursesAsDTOs() {
    List<Course> courses = Arrays.asList(
      new Course(1, "Mathematics", 1),
      new Course(2, "Physics", 2)
    );
    List<CourseDTO> expectedDTOs = Arrays.asList(
      new CourseDTO(1, "Mathematics", 1),
      new CourseDTO(2, "Physics", 2)
    );

    when(courseRepository.findAll()).thenReturn(courses);
    when(courseMapper.courseToCourseDTO(courses.get(0))).thenReturn(expectedDTOs.get(0));
    when(courseMapper.courseToCourseDTO(courses.get(1))).thenReturn(expectedDTOs.get(1));

    List<CourseDTO> result = courseService.getAllCourses();

    assertEquals(expectedDTOs, result);
    verify(courseRepository, times(1)).findAll();
    verify(courseMapper, times(2)).courseToCourseDTO(any(Course.class));
  }

  @Test
  @DisplayName("Should return empty list when no courses exist")
  void shouldReturnEmptyListWhenNoCoursesExist() {
    when(courseRepository.findAll()).thenReturn(Arrays.asList());

    List<CourseDTO> result = courseService.getAllCourses();

    assertTrue(result.isEmpty());
    verify(courseRepository, times(1)).findAll();
    verify(courseMapper, never()).courseToCourseDTO(any());
  }

  @Test
  @DisplayName("Should return course DTO by ID when exists")
  void shouldReturnCourseDTOByIdWhenExists() {
    Integer courseId = 1;
    Course course = new Course(courseId, "Mathematics", 1);
    CourseDTO expectedDTO = new CourseDTO(courseId, "Mathematics", 1);

    when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
    when(courseMapper.courseToCourseDTO(course)).thenReturn(expectedDTO);

    Optional<CourseDTO> result = courseService.getCourseById(courseId);

    assertTrue(result.isPresent());
    assertEquals(expectedDTO, result.get());
    verify(courseRepository, times(1)).findById(courseId);
    verify(courseMapper, times(1)).courseToCourseDTO(course);
  }

  @Test
  @DisplayName("Should return empty when course ID does not exist")
  void shouldReturnEmptyWhenCourseIdDoesNotExist() {
    Integer courseId = 999;
    when(courseRepository.findById(courseId)).thenReturn(Optional.empty());

    Optional<CourseDTO> result = courseService.getCourseById(courseId);

    assertFalse(result.isPresent());
    verify(courseRepository, times(1)).findById(courseId);
    verify(courseMapper, never()).courseToCourseDTO(any());
  }

  @Test
  @DisplayName("Should create course successfully")
  void shouldCreateCourseSuccessfully() {
    CourseDTO inputDTO = new CourseDTO(null, "Mathematics", 1);
    Course courseEntity = new Course(null, "Mathematics", 1);
    Course savedCourse = new Course(1, "Mathematics", 1);
    CourseDTO expectedDTO = new CourseDTO(1, "Mathematics", 1);

    when(courseMapper.courseDTOToCourse(inputDTO)).thenReturn(courseEntity);
    when(courseRepository.save(courseEntity)).thenReturn(savedCourse);
    when(courseMapper.courseToCourseDTO(savedCourse)).thenReturn(expectedDTO);

    CourseDTO result = courseService.createCourse(inputDTO);

    assertEquals(expectedDTO, result);
    verify(courseMapper, times(1)).courseDTOToCourse(inputDTO);
    verify(courseRepository, times(1)).save(courseEntity);
    verify(courseMapper, times(1)).courseToCourseDTO(savedCourse);
  }

  @Test
  @DisplayName("Should update course successfully")
  void shouldUpdateCourseSuccessfully() {
    Integer courseId = 1;
    CourseDTO inputDTO = new CourseDTO(null, "Mathematics Updated", 2);
    Course courseEntity = new Course(courseId, "Mathematics Updated", 2);
    Course updatedCourse = new Course(courseId, "Mathematics Updated", 2);
    CourseDTO expectedDTO = new CourseDTO(courseId, "Mathematics Updated", 2);

    when(courseMapper.courseDTOToCourse(inputDTO)).thenReturn(courseEntity);
    when(courseRepository.save(courseEntity)).thenReturn(updatedCourse);
    when(courseMapper.courseToCourseDTO(updatedCourse)).thenReturn(expectedDTO);

    CourseDTO result = courseService.updateCourse(courseId, inputDTO);

    assertEquals(expectedDTO, result);
    verify(courseMapper, times(1)).courseDTOToCourse(inputDTO);
    verify(courseRepository, times(1)).save(courseEntity);
    verify(courseMapper, times(1)).courseToCourseDTO(updatedCourse);
  }

  @Test
  @DisplayName("Should delete course successfully")
  void shouldDeleteCourseSuccessfully() {
    Integer courseId = 1;
    doNothing().when(courseRepository).deleteById(courseId);

    courseService.deleteCourse(courseId);

    verify(courseRepository, times(1)).deleteById(courseId);
  }

  @Test
  @DisplayName("Should handle null input DTO in create")
  void shouldHandleNullInputDTOInCreate() {
    when(courseMapper.courseDTOToCourse(null)).thenReturn(new Course());
    when(courseRepository.save(any())).thenReturn(new Course());
    when(courseMapper.courseToCourseDTO(any())).thenReturn(new CourseDTO());

    CourseDTO result = courseService.createCourse(null);

    assertNotNull(result);
    verify(courseMapper, times(1)).courseDTOToCourse(null);
    verify(courseRepository, times(1)).save(any());
  }

  @Test
  @DisplayName("Should handle null input DTO in update")
  void shouldHandleNullInputDTOInUpdate() {
    Integer courseId = 1;
    when(courseMapper.courseDTOToCourse(null)).thenReturn(new Course());
    when(courseRepository.save(any())).thenReturn(new Course());
    when(courseMapper.courseToCourseDTO(any())).thenReturn(new CourseDTO());

    CourseDTO result = courseService.updateCourse(courseId, null);

    assertNotNull(result);
    verify(courseMapper, times(1)).courseDTOToCourse(null);
    verify(courseRepository, times(1)).save(any());
  }

  @Test
  @DisplayName("Should handle empty course name")
  void shouldHandleEmptyCourseName() {
    CourseDTO inputDTO = new CourseDTO(null, "", 1);
    Course courseEntity = new Course(null, "", 1);
    Course savedCourse = new Course(1, "", 1);
    CourseDTO expectedDTO = new CourseDTO(1, "", 1);

    when(courseMapper.courseDTOToCourse(inputDTO)).thenReturn(courseEntity);
    when(courseRepository.save(courseEntity)).thenReturn(savedCourse);
    when(courseMapper.courseToCourseDTO(savedCourse)).thenReturn(expectedDTO);

    CourseDTO result = courseService.createCourse(inputDTO);

    assertEquals(expectedDTO, result);
    verify(courseMapper, times(1)).courseDTOToCourse(inputDTO);
  }

  @Test
  @DisplayName("Should handle zero faculty ID")
  void shouldHandleZeroFacultyId() {
    CourseDTO inputDTO = new CourseDTO(null, "Mathematics", 0);
    Course courseEntity = new Course(null, "Mathematics", 0);
    Course savedCourse = new Course(1, "Mathematics", 0);
    CourseDTO expectedDTO = new CourseDTO(1, "Mathematics", 0);

    when(courseMapper.courseDTOToCourse(inputDTO)).thenReturn(courseEntity);
    when(courseRepository.save(courseEntity)).thenReturn(savedCourse);
    when(courseMapper.courseToCourseDTO(savedCourse)).thenReturn(expectedDTO);

    CourseDTO result = courseService.createCourse(inputDTO);

    assertEquals(expectedDTO, result);
    verify(courseMapper, times(1)).courseDTOToCourse(inputDTO);
  }

  @Test
  @DisplayName("Should handle negative faculty ID")
  void shouldHandleNegativeFacultyId() {
    CourseDTO inputDTO = new CourseDTO(null, "Mathematics", -1);
    Course courseEntity = new Course(null, "Mathematics", -1);
    Course savedCourse = new Course(1, "Mathematics", -1);
    CourseDTO expectedDTO = new CourseDTO(1, "Mathematics", -1);

    when(courseMapper.courseDTOToCourse(inputDTO)).thenReturn(courseEntity);
    when(courseRepository.save(courseEntity)).thenReturn(savedCourse);
    when(courseMapper.courseToCourseDTO(savedCourse)).thenReturn(expectedDTO);

    CourseDTO result = courseService.createCourse(inputDTO);

    assertEquals(expectedDTO, result);
    verify(courseMapper, times(1)).courseDTOToCourse(inputDTO);
  }
}
