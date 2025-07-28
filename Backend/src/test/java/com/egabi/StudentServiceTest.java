package com.egabi;

import com.egabi.DTO.StudentDTO;
import com.egabi.Mapper.StudentMapper;
import com.egabi.Repository.StudentRepository;
import com.egabi.Service.StudentService;
import com.egabi.Student;
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

class StudentServiceTest {

  @Mock
  private StudentRepository studentRepository;

  @Mock
  private StudentMapper studentMapper;

  @InjectMocks
  private StudentService studentService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  @DisplayName("Should return all students as DTOs")
  void shouldReturnAllStudentsAsDTOs() {
    List<Student> students = Arrays.asList(
      new Student(1, "John Doe", 1, 2, "1234567890123456"),
      new Student(2, "Jane Smith", 2, 3, "9876543210987654")
    );
    List<StudentDTO> expectedDTOs = Arrays.asList(
      new StudentDTO(1, "John Doe", 1, 2, "1234567890123456"),
      new StudentDTO(2, "Jane Smith", 2, 3, "9876543210987654")
    );

    when(studentRepository.findAll()).thenReturn(students);
    when(studentMapper.studentToStudentDTO(students.get(0))).thenReturn(expectedDTOs.get(0));
    when(studentMapper.studentToStudentDTO(students.get(1))).thenReturn(expectedDTOs.get(1));

    List<StudentDTO> result = studentService.getAllStudents();

    assertEquals(expectedDTOs, result);
    verify(studentRepository, times(1)).findAll();
    verify(studentMapper, times(2)).studentToStudentDTO(any(Student.class));
  }

  @Test
  @DisplayName("Should return empty list when no students exist")
  void shouldReturnEmptyListWhenNoStudentsExist() {
    when(studentRepository.findAll()).thenReturn(Arrays.asList());

    List<StudentDTO> result = studentService.getAllStudents();

    assertTrue(result.isEmpty());
    verify(studentRepository, times(1)).findAll();
    verify(studentMapper, never()).studentToStudentDTO(any());
  }

  @Test
  @DisplayName("Should return student DTO by ID when exists")
  void shouldReturnStudentDTOByIdWhenExists() {
    Integer studentId = 1;
    Student student = new Student(studentId, "John Doe", 1, 2, "1234567890123456");
    StudentDTO expectedDTO = new StudentDTO(studentId, "John Doe", 1, 2, "1234567890123456");

    when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
    when(studentMapper.studentToStudentDTO(student)).thenReturn(expectedDTO);

    StudentDTO result = studentService.getStudentById(studentId);

    assertEquals(expectedDTO, result);
    verify(studentRepository, times(1)).findById(studentId);
    verify(studentMapper, times(1)).studentToStudentDTO(student);
  }

  @Test
  @DisplayName("Should return null when student ID does not exist")
  void shouldReturnNullWhenStudentIdDoesNotExist() {
    Integer studentId = 999;
    when(studentRepository.findById(studentId)).thenReturn(Optional.empty());

    StudentDTO result = studentService.getStudentById(studentId);

    assertNull(result);
    verify(studentRepository, times(1)).findById(studentId);
    verify(studentMapper, never()).studentToStudentDTO(any());
  }

  @Test
  @DisplayName("Should create student successfully")
  void shouldCreateStudentSuccessfully() {
    StudentDTO inputDTO = new StudentDTO(null, "John Doe", 1, 2, "1234567890123456");
    Student studentEntity = new Student(null, "John Doe", 1, 2, "1234567890123456");
    Student savedStudent = new Student(1, "John Doe", 1, 2, "1234567890123456");
    StudentDTO expectedDTO = new StudentDTO(1, "John Doe", 1, 2, "1234567890123456");

    when(studentMapper.studentDTOToStudent(inputDTO)).thenReturn(studentEntity);
    when(studentRepository.save(studentEntity)).thenReturn(savedStudent);
    when(studentMapper.studentToStudentDTO(savedStudent)).thenReturn(expectedDTO);

    StudentDTO result = studentService.createStudent(inputDTO);

    assertEquals(expectedDTO, result);
    verify(studentMapper, times(1)).studentDTOToStudent(inputDTO);
    verify(studentRepository, times(1)).save(studentEntity);
    verify(studentMapper, times(1)).studentToStudentDTO(savedStudent);
  }

