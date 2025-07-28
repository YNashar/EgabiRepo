package com.egabi.Service;

import com.egabi.DTO.StudentDTO;
import com.egabi.Repository.StudentRepository;
import com.egabi.student;
import com.egabi.Mapper.StudentMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentDTO> getStudents() {
        return studentRepository.findAll().stream()
                .map(StudentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void addStudent(StudentDTO studentDto) {
        student student = StudentMapper.toEntity(studentDto);
        if (student.getNationalId() != null && student.getNationalId().length() > 16) {
            throw new IllegalArgumentException("National ID must not exceed 16 characters");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Integer id) {
        if (!studentRepository.existsById(id)) {
            throw new IllegalArgumentException("Student with id " + id + " does not exist");
        }
        studentRepository.deleteById(id);
    }

    public void updateStudent(Integer id, StudentDTO studentDto) {
        student existing = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        existing.setStudentName(studentDto.getStudentName());
        existing.setFacultyId(studentDto.getFacultyId());
        existing.setLevel(studentDto.getLevel());
        existing.setNationalId(studentDto.getNationalId());

        studentRepository.save(existing);
    }
}
