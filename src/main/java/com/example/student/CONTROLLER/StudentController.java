package com.example.student.CONTROLLER;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.student.ENTITY.Student;
import com.example.student.RESPOSITORY.StudentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/student")
@CrossOrigin("*")
@Service
public class StudentController {
    
    @Autowired
    private StudentRepository studentRepository;

    Logger LOGGER = LoggerFactory.getLogger(StudentController.class);


    @GetMapping(value="/")
    public List<Student> getStudents() {
        return this.studentRepository.findAll();
    }
    
}
