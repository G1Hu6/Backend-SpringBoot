package com.assign.dto;

import com.assign.entities.StudentEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdmissionRecordDto {

    private Long id;

    private Double fees;

    @JsonIgnore
    private StudentEntity student;
}
