package com.egabi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/grades")
@CrossOrigin(origins = "*")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @GetMapping
    public List<Grade> getAllGrades() {
        return gradeService.getAllGrades();
    }

    @GetMapping("/{id}")
    public Optional<Grade> getGradeById(@PathVariable Integer id) {
        return gradeService.getGradeById(id);
    }

    @PostMapping
    public Grade addGrade(@RequestBody Grade grade) {
        return gradeService.addGrade(grade);
    }

    @DeleteMapping("/{id}")
    public void deleteGrade(@PathVariable Integer id) {
        gradeService.deleteGrade(id);
    }

    @PutMapping("/{id}")
    public Grade updateGrade(@PathVariable Integer id, @RequestBody Grade grade) {
        return gradeService.updateGrade(id, grade);
    }
}
