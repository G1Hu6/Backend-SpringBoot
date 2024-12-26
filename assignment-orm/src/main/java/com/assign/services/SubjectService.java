package com.assign.services;

import com.assign.dto.ProfessorDto;
import com.assign.dto.SubjectDto;
import com.assign.entities.ProfessorEntity;
import com.assign.entities.SubjectEntity;
import com.assign.repositories.ProfessorRepository;
import com.assign.repositories.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<SubjectDto> getAllSubjects(){
        return subjectRepository.findAll().stream().map(subjectEntity -> modelMapper.map(subjectEntity, SubjectDto.class)).toList();
    }

    public SubjectDto getSubjectById(Long id){
        return modelMapper.map(subjectRepository.findById(id).orElse(null), SubjectDto.class);
    }

    public SubjectDto createSubjectById(SubjectDto toCreateSubject){
        return modelMapper.map(subjectRepository.save(modelMapper.map(toCreateSubject, SubjectEntity.class)), SubjectDto.class);
    }

    public ProfessorDto allocateSubjectTeacher(Long subjectId, Long professorId) {
        Optional<SubjectEntity> subjectEntity = subjectRepository.findById(subjectId);
        Optional<ProfessorEntity> professorEntity = professorRepository.findById(professorId);

        return professorEntity.flatMap(professor -> subjectEntity.map(subject -> {

            subject.setSubjectTeacher(professor);
            subjectRepository.save(subject);
            professor.getSubjects().add(subject);
            return modelMapper.map(professor, ProfessorDto.class);
        })).orElse(null);
    }

    public ProfessorDto getAllocatedProfessorOfSubject(Long subjectId) {
        Optional<SubjectEntity> subjectEntity = subjectRepository.findById(subjectId);
        return subjectEntity.map(subject-> {
            ProfessorEntity professorEntity = subject.getSubjectTeacher();
            if(professorEntity != null){
                return modelMapper.map(professorEntity, ProfessorDto.class);
            }
            return null;
        }).orElse(null);
    }
}
