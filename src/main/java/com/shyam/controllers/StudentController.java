package com.shyam.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shyam.dto.StudentDTO;
import com.shyam.entities.StudentEntity;
import com.shyam.services.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/student")
public class StudentController {

    private final StudentService studentService;

    // Create a new student
    @PostMapping
    public  ResponseEntity<Map<String, Object>> createStudent(@RequestBody StudentDTO studentDTO) {
        StudentEntity savedStudent = studentService.createStudent(studentDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                    Map.of(
                        "message", "new student created successfully",
                        "student", savedStudent
                    )
                );
    }

    // Get all students
    @GetMapping
    public ResponseEntity<List<StudentEntity>> getAllStudents() {
        List<StudentEntity> students = studentService.getAllStudents();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(students);
    }

    // Get student by ID
    @GetMapping("/{id}")
    public ResponseEntity<StudentEntity> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    // Update student
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO) {
        StudentEntity updatedStudent = studentService.updateStudent(id, studentDTO);
        
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                    Map.of(
                        "message", "student updated successfully",
                        "student", updatedStudent
                    )
                );
    }

    // Delete student
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                    Map.of(
                        "message", "student deleted successfully"
                    )
                );
    }
}
