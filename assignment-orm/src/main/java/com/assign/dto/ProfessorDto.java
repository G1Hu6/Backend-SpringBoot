package com.assign.dto;

import com.assign.entities.StudentEntity;
import com.assign.entities.SubjectEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDto {

    private Long id;

    private String name;

    private Set<SubjectEntity> subjects;

    private Set<StudentEntity> students;
}
