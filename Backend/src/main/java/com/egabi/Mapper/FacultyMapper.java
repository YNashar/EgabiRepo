package com.egabi.Mapper;

import com.egabi.Faculty;
import com.egabi.DTO.FacultyDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FacultyMapper {
  FacultyMapper INSTANCE = Mappers.getMapper(FacultyMapper.class);

  FacultyDTO facultyToFacultyDTO(Faculty faculty);
  Faculty facultyDTOToFaculty(FacultyDTO facultyDTO);
}
