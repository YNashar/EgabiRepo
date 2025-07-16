package com.egabi.DTO;

public class FacultyDTO {
    private Integer facultyId;
    private String facultyName;

    public FacultyDTO() {}

    public FacultyDTO(Integer facultyId, String facultyName) {
        this.facultyId = facultyId;
        this.facultyName = facultyName;
    }

    public Integer getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Integer facultyId) {
        this.facultyId = facultyId;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }
}
