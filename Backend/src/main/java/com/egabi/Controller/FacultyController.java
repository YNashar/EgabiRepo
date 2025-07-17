package com.egabi.Controller;

import com.egabi.Course;
import com.egabi.DTO.FacultyDTO;
import com.egabi.Service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<List<FacultyDTO>> getAllFaculties() {
    List<FacultyDTO> faculties = facultyService.getAllFaculties();
    return ResponseEntity.ok(faculties);
  }

  @GetMapping("/{id}")
  public ResponseEntity<FacultyDTO> getFacultyById(@PathVariable Integer id) {
    return facultyService.getFacultyById(id)
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<FacultyDTO> createFaculty(@RequestBody FacultyDTO facultyDto) {
    FacultyDTO created = facultyService.createFaculty(facultyDto);
    return ResponseEntity.ok(created);
  }

  @PutMapping("/{id}")
  public ResponseEntity<FacultyDTO> updateFaculty(@PathVariable Integer id, @RequestBody FacultyDTO facultyDto) {
    FacultyDTO updated = facultyService.updateFaculty(id, facultyDto);
    return ResponseEntity.ok(updated);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteFaculty(@PathVariable Integer id) {
    facultyService.deleteFaculty(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{facultyId}/courses")
  public ResponseEntity<List<Course>> getCoursesByFaculty(@PathVariable Integer facultyId) {
    List<Course> courses = facultyService.getCoursesByFaculty(facultyId);
    return ResponseEntity.ok(courses);
  }
}
