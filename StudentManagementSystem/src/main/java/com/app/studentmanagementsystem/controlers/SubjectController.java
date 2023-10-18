package com.app.studentmanagementsystem.controlers;


import com.app.studentmanagementsystem.dtos.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/subjects")
public class SubjectController {

    private static final List<Subject> subjects = Arrays.asList(
            new Subject(101, "English"),
            new Subject(102, "Kiswahili"),
            new Subject(103, "Math"),
            new Subject(104, "Chemistry"),
            new Subject(105, "Biology"),
            new Subject(106, "Physics"),
            new Subject(107, "Geography"),
            new Subject(108, "Agriculture")
    );

    @GetMapping
    public List<Subject> getAllSubjects() {
        return subjects;
    }
}
