package com.egabi;

import com.egabi.Controller.FacultyController;
import com.egabi.Course;
import com.egabi.DTO.FacultyDTO;
import com.egabi.Service.FacultyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FacultyControllerTest {

  @Mock
  private FacultyService facultyService;

  @InjectMocks
  private FacultyController facultyController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  @DisplayName("Should return all faculties successfully")
  void shouldReturnAllFacultiesSuccessfully() {
    List<FacultyDTO> expectedFaculties = Arrays.asList(
      new FacultyDTO(1, "Engineering"),
      new FacultyDTO(2, "Science")
    );
    when(facultyService.getAllFaculties()).thenReturn(expectedFaculties);

    ResponseEntity<List<FacultyDTO>> response = facultyController.getAllFaculties();

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(expectedFaculties, response.getBody());
    verify(facultyService, times(1)).getAllFaculties();
  }

  @Test
  @DisplayName("Should return empty list when no faculties exist")
  void shouldReturnEmptyListWhenNoFacultiesExist() {
    when(facultyService.getAllFaculties()).thenReturn(Arrays.asList());

    ResponseEntity<List<FacultyDTO>> response = facultyController.getAllFaculties();

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertTrue(response.getBody().isEmpty());
    verify(facultyService, times(1)).getAllFaculties();
  }

  @Test
  @DisplayName("Should return faculty by ID when exists")
  void shouldReturnFacultyByIdWhenExists() {
    Integer facultyId = 1;
    FacultyDTO expectedFaculty = new FacultyDTO(facultyId, "Engineering");
    when(facultyService.getFacultyById(facultyId)).thenReturn(Optional.of(expectedFaculty));

    ResponseEntity<FacultyDTO> response = facultyController.getFacultyById(facultyId);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(expectedFaculty, response.getBody());
    verify(facultyService, times(1)).getFacultyById(facultyId);
  }

  @Test
  @DisplayName("Should return not found when faculty ID does not exist")
  void shouldReturnNotFoundWhenFacultyIdDoesNotExist() {
    Integer facultyId = 999;
    when(facultyService.getFacultyById(facultyId)).thenReturn(Optional.empty());

    ResponseEntity<FacultyDTO> response = facultyController.getFacultyById(facultyId);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertNull(response.getBody());
    verify(facultyService, times(1)).getFacultyById(facultyId);
  }

  @Test
  @DisplayName("Should create faculty successfully")
  void shouldCreateFacultySuccessfully() {
    FacultyDTO inputFaculty = new FacultyDTO(null, "Engineering");
    FacultyDTO createdFaculty = new FacultyDTO(1, "Engineering");
    when(facultyService.createFaculty(inputFaculty)).thenReturn(createdFaculty);

    ResponseEntity<FacultyDTO> response = facultyController.createFaculty(inputFaculty);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(createdFaculty, response.getBody());
    verify(facultyService, times(1)).createFaculty(inputFaculty);
  }

  @Test
  @DisplayName("Should handle null input in create faculty")
  void shouldHandleNullInputInCreateFaculty() {
    FacultyDTO createdFaculty = new FacultyDTO(1, null);
    when(facultyService.createFaculty(null)).thenReturn(createdFaculty);

    ResponseEntity<FacultyDTO> response = facultyController.createFaculty(null);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(createdFaculty, response.getBody());
    verify(facultyService, times(1)).createFaculty(null);
  }

  @Test
  @DisplayName("Should update faculty successfully")
  void shouldUpdateFacultySuccessfully() {
    Integer facultyId = 1;
    FacultyDTO inputFaculty = new FacultyDTO(null, "Engineering Updated");
    FacultyDTO updatedFaculty = new FacultyDTO(facultyId, "Engineering Updated");
    when(facultyService.updateFaculty(facultyId, inputFaculty)).thenReturn(updatedFaculty);

    ResponseEntity<FacultyDTO> response = facultyController.updateFaculty(facultyId, inputFaculty);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(updatedFaculty, response.getBody());
    verify(facultyService, times(1)).updateFaculty(facultyId, inputFaculty);
  }

  @Test
  @DisplayName("Should handle null input in update faculty")
  void shouldHandleNullInputInUpdateFaculty() {
    Integer facultyId = 1;
    FacultyDTO updatedFaculty = new FacultyDTO(facultyId, null);
    when(facultyService.updateFaculty(facultyId, null)).thenReturn(updatedFaculty);

    ResponseEntity<FacultyDTO> response = facultyController.updateFaculty(facultyId, null);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(updatedFaculty, response.getBody());
    verify(facultyService, times(1)).updateFaculty(facultyId, null);
  }

  @Test
  @DisplayName("Should delete faculty successfully")
  void shouldDeleteFacultySuccessfully() {
    Integer facultyId = 1;
    doNothing().when(facultyService).deleteFaculty(facultyId);

    ResponseEntity<Void> response = facultyController.deleteFaculty(facultyId);

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    assertNull(response.getBody());
    verify(facultyService, times(1)).deleteFaculty(facultyId);
  }

  @Test
  @DisplayName("Should return courses by faculty ID")
  void shouldReturnCoursesByFacultyId() {
    Integer facultyId = 1;
    List<Course> expectedCourses = Arrays.asList(
      new Course(1, "Mathematics", facultyId),
      new Course(2, "Physics", facultyId)
    );
    when(facultyService.getCoursesByFaculty(facultyId)).thenReturn(expectedCourses);

    ResponseEntity<List<Course>> response = facultyController.getCoursesByFaculty(facultyId);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(expectedCourses, response.getBody());
    verify(facultyService, times(1)).getCoursesByFaculty(facultyId);
  }

  @Test
  @DisplayName("Should return empty list when faculty has no courses")
  void shouldReturnEmptyListWhenFacultyHasNoCourses() {
    Integer facultyId = 1;
    when(facultyService.getCoursesByFaculty(facultyId)).thenReturn(Arrays.asList());

    ResponseEntity<List<Course>> response = facultyController.getCoursesByFaculty(facultyId);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertTrue(response.getBody().isEmpty());
    verify(facultyService, times(1)).getCoursesByFaculty(facultyId);
  }

  @Test
  @DisplayName("Should handle multiple operations on same faculty")
  void shouldHandleMultipleOperationsOnSameFaculty() {
    Integer facultyId = 1;
    FacultyDTO faculty = new FacultyDTO(facultyId, "Engineering");
    List<Course> courses = Arrays.asList(new Course(1, "Mathematics", facultyId));

    when(facultyService.getFacultyById(facultyId)).thenReturn(Optional.of(faculty));
    when(facultyService.updateFaculty(facultyId, faculty)).thenReturn(faculty);
    when(facultyService.getCoursesByFaculty(facultyId)).thenReturn(courses);
    doNothing().when(facultyService).deleteFaculty(facultyId);

    ResponseEntity<FacultyDTO> retrieved = facultyController.getFacultyById(facultyId);
    ResponseEntity<FacultyDTO> updated = facultyController.updateFaculty(facultyId, faculty);
    ResponseEntity<List<Course>> coursesResponse = facultyController.getCoursesByFaculty(facultyId);
    ResponseEntity<Void> deleteResponse = facultyController.deleteFaculty(facultyId);

    assertEquals(faculty, retrieved.getBody());
    assertEquals(faculty, updated.getBody());
    assertEquals(courses, coursesResponse.getBody());
    assertEquals(HttpStatus.NO_CONTENT, deleteResponse.getStatusCode());

    verify(facultyService, times(1)).getFacultyById(facultyId);
    verify(facultyService, times(1)).updateFaculty(facultyId, faculty);
    verify(facultyService, times(1)).getCoursesByFaculty(facultyId);
    verify(facultyService, times(1)).deleteFaculty(facultyId);
  }

  @Test
  @DisplayName("Should handle edge case with zero ID")
  void shouldHandleEdgeCaseWithZeroId() {
    Integer facultyId = 0;
    FacultyDTO faculty = new FacultyDTO(facultyId, "Engineering");
    when(facultyService.getFacultyById(facultyId)).thenReturn(Optional.of(faculty));

    ResponseEntity<FacultyDTO> response = facultyController.getFacultyById(facultyId);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(faculty, response.getBody());
    verify(facultyService, times(1)).getFacultyById(facultyId);
  }

  @Test
  @DisplayName("Should handle edge case with negative ID")
  void shouldHandleEdgeCaseWithNegativeId() {
    Integer facultyId = -1;
    when(facultyService.getFacultyById(facultyId)).thenReturn(Optional.empty());

    ResponseEntity<FacultyDTO> response = facultyController.getFacultyById(facultyId);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertNull(response.getBody());
    verify(facultyService, times(1)).getFacultyById(facultyId);
  }

  @Test
  @DisplayName("Should handle empty faculty name")
  void shouldHandleEmptyFacultyName() {
    FacultyDTO inputFaculty = new FacultyDTO(null, "");
    FacultyDTO createdFaculty = new FacultyDTO(1, "");
    when(facultyService.createFaculty(inputFaculty)).thenReturn(createdFaculty);

    ResponseEntity<FacultyDTO> response = facultyController.createFaculty(inputFaculty);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(createdFaculty, response.getBody());
    verify(facultyService, times(1)).createFaculty(inputFaculty);
  }

  @Test
  @DisplayName("Should handle zero faculty ID for courses")
  void shouldHandleZeroFacultyIdForCourses() {
    Integer facultyId = 0;
    when(facultyService.getCoursesByFaculty(facultyId)).thenReturn(Arrays.asList());

    ResponseEntity<List<Course>> response = facultyController.getCoursesByFaculty(facultyId);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertTrue(response.getBody().isEmpty());
    verify(facultyService, times(1)).getCoursesByFaculty(facultyId);
  }
}
