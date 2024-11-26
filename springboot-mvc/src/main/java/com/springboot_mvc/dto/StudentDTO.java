package com.springboot_mvc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

// Simple DTO(Data Transfer Object) for communicate between client and controllers
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    private String name;

    private String address;

    private Long id;

    @JsonProperty("isPassed")
    private Boolean isPassed;

    private LocalDate resultDate;
}
