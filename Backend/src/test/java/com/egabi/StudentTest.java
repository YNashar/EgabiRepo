package com.egabi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

  @Test
  @DisplayName("Should create student with no args constructor")
  void shouldCreateStudentWithNoArgsConstructor() {
    Student student = new Student();

    assertNotNull(student);
    assertNull(student.getStudentId());
    assertNull(student.getStudentName());
    assertNull(student.getFacultyId());
    assertNull(student.getLevel());
    assertNull(student.getNationalId());
  }

  @Test
  @DisplayName("Should create student with all args constructor")
  void shouldCreateStudentWithAllArgsConstructor() {
    Integer studentId = 1;
    String studentName = "John Doe";
    Integer facultyId = 2;
    Integer level = 3;
    String nationalId = "1234567890123456";

    Student student = new Student(studentId, studentName, facultyId, level, nationalId);

    assertEquals(studentId, student.getStudentId());
    assertEquals(studentName, student.getStudentName());
    assertEquals(facultyId, student.getFacultyId());
    assertEquals(level, student.getLevel());
    assertEquals(nationalId, student.getNationalId());
  }

  @Test
  @DisplayName("Should set and get student ID correctly")
  void shouldSetAndGetStudentIdCorrectly() {
    Student student = new Student();
    Integer studentId = 1;

    student.setStudentId(studentId);

    assertEquals(studentId, student.getStudentId());
  }

  @Test
  @DisplayName("Should set and get student name correctly")
  void shouldSetAndGetStudentNameCorrectly() {
    Student student = new Student();
    String studentName = "Jane Smith";

    student.setStudentName(studentName);

    assertEquals(studentName, student.getStudentName());
  }

  @Test
  @DisplayName("Should set and get faculty ID correctly")
  void shouldSetAndGetFacultyIdCorrectly() {
    Student student = new Student();
    Integer facultyId = 3;

    student.setFacultyId(facultyId);

    assertEquals(facultyId, student.getFacultyId());
  }

  @Test
  @DisplayName("Should set and get level correctly")
  void shouldSetAndGetLevelCorrectly() {
    Student student = new Student();
    Integer level = 4;

    student.setLevel(level);

    assertEquals(level, student.getLevel());
  }

  @Test
  @DisplayName("Should set and get national ID correctly")
  void shouldSetAndGetNationalIdCorrectly() {
    Student student = new Student();
    String nationalId = "9876543210987654";

    student.setNationalId(nationalId);

    assertEquals(nationalId, student.getNationalId());
  }

  @Test
  @DisplayName("Should handle null values")
  void shouldHandleNullValues() {
    Student student = new Student();

    student.setStudentId(null);
    student.setStudentName(null);
    student.setFacultyId(null);
    student.setLevel(null);
    student.setNationalId(null);

    assertNull(student.getStudentId());
    assertNull(student.getStudentName());
    assertNull(student.getFacultyId());
    assertNull(student.getLevel());
    assertNull(student.getNationalId());
  }

  @Test
  @DisplayName("Should handle empty string values")
  void shouldHandleEmptyStringValues() {
    Student student = new Student();
    String emptyString = "";

    student.setStudentName(emptyString);
    student.setNationalId(emptyString);

    assertEquals(emptyString, student.getStudentName());
    assertEquals(emptyString, student.getNationalId());
  }

  @Test
  @DisplayName("Should handle zero values for IDs")
  void shouldHandleZeroValuesForIds() {
    Student student = new Student();
    Integer zeroValue = 0;

    student.setStudentId(zeroValue);
    student.setFacultyId(zeroValue);
    student.setLevel(zeroValue);

    assertEquals(zeroValue, student.getStudentId());
    assertEquals(zeroValue, student.getFacultyId());
    assertEquals(zeroValue, student.getLevel());
  }

  @Test
  @DisplayName("Should handle negative values")
  void shouldHandleNegativeValues() {
    Student student = new Student();
    Integer negativeValue = -1;

    student.setStudentId(negativeValue);
    student.setFacultyId(negativeValue);
    student.setLevel(negativeValue);

    assertEquals(negativeValue, student.getStudentId());
    assertEquals(negativeValue, student.getFacultyId());
    assertEquals(negativeValue, student.getLevel());
  }

  @Test
  @DisplayName("Should maintain state after multiple setter calls")
  void shouldMaintainStateAfterMultipleSetterCalls() {
    Student student = new Student();

    student.setStudentName("First");
    student.setStudentName("Second");
    student.setFacultyId(1);
    student.setFacultyId(2);
    student.setLevel(1);
    student.setLevel(3);

    assertEquals("Second", student.getStudentName());
    assertEquals(2, student.getFacultyId());
    assertEquals(3, student.getLevel());
  }
}