  @Test
  @DisplayName("Should update student successfully when exists")
  void shouldUpdateStudentSuccessfullyWhenExists() {
    Integer studentId = 1;
    StudentDTO inputDTO = new StudentDTO(null, "John Doe Updated", 1, 3, "1234567890123456");
    Student existingStudent = new Student(studentId, "John Doe", 1, 2, "1234567890123456");
    Student updatedStudent = new Student(studentId, "John Doe Updated", 1, 3, "1234567890123456");
    StudentDTO expectedDTO = new StudentDTO(studentId, "John Doe Updated", 1, 3, "1234567890123456");

    when(studentRepository.findById(studentId)).thenReturn(Optional.of(existingStudent));
    when(studentMapper.studentDTOToStudent(inputDTO)).thenReturn(updatedStudent);
    when(studentRepository.save(updatedStudent)).thenReturn(updatedStudent);
    when(studentMapper.studentToStudentDTO(updatedStudent)).thenReturn(expectedDTO);

    StudentDTO result = studentService.updateStudent(studentId, inputDTO);

    assertEquals(expectedDTO, result);
    verify(studentRepository, times(1)).findById(studentId);
    verify(studentMapper, times(1)).studentDTOToStudent(inputDTO);
    verify(studentRepository, times(1)).save(updatedStudent);
    verify(studentMapper, times(1)).studentToStudentDTO(updatedStudent);
  }

  @Test
  @DisplayName("Should return null when updating non-existent student")
  void shouldReturnNullWhenUpdatingNonExistentStudent() {
    Integer studentId = 999;
    StudentDTO inputDTO = new StudentDTO(null, "John Doe", 1, 2, "1234567890123456");
    when(studentRepository.findById(studentId)).thenReturn(Optional.empty());

    StudentDTO result = studentService.updateStudent(studentId, inputDTO);

    assertNull(result);
    verify(studentRepository, times(1)).findById(studentId);
    verify(studentMapper, never()).studentDTOToStudent(any());
    verify(studentRepository, never()).save(any());
  }

  @Test
  @DisplayName("Should delete student successfully")
  void shouldDeleteStudentSuccessfully() {
    Integer studentId = 1;
    doNothing().when(studentRepository).deleteById(studentId);

    studentService.deleteStudent(studentId);

    verify(studentRepository, times(1)).deleteById(studentId);
  }

  @Test
  @DisplayName("Should handle null input DTO in create")
  void shouldHandleNullInputDTOInCreate() {
    when(studentMapper.studentDTOToStudent(null)).thenReturn(new Student());
    when(studentRepository.save(any())).thenReturn(new Student());
    when(studentMapper.studentToStudentDTO(any())).thenReturn(new StudentDTO());

    StudentDTO result = studentService.createStudent(null);

    assertNotNull(result);
    verify(studentMapper, times(1)).studentDTOToStudent(null);
    verify(studentRepository, times(1)).save(any());
  }

  @Test
  @DisplayName("Should handle null input DTO in update")
  void shouldHandleNullInputDTOInUpdate() {
    Integer studentId = 1;
    when(studentRepository.findById(studentId)).thenReturn(Optional.of(new Student()));
    when(studentMapper.studentDTOToStudent(null)).thenReturn(new Student());
    when(studentRepository.save(any())).thenReturn(new Student());
    when(studentMapper.studentToStudentDTO(any())).thenReturn(new StudentDTO());

    StudentDTO result = studentService.updateStudent(studentId, null);

    assertNotNull(result);
    verify(studentRepository, times(1)).findById(studentId);
    verify(studentMapper, times(1)).studentDTOToStudent(null);
  }
}
