package com.example.student.service;

import com.example.student.errors.StudentNotFoundException;
import com.example.student.model.Student;
import com.example.student.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepo studentRepo;
    public List<Student> getStudents(){
        ArrayList<Student> students = new ArrayList<>();
        studentRepo.findAll().forEach(students::add);
        return students;
    }

    //find student
    public Student getStudent(int id){
        return studentRepo.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
    }

    //create a student
    public Student createStudent(Student student){
        return studentRepo.save(student);
    }

    //delete a student
    public void deleteStudent(int id){
        getStudent(id);
        studentRepo.deleteById(id);
    }

    //update student
    public void updateStudent(int id,Student student ){
        if(getStudent(id)!=null){
            studentRepo.save(student);
        }
    }
}
