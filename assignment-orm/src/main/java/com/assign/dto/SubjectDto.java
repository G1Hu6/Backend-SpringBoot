package com.assign.dto;

import com.assign.entities.AdmissionRecordEntity;
import com.assign.entities.ProfessorEntity;
import com.assign.entities.StudentEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDto {

    private Long id;

    private String title;

    @JsonIgnore
    private ProfessorEntity subjectTeacher;

    @JsonIgnore
    private Set<StudentEntity> learningStudents;
}
