package com.egabi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class FacultyTest {

  @Test
  @DisplayName("Should create faculty with no args constructor")
  void shouldCreateFacultyWithNoArgsConstructor() {
    Faculty faculty = new Faculty();

    assertNotNull(faculty);
    assertNull(faculty.getFacultyId());
    assertNull(faculty.getFacultyName());
  }

  @Test
  @DisplayName("Should create faculty with all args constructor")
  void shouldCreateFacultyWithAllArgsConstructor() {
    Integer facultyId = 1;
    String facultyName = "Engineering";

    Faculty faculty = new Faculty(facultyId, facultyName);

    assertEquals(facultyId, faculty.getFacultyId());
    assertEquals(facultyName, faculty.getFacultyName());
  }

  @Test
  @DisplayName("Should set and get faculty ID correctly")
  void shouldSetAndGetFacultyIdCorrectly() {
    Faculty faculty = new Faculty();
    Integer facultyId = 1;

    faculty.setFacultyId(facultyId);

    assertEquals(facultyId, faculty.getFacultyId());
  }

  @Test
  @DisplayName("Should set and get faculty name correctly")
  void shouldSetAndGetFacultyNameCorrectly() {
    Faculty faculty = new Faculty();
    String facultyName = "Science";

    faculty.setFacultyName(facultyName);

    assertEquals(facultyName, faculty.getFacultyName());
  }

  @Test
  @DisplayName("Should handle null values")
  void shouldHandleNullValues() {
    Faculty faculty = new Faculty();

    faculty.setFacultyId(null);
    faculty.setFacultyName(null);

    assertNull(faculty.getFacultyId());
    assertNull(faculty.getFacultyName());
  }

  @Test
  @DisplayName("Should handle empty string values")
  void shouldHandleEmptyStringValues() {
    Faculty faculty = new Faculty();
    String emptyString = "";

    faculty.setFacultyName(emptyString);

    assertEquals(emptyString, faculty.getFacultyName());
  }

  @Test
  @DisplayName("Should handle zero values for ID")
  void shouldHandleZeroValuesForId() {
    Faculty faculty = new Faculty();
    Integer zeroValue = 0;

    faculty.setFacultyId(zeroValue);

    assertEquals(zeroValue, faculty.getFacultyId());
  }

  @Test
  @DisplayName("Should handle negative values")
  void shouldHandleNegativeValues() {
    Faculty faculty = new Faculty();
    Integer negativeValue = -1;

    faculty.setFacultyId(negativeValue);

    assertEquals(negativeValue, faculty.getFacultyId());
  }

  @Test
  @DisplayName("Should maintain state after multiple setter calls")
  void shouldMaintainStateAfterMultipleSetterCalls() {
    Faculty faculty = new Faculty();

    faculty.setFacultyName("First");
    faculty.setFacultyName("Second");
    faculty.setFacultyId(1);
    faculty.setFacultyId(2);

    assertEquals("Second", faculty.getFacultyName());
    assertEquals(2, faculty.getFacultyId());
  }

  @Test
  @DisplayName("Should handle special characters in faculty name")
  void shouldHandleSpecialCharactersInFacultyName() {
    Faculty faculty = new Faculty();
    String specialName = "Computer Science & Engineering";

    faculty.setFacultyName(specialName);

    assertEquals(specialName, faculty.getFacultyName());
  }

  @Test
  @DisplayName("Should handle long faculty name")
  void shouldHandleLongFacultyName() {
    Faculty faculty = new Faculty();
    String longName = "A".repeat(100);

    faculty.setFacultyName(longName);

    assertEquals(longName, faculty.getFacultyName());
  }

  @Test
  @DisplayName("Should handle whitespace in faculty name")
  void shouldHandleWhitespaceInFacultyName() {
    Faculty faculty = new Faculty();
    String nameWithSpaces = "  Faculty of Arts and Humanities  ";

    faculty.setFacultyName(nameWithSpaces);

    assertEquals(nameWithSpaces, faculty.getFacultyName());
  }

  @Test
  @DisplayName("Should handle numbers in faculty name")
  void shouldHandleNumbersInFacultyName() {
    Faculty faculty = new Faculty();
    String nameWithNumbers = "Faculty 2023";

    faculty.setFacultyName(nameWithNumbers);

    assertEquals(nameWithNumbers, faculty.getFacultyName());
  }
}
