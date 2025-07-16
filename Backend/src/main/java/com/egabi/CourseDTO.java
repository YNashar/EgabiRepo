package com.egabi;

public class CourseDTO {
    private Integer courseId;
    private String courseName;
    private Integer facultyId;

    public CourseDTO() {}

    public CourseDTO(Integer courseId, String courseName, Integer facultyId) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.facultyId = facultyId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Integer facultyId) {
        this.facultyId = facultyId;
    }
}
