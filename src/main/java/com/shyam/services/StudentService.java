package com.shyam.services;

import com.shyam.dto.StudentDTO;
import com.shyam.entities.StudentEntity;
import java.util.List;

public interface StudentService {
    StudentEntity createStudent(StudentDTO student);
    StudentEntity getStudentById(Long id);
    List<StudentEntity> getAllStudents();
    StudentEntity updateStudent(Long id, StudentDTO student);
    void deleteStudent(Long id);
}
