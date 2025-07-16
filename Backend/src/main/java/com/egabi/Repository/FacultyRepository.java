package com.egabi.Repository;

import com.egabi.Main.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
    Faculty findByFacultyName(String facultyName);
}