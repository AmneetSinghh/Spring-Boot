package com.example.demo.student;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student){
//        Student studentOptional = studentRepository.findStudentByEmail(student.getEmail());
//        if(studentOptional != null){
//            throw new IllegalStateException("Email taken");
//        }
        studentRepository.save(student);

    }
}
