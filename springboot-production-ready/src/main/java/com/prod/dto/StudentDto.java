package com.prod.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class StudentDto {
    private Long id;

    private String name;

    private String email;

    private Integer age;

    private String role;

    private Double fees;

    private String address;

    @JsonProperty("isPassed")
    private Boolean isPassed;

    private LocalDate resultDate;
}
