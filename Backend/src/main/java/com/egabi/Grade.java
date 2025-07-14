package com.egabi;

import jakarta.persistence.*;

@Entity
@Table(name = "grades")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grade_id")
    private Integer gradeId;

    @Column(name = "grade")
    private Double gradeValue;

    @Column(name = "student_id")
    private Integer studentId;

    @Column(name = "course_id")
    private Integer courseId;

    public Grade() {}

    public Grade(Integer gradeId, Double gradeValue, Integer studentId, Integer courseId) {
        this.gradeId = gradeId;
        this.gradeValue = gradeValue;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Double getGradeValue() {
        return gradeValue;
    }

    public void setGradeValue(Double gradeValue) {
        this.gradeValue = gradeValue;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "gradeId=" + gradeId +
                ", gradeValue=" + gradeValue +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                '}';
    }
}