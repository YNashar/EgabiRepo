package com.egabi.Controller;

import com.egabi.CourseFacultyLevel;
import com.egabi.Service.CourseFacultyLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/course-faculty-level")
@CrossOrigin(origins = "http://localhost:4200")
public class CourseFacultyLevelController {

    @Autowired
    private CourseFacultyLevelService service;

    @GetMapping
    public List<CourseFacultyLevel> getAll() {
        return service.getAllCourseFacultyLevels();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseFacultyLevel> getById(@PathVariable Integer id) {
        Optional<CourseFacultyLevel> result = service.getCourseFacultyLevelById(id);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public CourseFacultyLevel create(@RequestBody CourseFacultyLevel cfl) {
        return service.saveCourseFacultyLevel(cfl);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseFacultyLevel> update(@PathVariable Integer id, @RequestBody CourseFacultyLevel cfl) {
        if (service.getCourseFacultyLevelById(id).isPresent()) {
            cfl.setId(id);
            return ResponseEntity.ok(service.saveCourseFacultyLevel(cfl));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (service.getCourseFacultyLevelById(id).isPresent()) {
            service.deleteCourseFacultyLevel(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/faculty/{facultyId}/level/{level}")
    public List<CourseFacultyLevel> getByFacultyAndLevel(@PathVariable Integer facultyId, @PathVariable Integer level) {
        return service.getByFacultyAndLevel(facultyId, level);
    }

    @GetMapping("/course/{courseId}")
    public List<CourseFacultyLevel> getByCourseId(@PathVariable Integer courseId) {
        return service.getByCourseId(courseId);
    }

    @GetMapping("/faculty/{facultyId}")
    public List<CourseFacultyLevel> getByFacultyId(@PathVariable Integer facultyId) {
        return service.getByFacultyId(facultyId);
    }

    @GetMapping("/level/{level}")
    public List<CourseFacultyLevel> getByLevel(@PathVariable Integer level) {
        return service.getByLevel(level);
    }
}