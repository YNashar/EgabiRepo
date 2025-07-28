package com.egabi.Mapper;

import com.egabi.DTO.StudentDTO;
import com.egabi.student;

public class StudentMapper {
    public static StudentDTO toDTO(student entity) {
        if (entity == null) return null;
        return new StudentDTO(
                entity.getStudentId(),
                entity.getStudentName(),
                entity.getFacultyId(),
                entity.getLevel(),
                entity.getNationalId()
        );
    }

    public static student toEntity(StudentDTO dto) {
        if (dto == null) return null;
        return new student(
                dto.getStudentId(),
                dto.getStudentName(),
                dto.getFacultyId(),
                dto.getLevel(),
                dto.getNationalId()
        );
    }
}