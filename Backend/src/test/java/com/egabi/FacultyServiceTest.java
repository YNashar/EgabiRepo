package com.egabi;

import com.egabi.Faculty;
import com.egabi.Course;
import com.egabi.DTO.FacultyDTO;
import com.egabi.Mapper.FacultyMapper;
import com.egabi.Repository.FacultyRepository;
import com.egabi.Repository.CourseRepository;
import com.egabi.Service.FacultyService;
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

class FacultyServiceTest {

  @Mock
  private FacultyRepository facultyRepository;

  @Mock
  private CourseRepository courseRepository;

  @Mock
  private FacultyMapper facultyMapper;

  @InjectMocks
  private FacultyService facultyService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  @DisplayName("Should return all faculties as DTOs")
  void shouldReturnAllFacultiesAsDTOs() {
    List<Faculty> faculties = Arrays.asList(
      new Faculty(1, "Engineering"),
      new Faculty(2, "Science")
    );
    List<FacultyDTO> expectedDTOs = Arrays.asList(
      new FacultyDTO(1, "Engineering"),
      new FacultyDTO(2, "Science")
    );

    when(facultyRepository.findAll()).thenReturn(faculties);
    when(facultyMapper.facultyToFacultyDTO(faculties.get(0))).thenReturn(expectedDTOs.get(0));
    when(facultyMapper.facultyToFacultyDTO(faculties.get(1))).thenReturn(expectedDTOs.get(1));

    List<FacultyDTO> result = facultyService.getAllFaculties();

    assertEquals(expectedDTOs, result);
    verify(facultyRepository, times(1)).findAll();
    verify(facultyMapper, times(2)).facultyToFacultyDTO(any(Faculty.class));
  }

  @Test
  @DisplayName("Should return empty list when no faculties exist")
  void shouldReturnEmptyListWhenNoFacultiesExist() {
    when(facultyRepository.findAll()).thenReturn(Arrays.asList());

    List<FacultyDTO> result = facultyService.getAllFaculties();

    assertTrue(result.isEmpty());
    verify(facultyRepository, times(1)).findAll();
    verify(facultyMapper, never()).facultyToFacultyDTO(any());
  }

  @Test
  @DisplayName("Should return faculty DTO by ID when exists")
  void shouldReturnFacultyDTOByIdWhenExists() {
    Integer facultyId = 1;
    Faculty faculty = new Faculty(facultyId, "Engineering");
    FacultyDTO expectedDTO = new FacultyDTO(facultyId, "Engineering");

    when(facultyRepository.findById(facultyId)).thenReturn(Optional.of(faculty));
    when(facultyMapper.facultyToFacultyDTO(faculty)).thenReturn(expectedDTO);

    Optional<FacultyDTO> result = facultyService.getFacultyById(facultyId);

    assertTrue(result.isPresent());
    assertEquals(expectedDTO, result.get());
    verify(facultyRepository, times(1)).findById(facultyId);
    verify(facultyMapper, times(1)).facultyToFacultyDTO(faculty);
  }

  @Test
  @DisplayName("Should return empty when faculty ID does not exist")
  void shouldReturnEmptyWhenFacultyIdDoesNotExist() {
    Integer facultyId = 999;
    when(facultyRepository.findById(facultyId)).thenReturn(Optional.empty());

    Optional<FacultyDTO> result = facultyService.getFacultyById(facultyId);

    assertFalse(result.isPresent());
    verify(facultyRepository, times(1)).findById(facultyId);
    verify(facultyMapper, never()).facultyToFacultyDTO(any());
  }

  @Test
  @DisplayName("Should create faculty successfully")
  void shouldCreateFacultySuccessfully() {
    FacultyDTO inputDTO = new FacultyDTO(null, "Engineering");
    Faculty facultyEntity = new Faculty(null, "Engineering");
    Faculty savedFaculty = new Faculty(1, "Engineering");
    FacultyDTO expectedDTO = new FacultyDTO(1, "Engineering");

    when(facultyMapper.facultyDTOToFaculty(inputDTO)).thenReturn(facultyEntity);
    when(facultyRepository.save(facultyEntity)).thenReturn(savedFaculty);
    when(facultyMapper.facultyToFacultyDTO(savedFaculty)).thenReturn(expectedDTO);

    FacultyDTO result = facultyService.createFaculty(inputDTO);

    assertEquals(expectedDTO, result);
    verify(facultyMapper, times(1)).facultyDTOToFaculty(inputDTO);
    verify(facultyRepository, times(1)).save(facultyEntity);
    verify(facultyMapper, times(1)).facultyToFacultyDTO(savedFaculty);
  }

