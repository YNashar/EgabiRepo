package com.egabi.Mapper;

import com.egabi.DTO.CourseDTO;
import com.egabi.Course;

public class CourseMapper {
    public static CourseDTO toDTO(Course entity) {
        if (entity == null) return null;
        return new CourseDTO(
                entity.getCourseId(),
                entity.getCourseName(),
                entity.getFacultyId()
        );
    }

    public static Course toEntity(CourseDTO dto) {
        if (dto == null) return null;
        return new Course(
                dto.getCourseId(),
                dto.getCourseName(),
                dto.getFacultyId()
        );
    }
}