package com.springboot_mvc.controllers;

import com.springboot_mvc.dto.StudentDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;

// @RestController is implementing from @Controller and @ResponseBody
@RestController
@RequestMapping(path = "/students")
public class StudentController {

    @GetMapping(path="/home")
    public String getHomePage(){
        return "This is home page from StudentController";
    }

    /*
    we can add dynamic urls in path using @PathVariable and @RequestParam
    @PathVariable is used when parameter is essential

    url = /students/123

    @GetMapping(path = "/{studentId}")
    public StudentDTO getStudentById(@PathVariable Long studentId){
        return new StudentDTO("Pranav Patil", "Kolhapur", studentId, true , new Date());
    }
    */

    @GetMapping(path = "/{studentId}")
    public StudentDTO getStudentById(@PathVariable(name = "studentId") Long id){
        return new StudentDTO("Pranav Patil", "Kolhapur", id, true , LocalDate.now());
    }

    /*
    @PathVariable is used when parameter is not essential

    url = /students?id=123

    @GetMapping
    public String getAllStudents(@RequestParam(required = false) Integer age){
        return "List of Students having age " + age;
    }
    */

    @GetMapping
    public String getAllStudents(@RequestParam(required = false,name = "myAge") Integer age){
        return "List of Students having age " + age;
    }

    @PostMapping
    public StudentDTO insertStudent(@RequestBody StudentDTO student){
        //student.setId(100L);
        return student;
    }

    @PutMapping
    public String updateStudent(){
        return "Updating student in database";
    }

    @PatchMapping
    public String updateNameOfStudent(){
        return "Updating name of student in database";
    }

    @DeleteMapping
    public String deleteStudent(){
        return "Deleting student from database";
    }

}
