package com.springboot_mvc.controllers;

import com.springboot_mvc.dto.StudentDTO;
import com.springboot_mvc.exceptions.ResponseNotFoundException;
import com.springboot_mvc.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/db/students")
public class DBController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudentsFromH2DB(){

        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping(path = "/{stdId}")
    public ResponseEntity<StudentDTO> getStudentByIdFromH2DB(@PathVariable Long stdId){
        Optional<StudentDTO> studentDTO = studentService.getStudentById(stdId);
        return studentDTO
                .map(studentDTO1 -> ResponseEntity.ok(studentDTO1))
                .orElseThrow(() -> new ResponseNotFoundException("Student not found with id :" + stdId));
    }

    // @Valid annotation is added to ensure that dto class must have valid fields.
    @PostMapping
    public ResponseEntity<StudentDTO> insertStudentToH2DB(@RequestBody @Valid StudentDTO student){

        return new ResponseEntity<>(studentService.insertStudent(student), HttpStatus.CREATED);
    }

    @PutMapping(path = "{stdId}")
    public ResponseEntity<StudentDTO> updateStudentByIdToH2DB(@RequestBody StudentDTO student, @PathVariable Long stdId){
        return ResponseEntity.ok(studentService.updateStudentById(student,stdId));
    }

    @PatchMapping(path = "{stdId}")
    public ResponseEntity<StudentDTO> partiallyUpdateStudentByIdToH2DB(@RequestBody Map<String, Object> updates, @PathVariable Long stdId){
        StudentDTO studentDTO = studentService.partiallyUpdateStudentById(updates,stdId);
        if(studentDTO == null)return ResponseEntity.notFound().build();
        return ResponseEntity.ok(studentDTO);
    }

    @DeleteMapping(path = "{stdId}")
    public ResponseEntity<Boolean> deleteStudentByIdToH2DB(@PathVariable Long stdId){
        Boolean isDeleted = studentService.deleteStudentById(stdId);
        if(isDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

    // Use @ExceptionHandler annotation to handle specific exception in controller
    /*
    @ExceptionHandler
    public ResponseEntity<String> handleStudentNotFound(NoSuchElementException exception){
        return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
    }*/
}
