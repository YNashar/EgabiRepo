package com.egabi;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
    Faculty findByFacultyName(String facultyName);
}