package com.springboot_mvc.controllers;

import com.springboot_mvc.dto.StudentDTO;
import com.springboot_mvc.entities.StudentEntity;
import com.springboot_mvc.repositories.StudentRepository;
import com.springboot_mvc.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/db/students")
public class DBController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<StudentDTO> getAllStudentsFromH2DB(){

        return studentService.getAllStudents();
    }

    @GetMapping(path = "/{stdId}")
    public StudentDTO getStudentByIdFromH2DB(@PathVariable Long stdId){
        return studentService.getStudentById(stdId);
    }

    @PostMapping
    public StudentDTO insertStudentToH2DB(@RequestBody StudentDTO student){

        return studentService.insertStudent(student);
    }
}
