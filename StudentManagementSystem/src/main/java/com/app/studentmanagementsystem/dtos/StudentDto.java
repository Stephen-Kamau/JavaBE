package com.app.studentmanagementsystem.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    @NotBlank(message = "First name is required")
    private String firstName;
    @NotBlank(message = "Last name is required")
    private String lastName;
    @NotBlank(message = "Location is required")
    private String location;
    @NotBlank(message = "School name is required")
    private String school;
    @Min(value = 0, message = "Age must be a positive number")
    private int age;
    @Min(value = 0, message = "year of Registration must be a positive number")
    private int yearOfRegistration;

}
