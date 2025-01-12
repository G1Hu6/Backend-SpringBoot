package com.testing.dto;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Simple DTO(Data Transfer Object) for communicate between client and controllers
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    private Long id;

    @NotBlank(message = "Name must be not null and valid")
    @Size(min = 4, max = 10 , message = "Name having range in [4, 10]")
    private String name;

    @Email(message = "Email must be valid")
    @NotBlank(message = "Email must be not null and valid")
    private String email;
}
