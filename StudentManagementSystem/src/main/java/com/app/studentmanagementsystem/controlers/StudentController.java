package com.app.studentmanagementsystem.controlers;


import com.app.studentmanagementsystem.dtos.StudentDto;
import com.app.studentmanagementsystem.exception.BadRequestException;
import com.app.studentmanagementsystem.exception.ResourceNotFoundException;
import com.app.studentmanagementsystem.models.Student;
import com.app.studentmanagementsystem.repositories.StudentJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class StudentController {
    @Autowired
    private StudentJpaRepository studentJpaRepository;

    // get all students
    @GetMapping("/students")
    public List<Student> findAllStudents(){
        return studentJpaRepository.findAll();
    }

    // Get students by age
    @GetMapping("/students/year_of_registration")
    public ResponseEntity<?> getStudentsByYearOfRegistration(@RequestParam(required = false) String year) {
        if (year == null) {
            throw new BadRequestException("The 'year' parameter is required.", "api/v1/students/year_of_registration?year="+year);
        }
        try {
            int yearInt = Integer.parseInt(year);
            List<Student> students = studentJpaRepository.findByYearOfRegistration(yearInt);
            return ResponseEntity.ok(students);
        } catch (NumberFormatException e) {
            // Handle the case where the provided year is not a valid integer
            throw new BadRequestException("Invalid year format. Please provide a valid integer", "api/v1/students/year_of_registration?year="+year);

        }


    }



    // get student by id rest api
    @GetMapping("/students/{id}")
    public ResponseEntity< Student > getStudentById(@PathVariable Long id) {
        Student student;
        student = studentJpaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stuent not exist with id :" + id, "/api/v1/students/" + id));
        return ResponseEntity.ok(student);
    }

    // update studnet
    @PutMapping("/students/{id}")
    public ResponseEntity < Student > updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
        Student student = studentJpaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not exist with id :" + id, "/api/v1/students/" + id));

        student.setFirstName(studentDetails.getFirstName());
        student.setLastName(studentDetails.getLastName());
        student.setLocation(studentDetails.getLocation());
        student.setSchool(studentDetails.getSchool());
        student.setYearOfRegistration(studentDetails.getYearOfRegistration());
        Student updatedStudent = studentJpaRepository.save(student);
        return ResponseEntity.ok(updatedStudent);
    }

    // create student rest api
    @PostMapping("/students")
    public ResponseEntity<?> createStudent(@Valid @RequestBody StudentDto studentDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Validation errors occurred, return a meaningful error response
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }

        Student newStudent = new Student();
        newStudent.setYearOfRegistration(studentDto.getYearOfRegistration());
        newStudent.setCreatedAt(new Date());
        newStudent.setSchool(newStudent.getSchool());
        newStudent.setLocation(studentDto.getLocation());
        newStudent.setLastName(studentDto.getLastName());
        newStudent.setFirstName(studentDto.getFirstName());
        newStudent.setAge(studentDto.getAge());
        return ResponseEntity.ok(studentJpaRepository.save(newStudent));
    }

    //delete student
    @DeleteMapping("/students/{id}")
    public ResponseEntity <Map< String, Boolean >> deleteStudent(@PathVariable Long id) {
        Student student = studentJpaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not exist with id :" + id, "/api/v1/students/" + id));
        studentJpaRepository.delete(student);
        Map < String, Boolean > response = new HashMap< >();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }



}
