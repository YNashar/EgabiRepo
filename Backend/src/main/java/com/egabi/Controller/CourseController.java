package com.egabi.Controller;
import com.egabi.DTO.CourseDTO;
import com.egabi.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/courses")
@CrossOrigin(origins = "*")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<CourseDTO> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public Optional<CourseDTO> getCourseById(@PathVariable Integer id) {
        return courseService.getCourseById(id);
    }

    @PostMapping
    public CourseDTO createCourse(@RequestBody CourseDTO courseDto) {
        return courseService.createCourse(courseDto);
    }

    @PutMapping("/{id}")
    public CourseDTO updateCourse(@PathVariable Integer id, @RequestBody CourseDTO courseDto) {
        return courseService.updateCourse(id, courseDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Integer id) {
        courseService.deleteCourse(id);
    }
}
