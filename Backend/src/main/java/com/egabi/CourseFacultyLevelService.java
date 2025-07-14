package com.egabi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseFacultyLevelService {

    @Autowired
    private CourseFacultyLevelRepository courseFacultyLevelRepository;

    public List<CourseFacultyLevel> getAllCourseFacultyLevels() {
        return courseFacultyLevelRepository.findAll();
    }

    public CourseFacultyLevel saveCourseFacultyLevel(CourseFacultyLevel courseFacultyLevel) {
        return courseFacultyLevelRepository.save(courseFacultyLevel);
    }

    public Optional<CourseFacultyLevel> getCourseFacultyLevelById(Integer id) {
        return courseFacultyLevelRepository.findById(id);
    }

    public void deleteCourseFacultyLevel(Integer id) {
        courseFacultyLevelRepository.deleteById(id);
    }

    public List<CourseFacultyLevel> getByFacultyAndLevel(Integer facultyId, Integer level) {
        return courseFacultyLevelRepository.findByFacultyIdAndLevel(facultyId, level);
    }

    public List<CourseFacultyLevel> getByCourseId(Integer courseId) {
        return courseFacultyLevelRepository.findByCourseId(courseId);
    }

    public List<CourseFacultyLevel> getByFacultyId(Integer facultyId) {
        return courseFacultyLevelRepository.findByFacultyId(facultyId);
    }

    public List<CourseFacultyLevel> getByLevel(Integer level) {
        return courseFacultyLevelRepository.findByLevel(level);
    }
}