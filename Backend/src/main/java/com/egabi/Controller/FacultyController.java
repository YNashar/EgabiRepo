package com.egabi.Controller;

import com.egabi.Main.Course;
import com.egabi.DTO.FacultyDTO;
import com.egabi.Service.FacultyService;
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
    public List<FacultyDTO> getAllFaculties() {
        return facultyService.getAllFaculties();
    }

    @GetMapping("/{id}")
    public Optional<FacultyDTO> getFacultyById(@PathVariable Integer id) {
        return facultyService.getFacultyById(id);
    }

    @PostMapping
    public FacultyDTO createFaculty(@RequestBody FacultyDTO facultyDto) {
        return facultyService.createFaculty(facultyDto);
    }

    @PutMapping("/{id}")
    public FacultyDTO updateFaculty(@PathVariable Integer id, @RequestBody FacultyDTO facultyDto) {
        return facultyService.updateFaculty(id, facultyDto);
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
