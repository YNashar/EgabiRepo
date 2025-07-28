package com.egabi.Repository;

import com.egabi.student;
import org.springframework.data.jpa.repository.JpaRepository;



public interface StudentRepository extends JpaRepository<student, Integer> {

}
