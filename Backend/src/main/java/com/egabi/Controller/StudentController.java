package com.egabi.Controller;

import com.egabi.DTO.StudentDTO;
import com.egabi.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {

  @Autowired
  private StudentService studentService;

  @GetMapping
  public List<StudentDTO> getAllStudents() {
    return studentService.getAllStudents();
  }

  @GetMapping("/{id}")
  public StudentDTO getStudentById(@PathVariable Integer id) {
    return studentService.getStudentById(id);
  }

  @PostMapping
  public StudentDTO createStudent(@RequestBody StudentDTO studentDTO) {
    return studentService.createStudent(studentDTO);
  }

  @PutMapping("/{id}")
  public StudentDTO updateStudent(@PathVariable Integer id, @RequestBody StudentDTO studentDTO) {
    return studentService.updateStudent(id, studentDTO);
  }

  @DeleteMapping("/{id}")
  public void deleteStudent(@PathVariable Integer id) {
    studentService.deleteStudent(id);
  }
}
