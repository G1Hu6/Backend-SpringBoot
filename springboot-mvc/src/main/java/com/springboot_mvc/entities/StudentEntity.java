package com.springboot_mvc.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
/*
    lombok dependency is handling Getters, Setters and Constructors by itself.
*/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
