package com.egabi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/faculties")
@CrossOrigin(origins = "*") // Allow access from Angular
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @GetMapping
    public List<Faculty> getAllFaculties() {
        return facultyService.getAllFaculties();
    }

    @GetMapping("/{id}")
    public Optional<Faculty> getFacultyById(@PathVariable Integer id) {
        return facultyService.getFacultyById(id);
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @PutMapping("/{id}")
    public Faculty updateFaculty(@PathVariable Integer id, @RequestBody Faculty faculty) {
        return facultyService.updateFaculty(id, faculty);
    }

    @DeleteMapping("/{id}")
    public void deleteFaculty(@PathVariable Integer id) {
        facultyService.deleteFaculty(id);
    }

    @GetMapping("/{facultyId}/courses")
    public List<Course> getCoursesByFaculty(@PathVariable Integer facultyId) {
        return facultyService.getCoursesByFaculty(facultyId);
    }
}