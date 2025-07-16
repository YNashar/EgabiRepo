package com.egabi;

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

    // Mapping from entity to DTO
    private FacultyDTO toDTO(Faculty entity) {
        return new FacultyDTO(
                entity.getFacultyId(),
                entity.getFacultyName()
        );
    }

    // Mapping from DTO to entity
    private Faculty toEntity(FacultyDTO dto) {
        return new Faculty(
                dto.getFacultyId(),
                dto.getFacultyName()
        );
    }

    public List<Course> getCoursesByFaculty(Integer facultyId) {
        return courseRepository.findByFacultyId(facultyId);
    }

    public List<FacultyDTO> getAllFaculties() {
        return facultyRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public FacultyDTO createFaculty(FacultyDTO facultyDto) {
        Faculty faculty = toEntity(facultyDto);
        Faculty saved = facultyRepository.save(faculty);
        return toDTO(saved);
    }

    public Optional<FacultyDTO> getFacultyById(Integer id) {
        return facultyRepository.findById(id).map(this::toDTO);
    }

    public FacultyDTO updateFaculty(Integer id, FacultyDTO facultyDto) {
        Faculty faculty = toEntity(facultyDto);
        faculty.setFacultyId(id);
        Faculty updated = facultyRepository.save(faculty);
        return toDTO(updated);
    }

    public void deleteFaculty(Integer id) {
        facultyRepository.deleteById(id);
    }
}
