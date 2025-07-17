package com.egabi.Mapper;

import com.egabi.Course;
import com.egabi.DTO.CourseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CourseMapper {
  CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

  CourseDTO courseToCourseDTO(Course course);
  Course courseDTOToCourse(CourseDTO courseDTO);
}
