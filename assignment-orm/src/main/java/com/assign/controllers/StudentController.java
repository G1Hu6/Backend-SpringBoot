package com.assign.controllers;

import com.assign.dto.StudentDto;
import com.assign.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<StudentDto> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping(path = "/{id}")
    public StudentDto getStudentById(@PathVariable Long id){
        return studentService.getStudentById(id);
    }

    @PostMapping
    public StudentDto createStudentById(@RequestBody StudentDto studentDto){
        return studentService.createStudentById(studentDto);
    }

    @PutMapping(path = "/{studentId}/subject/{subjectId}")
    public StudentDto allocateCourseSubjectToStudent(@PathVariable Long studentId, @PathVariable Long subjectId){
        return studentService.allocateCourseSubjectToStudent(studentId, subjectId);
    }
}
