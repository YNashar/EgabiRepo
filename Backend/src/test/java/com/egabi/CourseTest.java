package com.egabi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

  @Test
  @DisplayName("Should create course with no args constructor")
  void shouldCreateCourseWithNoArgsConstructor() {
    Course course = new Course();

    assertNotNull(course);
    assertNull(course.getCourseId());
    assertNull(course.getCourseName());
    assertNull(course.getFacultyId());
  }

  @Test
  @DisplayName("Should create course with all args constructor")
  void shouldCreateCourseWithAllArgsConstructor() {
    Integer courseId = 1;
    String courseName = "Mathematics";
    Integer facultyId = 2;

    Course course = new Course(courseId, courseName, facultyId);

    assertEquals(courseId, course.getCourseId());
    assertEquals(courseName, course.getCourseName());
    assertEquals(facultyId, course.getFacultyId());
  }

  @Test
  @DisplayName("Should set and get course ID correctly")
  void shouldSetAndGetCourseIdCorrectly() {
    Course course = new Course();
    Integer courseId = 1;

    course.setCourseId(courseId);

    assertEquals(courseId, course.getCourseId());
  }

  @Test
  @DisplayName("Should set and get course name correctly")
  void shouldSetAndGetCourseNameCorrectly() {
    Course course = new Course();
    String courseName = "Physics";

    course.setCourseName(courseName);

    assertEquals(courseName, course.getCourseName());
  }

  @Test
  @DisplayName("Should set and get faculty ID correctly")
  void shouldSetAndGetFacultyIdCorrectly() {
    Course course = new Course();
    Integer facultyId = 3;

    course.setFacultyId(facultyId);

    assertEquals(facultyId, course.getFacultyId());
  }

  @Test
  @DisplayName("Should handle null values")
  void shouldHandleNullValues() {
    Course course = new Course();

    course.setCourseId(null);
    course.setCourseName(null);
    course.setFacultyId(null);

    assertNull(course.getCourseId());
    assertNull(course.getCourseName());
    assertNull(course.getFacultyId());
  }

  @Test
  @DisplayName("Should handle empty string values")
  void shouldHandleEmptyStringValues() {
    Course course = new Course();
    String emptyString = "";

    course.setCourseName(emptyString);

    assertEquals(emptyString, course.getCourseName());
  }

  @Test
  @DisplayName("Should handle zero values for IDs")
  void shouldHandleZeroValuesForIds() {
    Course course = new Course();
    Integer zeroValue = 0;

    course.setCourseId(zeroValue);
    course.setFacultyId(zeroValue);

    assertEquals(zeroValue, course.getCourseId());
    assertEquals(zeroValue, course.getFacultyId());
  }

  @Test
  @DisplayName("Should handle negative values")
  void shouldHandleNegativeValues() {
    Course course = new Course();
    Integer negativeValue = -1;

    course.setCourseId(negativeValue);
    course.setFacultyId(negativeValue);

    assertEquals(negativeValue, course.getCourseId());
    assertEquals(negativeValue, course.getFacultyId());
  }

  @Test
  @DisplayName("Should maintain state after multiple setter calls")
  void shouldMaintainStateAfterMultipleSetterCalls() {
    Course course = new Course();

    course.setCourseName("First");
    course.setCourseName("Second");
    course.setFacultyId(1);
    course.setFacultyId(2);

    assertEquals("Second", course.getCourseName());
    assertEquals(2, course.getFacultyId());
  }

  @Test
  @DisplayName("Should handle special characters in course name")
  void shouldHandleSpecialCharactersInCourseName() {
    Course course = new Course();
    String specialName = "Advanced Mathematics & Physics 101";

    course.setCourseName(specialName);

    assertEquals(specialName, course.getCourseName());
  }

  @Test
  @DisplayName("Should handle long course name")
  void shouldHandleLongCourseName() {
    Course course = new Course();
    String longName = "A".repeat(100);

    course.setCourseName(longName);

    assertEquals(longName, course.getCourseName());
  }

  @Test
  @DisplayName("Should handle whitespace in course name")
  void shouldHandleWhitespaceInCourseName() {
    Course course = new Course();
    String nameWithSpaces = "  Introduction to Computer Science  ";

    course.setCourseName(nameWithSpaces);

    assertEquals(nameWithSpaces, course.getCourseName());
  }
}
