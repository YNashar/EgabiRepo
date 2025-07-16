package com.egabi.Main;

import jakarta.persistence.*;

@Entity
@Table(name = "course_faculty_level", uniqueConstraints = @UniqueConstraint(columnNames = {"course_id", "faculty_id", "level"}))
public class CourseFacultyLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "course_id", nullable = false)
    private Integer courseId;

    @Column(name = "faculty_id", nullable = false)
    private Integer facultyId;

    @Column(name = "level", nullable = false)
    private Integer level;


    public CourseFacultyLevel() {}

    public CourseFacultyLevel(Integer courseId, Integer facultyId, Integer level) {
        this.courseId = courseId;
        this.facultyId = facultyId;
        this.level = level;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getCourseId() { return courseId; }
    public void setCourseId(Integer courseId) { this.courseId = courseId; }

    public Integer getFacultyId() { return facultyId; }
    public void setFacultyId(Integer facultyId) { this.facultyId = facultyId; }

    public Integer getLevel() { return level; }
    public void setLevel(Integer level) { this.level = level; }
}