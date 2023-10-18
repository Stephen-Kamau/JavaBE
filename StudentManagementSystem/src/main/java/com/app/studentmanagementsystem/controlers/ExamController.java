package com.app.studentmanagementsystem.controlers;

import com.app.studentmanagementsystem.models.Exam;
import com.app.studentmanagementsystem.repositories.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/exams")
public class ExamController {

    @Autowired
    private ExamRepository examRepository;

    @PostMapping
    public ResponseEntity<Exam> createExam(@RequestBody Exam exam) {
        Exam createdExam = examRepository.save(exam);
        return new ResponseEntity<>(createdExam, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    @GetMapping("/{examId}")
    public ResponseEntity<Exam> getExamById(@PathVariable Long examId) {
        Optional<Exam> optionalExam = examRepository.findById(examId);
        return optionalExam.map(exam -> new ResponseEntity<>(exam, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{examId}")
    public ResponseEntity<Exam> updateExam(@PathVariable Long examId, @RequestBody Exam updatedExam) {
        Optional<Exam> optionalExam = examRepository.findById(examId);
        if (optionalExam.isPresent()) {
            Exam exam = optionalExam.get();
            exam.setYear(updatedExam.getYear());
            exam.setName(updatedExam.getName());
            examRepository.save(exam);
            return new ResponseEntity<>(exam, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{examId}")
    public ResponseEntity<Void> deleteExam(@PathVariable Long examId) {
        if (examRepository.existsById(examId)) {
            examRepository.deleteById(examId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

