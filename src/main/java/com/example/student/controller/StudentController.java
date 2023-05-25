package com.example.student.controller;

import com.example.student.model.Student;
import com.example.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
public class StudentController {

    @Autowired
    StudentService studentservice;

    //get students
    @GetMapping("/students")
    private List<Student> fetchStudents(){
        return studentservice.getStudents();
    }

    //get student
    @GetMapping("/students/{id}")
    private ResponseEntity<Object> fetchStudent(@PathVariable("id") int id){
        Student found= studentservice.getStudent(id);
        return new ResponseEntity<Object>(found, HttpStatus.OK);
    }

    //create student
    @PostMapping("/students")
    private ResponseEntity<Object> createStudent(@Valid @RequestBody Student student) {
        Student s = studentservice.createStudent(student);
        return new ResponseEntity<Object>(s, HttpStatus.CREATED);
    }
    //delete student
    @DeleteMapping("/students/{id}")
    private ResponseEntity<Object> deleteStudent(@PathVariable("id") int id){
        studentservice.deleteStudent(id);
        return new ResponseEntity<Object>("deleted student",HttpStatus.OK);
    }

    //update student
    @PutMapping("/students/{id}")
    private ResponseEntity<Object> updateStudent(@Valid @PathVariable("id") int id, @RequestBody Student student){
        studentservice.updateStudent(id,student);
        return new ResponseEntity<Object>("updated succesfully",HttpStatus.OK);
    }
}