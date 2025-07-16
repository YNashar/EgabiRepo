package com.egabi;

public class StudentDTO {
   private Integer studentId;
   private String studentName;
   private Integer facultyId;
   private Integer level;
   private String nationalId;

   public StudentDTO() {}

   public StudentDTO(Integer studentId, String studentName, Integer facultyId, Integer level, String nationalId) {
      this.studentId = studentId;
      this.studentName = studentName;
      this.facultyId = facultyId;
      this.level = level;
      this.nationalId = nationalId;
   }

   public Integer getStudentId() {
      return studentId;
   }

   public void setStudentId(Integer studentId) {
      this.studentId = studentId;
   }

   public String getStudentName() {
      return studentName;
   }

   public void setStudentName(String studentName) {
      this.studentName = studentName;
   }

   public Integer getFacultyId() {
      return facultyId;
   }

   public void setFacultyId(Integer facultyId) {
      this.facultyId = facultyId;
   }

   public Integer getLevel() {
      return level;
   }

   public void setLevel(Integer level) {
      this.level = level;
   }

   public String getNationalId() {
      return nationalId;
   }

   public void setNationalId(String nationalId) {
      this.nationalId = nationalId;
   }
}