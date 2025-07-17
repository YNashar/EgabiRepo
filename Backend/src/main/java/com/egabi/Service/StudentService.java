package com.egabi.Service;

import com.egabi.DTO.StudentDTO;
import com.egabi.Mapper.StudentMapper;
import com.egabi.Repository.StudentRepository;
import com.egabi.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private StudentMapper studentMapper;

  public List<StudentDTO> getAllStudents() {
    return studentRepository.findAll()
      .stream()
      .map(studentMapper::studentToStudentDTO)
      .collect(Collectors.toList());
  }

  public StudentDTO getStudentById(Integer id) {
    return studentRepository.findById(id)
      .map(studentMapper::studentToStudentDTO)
      .orElse(null);
  }

  public StudentDTO createStudent(StudentDTO studentDTO) {
    Student studentEntity = studentMapper.studentDTOToStudent(studentDTO);
    Student saved = studentRepository.save(studentEntity);
    return studentMapper.studentToStudentDTO(saved);
  }

  public StudentDTO updateStudent(Integer id, StudentDTO studentDTO) {
    return studentRepository.findById(id)
      .map(existing -> {
        Student updated = studentMapper.studentDTOToStudent(studentDTO);
        updated.setStudentId(id);
        return studentMapper.studentToStudentDTO(studentRepository.save(updated));
      })
      .orElse(null);
  }

  public void deleteStudent(Integer id) {
    studentRepository.deleteById(id);
  }
}
