package com.egabi;

import com.egabi.Controller.StudentController;
import com.egabi.DTO.StudentDTO;
import com.egabi.Service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentControllerTest {

  @Mock
  private StudentService studentService;

  @InjectMocks
  private StudentController studentController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  @DisplayName("Should return all students successfully")
  void shouldReturnAllStudentsSuccessfully() {
    List<StudentDTO> expectedStudents = Arrays.asList(
      new StudentDTO(1, "John Doe", 1, 2, "1234567890123456"),
      new StudentDTO(2, "Jane Smith", 2, 3, "9876543210987654")
    );
    when(studentService.getAllStudents()).thenReturn(expectedStudents);

    List<StudentDTO> result = studentController.getAllStudents();

    assertEquals(expectedStudents, result);
    verify(studentService, times(1)).getAllStudents();
  }

  @Test
  @DisplayName("Should return empty list when no students exist")
  void shouldReturnEmptyListWhenNoStudentsExist() {
    when(studentService.getAllStudents()).thenReturn(Arrays.asList());

    List<StudentDTO> result = studentController.getAllStudents();

    assertTrue(result.isEmpty());
    verify(studentService, times(1)).getAllStudents();
  }

  @Test
  @DisplayName("Should return student by ID when exists")
  void shouldReturnStudentByIdWhenExists() {
    Integer studentId = 1;
    StudentDTO expectedStudent = new StudentDTO(studentId, "John Doe", 1, 2, "1234567890123456");
    when(studentService.getStudentById(studentId)).thenReturn(expectedStudent);

    StudentDTO result = studentController.getStudentById(studentId);

    assertEquals(expectedStudent, result);
    verify(studentService, times(1)).getStudentById(studentId);
  }

  @Test
  @DisplayName("Should return null when student ID does not exist")
  void shouldReturnNullWhenStudentIdDoesNotExist() {
    Integer studentId = 999;
    when(studentService.getStudentById(studentId)).thenReturn(null);

    StudentDTO result = studentController.getStudentById(studentId);

    assertNull(result);
    verify(studentService, times(1)).getStudentById(studentId);
  }

  @Test
  @DisplayName("Should create student successfully")
  void shouldCreateStudentSuccessfully() {
    StudentDTO inputStudent = new StudentDTO(null, "John Doe", 1, 2, "1234567890123456");
    StudentDTO createdStudent = new StudentDTO(1, "John Doe", 1, 2, "1234567890123456");
    when(studentService.createStudent(inputStudent)).thenReturn(createdStudent);

    StudentDTO result = studentController.createStudent(inputStudent);

    assertEquals(createdStudent, result);
    verify(studentService, times(1)).createStudent(inputStudent);
  }

  @Test
  @DisplayName("Should handle null input in create student")
  void shouldHandleNullInputInCreateStudent() {
    StudentDTO createdStudent = new StudentDTO(1, null, null, null, null);
    when(studentService.createStudent(null)).thenReturn(createdStudent);

    StudentDTO result = studentController.createStudent(null);

    assertEquals(createdStudent, result);
    verify(studentService, times(1)).createStudent(null);
  }

  @Test
  @DisplayName("Should update student successfully")
  void shouldUpdateStudentSuccessfully() {
    Integer studentId = 1;
    StudentDTO inputStudent = new StudentDTO(null, "John Doe Updated", 1, 3, "1234567890123456");
    StudentDTO updatedStudent = new StudentDTO(studentId, "John Doe Updated", 1, 3, "1234567890123456");
    when(studentService.updateStudent(studentId, inputStudent)).thenReturn(updatedStudent);

    StudentDTO result = studentController.updateStudent(studentId, inputStudent);

    assertEquals(updatedStudent, result);
    verify(studentService, times(1)).updateStudent(studentId, inputStudent);
  }

  @Test
  @DisplayName("Should return null when updating non-existent student")
  void shouldReturnNullWhenUpdatingNonExistentStudent() {
    Integer studentId = 999;
    StudentDTO inputStudent = new StudentDTO(null, "John Doe", 1, 2, "1234567890123456");
    when(studentService.updateStudent(studentId, inputStudent)).thenReturn(null);

    StudentDTO result = studentController.updateStudent(studentId, inputStudent);

    assertNull(result);
    verify(studentService, times(1)).updateStudent(studentId, inputStudent);
  }

  @Test
  @DisplayName("Should handle null input in update student")
  void shouldHandleNullInputInUpdateStudent() {
    Integer studentId = 1;
    StudentDTO updatedStudent = new StudentDTO(studentId, null, null, null, null);
    when(studentService.updateStudent(studentId, null)).thenReturn(updatedStudent);

    StudentDTO result = studentController.updateStudent(studentId, null);

    assertEquals(updatedStudent, result);
    verify(studentService, times(1)).updateStudent(studentId, null);
  }

  @Test
  @DisplayName("Should delete student successfully")
  void shouldDeleteStudentSuccessfully() {
    Integer studentId = 1;
    doNothing().when(studentService).deleteStudent(studentId);

    studentController.deleteStudent(studentId);

    verify(studentService, times(1)).deleteStudent(studentId);
  }

  @Test
  @DisplayName("Should handle multiple operations on same student")
  void shouldHandleMultipleOperationsOnSameStudent() {
    Integer studentId = 1;
    StudentDTO student = new StudentDTO(studentId, "John Doe", 1, 2, "1234567890123456");

    when(studentService.getStudentById(studentId)).thenReturn(student);
    when(studentService.updateStudent(studentId, student)).thenReturn(student);
    doNothing().when(studentService).deleteStudent(studentId);

    StudentDTO retrieved = studentController.getStudentById(studentId);
    StudentDTO updated = studentController.updateStudent(studentId, student);
    studentController.deleteStudent(studentId);

    assertEquals(student, retrieved);
    assertEquals(student, updated);
    verify(studentService, times(1)).getStudentById(studentId);
    verify(studentService, times(1)).updateStudent(studentId, student);
    verify(studentService, times(1)).deleteStudent(studentId);
  }

  @Test
  @DisplayName("Should handle edge case with zero ID")
  void shouldHandleEdgeCaseWithZeroId() {
    Integer studentId = 0;
    StudentDTO student = new StudentDTO(studentId, "John Doe", 1, 2, "1234567890123456");
    when(studentService.getStudentById(studentId)).thenReturn(student);

    StudentDTO result = studentController.getStudentById(studentId);

    assertEquals(student, result);
    verify(studentService, times(1)).getStudentById(studentId);
  }

  @Test
  @DisplayName("Should handle edge case with negative ID")
  void shouldHandleEdgeCaseWithNegativeId() {
    Integer studentId = -1;
    when(studentService.getStudentById(studentId)).thenReturn(null);

    StudentDTO result = studentController.getStudentById(studentId);

    assertNull(result);
    verify(studentService, times(1)).getStudentById(studentId);
  }
}
