package com.springboot_mvc.controllers;

import com.springboot_mvc.entities.StudentEntity;
import com.springboot_mvc.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/db/students")
public class DBController {

    @Autowired
    private StudentRepository stdRepo;

    @GetMapping
    public List<StudentEntity> getAllStudentsFromH2DB(){
       return stdRepo.findAll();
    }

    @GetMapping(path = "/{stdId}")
    public StudentEntity getStudentByIdFromH2DB(@PathVariable Long stdId){
        return stdRepo.findById(stdId).orElse(null);
    }

    @PostMapping
    public StudentEntity insertStudentToH2DB(@RequestBody StudentEntity student){
        return stdRepo.save(student);
    }
}
