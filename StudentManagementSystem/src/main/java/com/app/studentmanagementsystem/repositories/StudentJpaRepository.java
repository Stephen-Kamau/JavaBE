package com.app.studentmanagementsystem.repositories;

import com.app.studentmanagementsystem.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentJpaRepository extends JpaRepository<Student, Long> {
    List<Student> findByYearOfRegistration(int year);
}
