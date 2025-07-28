package com.egabi;

import com.egabi.Controller.CourseController;
import com.egabi.DTO.CourseDTO;
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

class CourseControllerTest {

  @Mock
  private CourseService courseService;

  @InjectMocks
  private CourseController courseController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  @DisplayName("Should return all courses successfully")
  void shouldReturnAllCoursesSuccessfully() {
    List<CourseDTO> expectedCourses = Arrays.asList(
      new CourseDTO(1, "Mathematics", 1),
      new CourseDTO(2, "Physics", 2)
    );
    when(courseService.getAllCourses()).thenReturn(expectedCourses);

    List<CourseDTO> result = courseController.getAllCourses();

    assertEquals(expectedCourses, result);
    verify(courseService, times(1)).getAllCourses();
  }

  @Test
  @DisplayName("Should return empty list when no courses exist")
  void shouldReturnEmptyListWhenNoCoursesExist() {
    when(courseService.getAllCourses()).thenReturn(Arrays.asList());

    List<CourseDTO> result = courseController.getAllCourses();

    assertTrue(result.isEmpty());
    verify(courseService, times(1)).getAllCourses();
  }

  @Test
  @DisplayName("Should return course by ID when exists")
  void shouldReturnCourseByIdWhenExists() {
    Integer courseId = 1;
    CourseDTO expectedCourse = new CourseDTO(courseId, "Mathematics", 1);
    when(courseService.getCourseById(courseId)).thenReturn(Optional.of(expectedCourse));

    Optional<CourseDTO> result = courseController.getCourseById(courseId);

    assertTrue(result.isPresent());
    assertEquals(expectedCourse, result.get());
    verify(courseService, times(1)).getCourseById(courseId);
  }

  @Test
  @DisplayName("Should return empty when course ID does not exist")
  void shouldReturnEmptyWhenCourseIdDoesNotExist() {
    Integer courseId = 999;
    when(courseService.getCourseById(courseId)).thenReturn(Optional.empty());

    Optional<CourseDTO> result = courseController.getCourseById(courseId);

    assertFalse(result.isPresent());
    verify(courseService, times(1)).getCourseById(courseId);
  }

  @Test
  @DisplayName("Should create course successfully")
  void shouldCreateCourseSuccessfully() {
    CourseDTO inputCourse = new CourseDTO(null, "Mathematics", 1);
    CourseDTO createdCourse = new CourseDTO(1, "Mathematics", 1);
    when(courseService.createCourse(inputCourse)).thenReturn(createdCourse);

    CourseDTO result = courseController.createCourse(inputCourse);

    assertEquals(createdCourse, result);
    verify(courseService, times(1)).createCourse(inputCourse);
  }

  @Test
  @DisplayName("Should handle null input in create course")
  void shouldHandleNullInputInCreateCourse() {
    CourseDTO createdCourse = new CourseDTO(1, null, null);
    when(courseService.createCourse(null)).thenReturn(createdCourse);

    CourseDTO result = courseController.createCourse(null);

    assertEquals(createdCourse, result);
    verify(courseService, times(1)).createCourse(null);
  }

  @Test
  @DisplayName("Should update course successfully")
  void shouldUpdateCourseSuccessfully() {
    Integer courseId = 1;
    CourseDTO inputCourse = new CourseDTO(null, "Mathematics Updated", 2);
    CourseDTO updatedCourse = new CourseDTO(courseId, "Mathematics Updated", 2);
    when(courseService.updateCourse(courseId, inputCourse)).thenReturn(updatedCourse);

    CourseDTO result = courseController.updateCourse(courseId, inputCourse);

    assertEquals(updatedCourse, result);
    verify(courseService, times(1)).updateCourse(courseId, inputCourse);
  }

  @Test
  @DisplayName("Should handle null input in update course")
  void shouldHandleNullInputInUpdateCourse() {
    Integer courseId = 1;
    CourseDTO updatedCourse = new CourseDTO(courseId, null, null);
    when(courseService.updateCourse(courseId, null)).thenReturn(updatedCourse);

    CourseDTO result = courseController.updateCourse(courseId, null);

    assertEquals(updatedCourse, result);
    verify(courseService, times(1)).updateCourse(courseId, null);
  }

  @Test
  @DisplayName("Should delete course successfully")
  void shouldDeleteCourseSuccessfully() {
    Integer courseId = 1;
    doNothing().when(courseService).deleteCourse(courseId);

    courseController.deleteCourse(courseId);

    verify(courseService, times(1)).deleteCourse(courseId);
  }

  @Test
  @DisplayName("Should handle multiple operations on same course")
  void shouldHandleMultipleOperationsOnSameCourse() {
    Integer courseId = 1;
    CourseDTO course = new CourseDTO(courseId, "Mathematics", 1);

    when(courseService.getCourseById(courseId)).thenReturn(Optional.of(course));
    when(courseService.updateCourse(courseId, course)).thenReturn(course);
    doNothing().when(courseService).deleteCourse(courseId);

    Optional<CourseDTO> retrieved = courseController.getCourseById(courseId);
    CourseDTO updated = courseController.updateCourse(courseId, course);
    courseController.deleteCourse(courseId);

    assertEquals(course, retrieved.get());
    assertEquals(course, updated);
    verify(courseService, times(1)).getCourseById(courseId);
    verify(courseService, times(1)).updateCourse(courseId, course);
    verify(courseService, times(1)).deleteCourse(courseId);
  }

  @Test
  @DisplayName("Should handle edge case with zero ID")
  void shouldHandleEdgeCaseWithZeroId() {
    Integer courseId = 0;
    CourseDTO course = new CourseDTO(courseId, "Mathematics", 1);
    when(courseService.getCourseById(courseId)).thenReturn(Optional.of(course));

    Optional<CourseDTO> result = courseController.getCourseById(courseId);

    assertEquals(course, result.get());
    verify(courseService, times(1)).getCourseById(courseId);
  }

  @Test
  @DisplayName("Should handle edge case with negative ID")
  void shouldHandleEdgeCaseWithNegativeId() {
    Integer courseId = -1;
    when(courseService.getCourseById(courseId)).thenReturn(Optional.empty());

    Optional<CourseDTO> result = courseController.getCourseById(courseId);

    assertFalse(result.isPresent());
    verify(courseService, times(1)).getCourseById(courseId);
  }

  @Test
  @DisplayName("Should handle empty course name")
  void shouldHandleEmptyCourseName() {
    CourseDTO inputCourse = new CourseDTO(null, "", 1);
    CourseDTO createdCourse = new CourseDTO(1, "", 1);
    when(courseService.createCourse(inputCourse)).thenReturn(createdCourse);

    CourseDTO result = courseController.createCourse(inputCourse);

    assertEquals(createdCourse, result);
    verify(courseService, times(1)).createCourse(inputCourse);
  }

  @Test
  @DisplayName("Should handle zero faculty ID")
  void shouldHandleZeroFacultyId() {
    CourseDTO inputCourse = new CourseDTO(null, "Mathematics", 0);
    CourseDTO createdCourse = new CourseDTO(1, "Mathematics", 0);
    when(courseService.createCourse(inputCourse)).thenReturn(createdCourse);

    CourseDTO result = courseController.createCourse(inputCourse);

    assertEquals(createdCourse, result);
    verify(courseService, times(1)).createCourse(inputCourse);
  }
}
