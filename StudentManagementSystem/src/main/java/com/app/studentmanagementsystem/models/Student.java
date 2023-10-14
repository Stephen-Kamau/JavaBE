package com.app.studentmanagementsystem.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table(name = "student_tbl")
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @Column(name = "fname")
    private String firstName;
    @Column(name = "lname")
    private String lastName;
    @Column(name = "location")
    private String location;
    @Column(name = "school")
    private String school;
    @Column(name = "age")
    private int age;
    @Column(name = "date_of_creation")
    private Date createdAt;
    @Column(name = "year_of_registration")
    private int yearOfRegistration;
}
