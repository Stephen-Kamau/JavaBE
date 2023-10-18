package com.app.studentmanagementsystem.repositories;
import com.app.studentmanagementsystem.models.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Long> {
    List<Exam> findByYear(int year);
}