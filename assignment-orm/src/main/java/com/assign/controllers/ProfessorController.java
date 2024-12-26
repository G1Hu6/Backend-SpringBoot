package com.assign.controllers;

import com.assign.dto.ProfessorDto;
import com.assign.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/professors")
public class ProfessorController {

    @Autowired
    public ProfessorService professorService;

    @GetMapping
    public List<ProfessorDto> getAllProfessors(){
        return professorService.getAllProfessors();
    }

    @GetMapping(path = "/{id}")
    public ProfessorDto getProfessorById(@PathVariable Long id){
        return professorService.getProfessorById(id);
    }

    @PostMapping
    public ProfessorDto createProfessorById(@RequestBody ProfessorDto professorDto){
        return professorService.createProfessorById(professorDto);
    }

    @PutMapping(path = "/{professorId}/student/{studentId}")
    public ProfessorDto allocateStudentToProfessor(@PathVariable Long professorId, @PathVariable Long studentId){
        return professorService.allocateStudentsToProfessors(professorId, studentId);
    }
}
