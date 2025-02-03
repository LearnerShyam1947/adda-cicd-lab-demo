package com.shyam.services.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.shyam.dto.StudentDTO;
import com.shyam.entities.StudentEntity;
import com.shyam.exceptions.StudentAlreadyExistsException;
import com.shyam.exceptions.StudentNotFoundException;
import com.shyam.repositories.StudentRepository;
import com.shyam.services.StudentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public StudentEntity createStudent(StudentDTO student) {
        // // Check if student already exists by email
        if (studentRepository.findByEmail(student.getEmail()).isPresent()) 
            throw new StudentAlreadyExistsException(student.getName(), student.getEmail());
        
        StudentEntity newStudent = new StudentEntity();
        BeanUtils.copyProperties(student, newStudent);

        return studentRepository.save(newStudent);
    }

    @Override
    public StudentEntity getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));
    }
    
    public StudentEntity getStudentByEmail(String email) {
        return studentRepository.findByEmail(email)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with email: " + email));
    }

    @Override
    public List<StudentEntity> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public StudentEntity updateStudent(Long id, StudentDTO studentDetails) {
        // First, check if student exists
        StudentEntity existingStudent = getStudentById(id);
        BeanUtils.copyProperties(studentDetails, existingStudent);
       
        return studentRepository.save(existingStudent);
    }

    @Override
    public void deleteStudent(Long id) {
        // First, check if student exists
        StudentEntity existingStudent = getStudentById(id);
        studentRepository.delete(existingStudent);
    }


}
