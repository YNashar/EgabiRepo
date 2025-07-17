package com.egabi.Service;

import com.egabi.Faculty;
import com.egabi.Course;
import com.egabi.Repository.FacultyRepository;
import com.egabi.Repository.CourseRepository;
import com.egabi.DTO.FacultyDTO;
import com.egabi.Mapper.FacultyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FacultyService {

  @Autowired
  private FacultyRepository facultyRepository;

  @Autowired
  private CourseRepository courseRepository;

  @Autowired
  private FacultyMapper facultyMapper;

  public List<FacultyDTO> getAllFaculties() {
    return facultyRepository.findAll().stream()
      .map(facultyMapper::facultyToFacultyDTO)
      .collect(Collectors.toList());
  }

  public Optional<FacultyDTO> getFacultyById(Integer id) {
    return facultyRepository.findById(id).map(facultyMapper::facultyToFacultyDTO);
  }

  public FacultyDTO createFaculty(FacultyDTO facultyDto) {
    Faculty faculty = facultyMapper.facultyDTOToFaculty(facultyDto);
    Faculty saved = facultyRepository.save(faculty);
    return facultyMapper.facultyToFacultyDTO(saved);
  }

  public FacultyDTO updateFaculty(Integer id, FacultyDTO facultyDto) {
    Faculty faculty = facultyMapper.facultyDTOToFaculty(facultyDto);
    faculty.setFacultyId(id);
    Faculty updated = facultyRepository.save(faculty);
    return facultyMapper.facultyToFacultyDTO(updated);
  }

  public void deleteFaculty(Integer id) {
    facultyRepository.deleteById(id);
  }

  public List<Course> getCoursesByFaculty(Integer facultyId) {
    return courseRepository.findByFacultyId(facultyId);
  }
}
