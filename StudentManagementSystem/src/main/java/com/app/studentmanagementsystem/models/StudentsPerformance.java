package com.app.studentmanagementsystem.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedEntityGraph
@Entity
@Table(name = "students_perfomance")
public class StudentsPerformance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private int englishScore;
    private int kiswahiliScore;
    private int mathScore;
    private int chemistryScore;
    private int biologyScore;
    private int physicsScore;
    private int geographyScore;
    private int agricultureScore;

}
