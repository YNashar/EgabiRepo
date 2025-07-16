package com.egabi.Repository;

import com.egabi.Main.CourseFacultyLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CourseFacultyLevelRepository extends JpaRepository<CourseFacultyLevel, Integer> {
    List<CourseFacultyLevel> findByFacultyIdAndLevel(Integer facultyId, Integer level);
    List<CourseFacultyLevel> findByCourseId(Integer courseId);
    List<CourseFacultyLevel> findByFacultyId(Integer facultyId);
    List<CourseFacultyLevel> findByLevel(Integer level);
}