package com.assign.controllers;

import com.assign.dto.ProfessorDto;
import com.assign.dto.SubjectDto;
import com.assign.services.SubjectService;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping
    public List<SubjectDto> getAllSubjects(){
        return subjectService.getAllSubjects();
    }

    @GetMapping(path = "/{id}")
    public SubjectDto getSubjectById(@PathVariable Long id){
        return subjectService.getSubjectById(id);
    }

    @PostMapping
    public SubjectDto createSubjectById(@RequestBody SubjectDto subjectDto){
        return subjectService.createSubjectById(subjectDto);
    }

    @PutMapping(path = "/{subjectId}/professor/{professorId}")
    public ProfessorDto allocateSubjectTeacher(@PathVariable Long subjectId, @PathVariable Long professorId){
        return subjectService.allocateSubjectTeacher(subjectId, professorId);
    }

    @GetMapping(path = "/allocatedProfessor/{subjectId}")
    public ProfessorDto getAllocatedProfessorOfSubject(@PathVariable Long subjectId){
        return subjectService.getAllocatedProfessorOfSubject(subjectId);
    }

}
