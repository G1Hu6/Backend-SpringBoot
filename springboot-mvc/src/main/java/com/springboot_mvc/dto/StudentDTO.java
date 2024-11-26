package com.springboot_mvc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
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

    @NotBlank(message = "Name must be not null and valid")
    @Size(min = 4, max = 10 , message = "Name having range in [4, 10]")
    private String name;

    @Email(message = "Email must be valid")
    @NotBlank(message = "Email must be not null and valid")
    private String email;

    @NotBlank(message = "Address must be not null and valid")
    private String address;

    @NotNull(message = "Fees must not be null")
    @Positive
    @Digits(integer = 6, fraction = 2, message = "Fees must be in format XXXXXX.YY")
    @DecimalMin(value = "1000.00")
    private Double fees;

    @Max(value = 30, message = "Maximum value of age 30")
    @Min(value = 18, message = "Minimum value of age 18")
    private Integer age;

    @Pattern(regexp = "^(USER|ADMIN)$", message = "Role must be ADMIN or USER")
    @NotBlank(message = "Role must be not null and valid")
    private String role;

    private Long id;

    @JsonProperty("isPassed")
    private Boolean isPassed;

    @PastOrPresent
    private LocalDate resultDate;
}
