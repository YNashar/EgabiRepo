package com.egabi.Mapper;

import com.egabi.DTO.StudentDTO;
import com.egabi.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentMapper {
  StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

  StudentDTO studentToStudentDTO(Student student);
  Student studentDTOToStudent(StudentDTO studentDTO);
}
