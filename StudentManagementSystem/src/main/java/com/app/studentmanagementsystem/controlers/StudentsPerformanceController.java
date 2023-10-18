package com.app.studentmanagementsystem.controlers;

import com.app.studentmanagementsystem.models.StudentsPerformance;
import com.app.studentmanagementsystem.repositories.StudentPerfomanceJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/exam-performances")
public class StudentsPerformanceController {

    @Autowired
    private StudentPerfomanceJpaRepository studentPerfomanceJpaRepository;

    @GetMapping
    public List<StudentsPerformance> getAllExamPerformances() {
        return studentPerfomanceJpaRepository.findAll();
    }

    @GetMapping("/year/{year}")
    public List<StudentsPerformance> getPerformancesByYear(@PathVariable int year) {
        return studentPerfomanceJpaRepository.findByExam_Year(year);
    }

    @GetMapping("/student/{studentId}")
    public List<StudentsPerformance> getPerformancesByStudent(@PathVariable Long studentId) {
        return studentPerfomanceJpaRepository.findByStudent_Id(studentId);
    }

    @PostMapping
    public ResponseEntity<StudentsPerformance> addPerformance(@RequestBody StudentsPerformance performance) {
        StudentsPerformance addedPerformance = studentPerfomanceJpaRepository.save(performance);
        return new ResponseEntity<>(addedPerformance, HttpStatus.CREATED);
    }

    @PutMapping("/{performanceId}")
    public ResponseEntity<StudentsPerformance> updatePerformance(
            @PathVariable Long performanceId, @RequestBody StudentsPerformance updatedPerformance) {
        Optional<StudentsPerformance> optionalPerformance = studentPerfomanceJpaRepository.findById(performanceId);
        if (optionalPerformance.isPresent()) {
            StudentsPerformance performance = optionalPerformance.get();
            // Update fields as needed
            studentPerfomanceJpaRepository.save(performance);
            return new ResponseEntity<>(performance, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{performanceId}")
    public ResponseEntity<Void> deletePerformance(@PathVariable Long performanceId) {
        if (studentPerfomanceJpaRepository.existsById(performanceId)) {
            studentPerfomanceJpaRepository.deleteById(performanceId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
