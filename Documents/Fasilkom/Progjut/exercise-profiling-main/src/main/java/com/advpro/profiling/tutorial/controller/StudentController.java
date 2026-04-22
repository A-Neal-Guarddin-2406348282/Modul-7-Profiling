package com.advpro.profiling.tutorial.controller;

import com.advpro.profiling.tutorial.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author muhammad.khadafi
 */
@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/all-student")
    public ResponseEntity<String> allStudents() {
        return ResponseEntity.ok(studentService.getAllStudentsWithCourses());
    }

    @GetMapping("/highest-gpa")
    public ResponseEntity<String> highestGpa() {
        return studentService.findStudentWithHighestGpa()
                .map(student -> ResponseEntity.ok(student.toString()))
                .orElseGet(() -> ResponseEntity.ok("No student found"));
    }

    @GetMapping("/all-student-name")
    public ResponseEntity<String> allStudentName() {
        return ResponseEntity.ok(studentService.joinStudentNames());
    }
}