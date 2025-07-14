package com.egabi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getCoursesByFaculty(Integer facultyId) {
        return courseRepository.findByFacultyId(facultyId);
    }

    public List<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Optional<Faculty> getFacultyById(Integer id) {
        return facultyRepository.findById(id);
    }

    public Faculty updateFaculty(Integer id, Faculty faculty) {
        faculty.setFacultyId(id);
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(Integer id) {
        facultyRepository.deleteById(id);
    }
}