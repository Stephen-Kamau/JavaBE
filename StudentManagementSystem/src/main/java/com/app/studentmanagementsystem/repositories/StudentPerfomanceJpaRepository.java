package com.app.studentmanagementsystem.repositories;

import com.app.studentmanagementsystem.models.StudentsPerformance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentPerfomanceJpaRepository extends JpaRepository<StudentsPerformance, Long> {
    List<StudentsPerformance> findByExam_Year(int year);
    List<StudentsPerformance> findByStudent_Id(Long studentId);
}
