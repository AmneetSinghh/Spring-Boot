package com.example.demo.student;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/get")
    public List<Student> getStudents(){
         List<Student> val = studentService.getStudents();
        System.out.println(val.get(0).toString());
         return val;
    }

    @PostMapping("/insert")
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }
}
