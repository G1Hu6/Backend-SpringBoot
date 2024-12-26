package com.assign.services;

import com.assign.dto.ProfessorDto;
import com.assign.dto.SubjectDto;
import com.assign.entities.ProfessorEntity;
import com.assign.entities.StudentEntity;
import com.assign.entities.SubjectEntity;
import com.assign.repositories.ProfessorRepository;
import com.assign.repositories.StudentRepository;
import com.assign.repositories.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<ProfessorDto> getAllProfessors(){
        return professorRepository.findAll().stream().map(
                professorEntity -> modelMapper.map(professorEntity, ProfessorDto.class)
        ).toList();
    }

    public ProfessorDto getProfessorById(Long id){
        return modelMapper.map(professorRepository.findById(id).orElse(null), ProfessorDto.class);
    }

    public ProfessorDto createProfessorById(ProfessorDto toCreateProfessor){
        return modelMapper.map(professorRepository.save(modelMapper.map(toCreateProfessor, ProfessorEntity.class)), ProfessorDto.class);
    }

    public ProfessorDto allocateStudentsToProfessors(Long professorId, Long studentId) {
        Optional<ProfessorEntity> professorEntity = professorRepository.findById(professorId);
        Optional<StudentEntity> studentEntity = studentRepository.findById(studentId);

        return professorEntity.flatMap(professor-> studentEntity.map(student -> {
          professor.getStudents().add(student);
          professorRepository.save(professor);
          return modelMapper.map(professor, ProfessorDto.class);
        }) ).orElse(null);
    }
}