  @Test
  @DisplayName("Should update faculty successfully")
  void shouldUpdateFacultySuccessfully() {
    Integer facultyId = 1;
    FacultyDTO inputDTO = new FacultyDTO(null, "Engineering Updated");
    Faculty facultyEntity = new Faculty(facultyId, "Engineering Updated");
    Faculty updatedFaculty = new Faculty(facultyId, "Engineering Updated");
    FacultyDTO expectedDTO = new FacultyDTO(facultyId, "Engineering Updated");

    when(facultyMapper.facultyDTOToFaculty(inputDTO)).thenReturn(facultyEntity);
    when(facultyRepository.save(facultyEntity)).thenReturn(updatedFaculty);
    when(facultyMapper.facultyToFacultyDTO(updatedFaculty)).thenReturn(expectedDTO);

    FacultyDTO result = facultyService.updateFaculty(facultyId, inputDTO);

    assertEquals(expectedDTO, result);
    verify(facultyMapper, times(1)).facultyDTOToFaculty(inputDTO);
    verify(facultyRepository, times(1)).save(facultyEntity);
    verify(facultyMapper, times(1)).facultyToFacultyDTO(updatedFaculty);
  }

  @Test
  @DisplayName("Should delete faculty successfully")
  void shouldDeleteFacultySuccessfully() {
    Integer facultyId = 1;
    doNothing().when(facultyRepository).deleteById(facultyId);

    facultyService.deleteFaculty(facultyId);

    verify(facultyRepository, times(1)).deleteById(facultyId);
  }

  @Test
  @DisplayName("Should return courses by faculty ID")
  void shouldReturnCoursesByFacultyId() {
    Integer facultyId = 1;
    List<Course> expectedCourses = Arrays.asList(
      new Course(1, "Mathematics", facultyId),
      new Course(2, "Physics", facultyId)
    );

    when(courseRepository.findByFacultyId(facultyId)).thenReturn(expectedCourses);

    List<Course> result = facultyService.getCoursesByFaculty(facultyId);

    assertEquals(expectedCourses, result);
    verify(courseRepository, times(1)).findByFacultyId(facultyId);
  }

  @Test
  @DisplayName("Should return empty list when faculty has no courses")
  void shouldReturnEmptyListWhenFacultyHasNoCourses() {
    Integer facultyId = 1;
    when(courseRepository.findByFacultyId(facultyId)).thenReturn(Arrays.asList());

    List<Course> result = facultyService.getCoursesByFaculty(facultyId);

    assertTrue(result.isEmpty());
    verify(courseRepository, times(1)).findByFacultyId(facultyId);
  }

  @Test
  @DisplayName("Should handle null input DTO in create")
  void shouldHandleNullInputDTOInCreate() {
    when(facultyMapper.facultyDTOToFaculty(null)).thenReturn(new Faculty());
    when(facultyRepository.save(any())).thenReturn(new Faculty());
    when(facultyMapper.facultyToFacultyDTO(any())).thenReturn(new FacultyDTO());

    FacultyDTO result = facultyService.createFaculty(null);

    assertNotNull(result);
    verify(facultyMapper, times(1)).facultyDTOToFaculty(null);
    verify(facultyRepository, times(1)).save(any());
  }

  @Test
  @DisplayName("Should handle null input DTO in update")
  void shouldHandleNullInputDTOInUpdate() {
    Integer facultyId = 1;
    when(facultyMapper.facultyDTOToFaculty(null)).thenReturn(new Faculty());
    when(facultyRepository.save(any())).thenReturn(new Faculty());
    when(facultyMapper.facultyToFacultyDTO(any())).thenReturn(new FacultyDTO());

    FacultyDTO result = facultyService.updateFaculty(facultyId, null);

    assertNotNull(result);
    verify(facultyMapper, times(1)).facultyDTOToFaculty(null);
    verify(facultyRepository, times(1)).save(any());
  }

  @Test
  @DisplayName("Should handle empty faculty name")
  void shouldHandleEmptyFacultyName() {
    FacultyDTO inputDTO = new FacultyDTO(null, "");
    Faculty facultyEntity = new Faculty(null, "");
    Faculty savedFaculty = new Faculty(1, "");
    FacultyDTO expectedDTO = new FacultyDTO(1, "");

    when(facultyMapper.facultyDTOToFaculty(inputDTO)).thenReturn(facultyEntity);
    when(facultyRepository.save(facultyEntity)).thenReturn(savedFaculty);
    when(facultyMapper.facultyToFacultyDTO(savedFaculty)).thenReturn(expectedDTO);

    FacultyDTO result = facultyService.createFaculty(inputDTO);

    assertEquals(expectedDTO, result);
    verify(facultyMapper, times(1)).facultyDTOToFaculty(inputDTO);
  }

  @Test
  @DisplayName("Should handle zero faculty ID")
  void shouldHandleZeroFacultyId() {
    Integer facultyId = 0;
    when(courseRepository.findByFacultyId(facultyId)).thenReturn(Arrays.asList());

    List<Course> result = facultyService.getCoursesByFaculty(facultyId);

    assertTrue(result.isEmpty());
    verify(courseRepository, times(1)).findByFacultyId(facultyId);
  }

  @Test
  @DisplayName("Should handle negative faculty ID")
  void shouldHandleNegativeFacultyId() {
    Integer facultyId = -1;
    when(courseRepository.findByFacultyId(facultyId)).thenReturn(Arrays.asList());

    List<Course> result = facultyService.getCoursesByFaculty(facultyId);

    assertTrue(result.isEmpty());
    verify(courseRepository, times(1)).findByFacultyId(facultyId);
  }
}
