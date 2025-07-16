package com.egabi.Service;

import com.egabi.Main.Grade;
import com.egabi.Repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }

    public Optional<Grade> getGradeById(Integer id) {
        return gradeRepository.findById(id);
    }

    public Grade addGrade(Grade grade) {
        return gradeRepository.save(grade);
    }

    public void deleteGrade(Integer id) {
        gradeRepository.deleteById(id);
    }

    public Grade updateGrade(Integer id, Grade grade) {
        grade.setGradeId(id);
        return gradeRepository.save(grade);
    }
}
