package com.assign.dto;

import com.assign.entities.AdmissionRecordEntity;
import com.assign.entities.ProfessorEntity;
import com.assign.entities.SubjectEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    private Long id;

    private String name;

    @JsonIgnore
    private Set<ProfessorEntity> teachingProfessors;

    private Set<SubjectEntity> courseSubjects;

    private AdmissionRecordEntity admissionRecord;
}
