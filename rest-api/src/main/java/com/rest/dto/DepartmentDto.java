package com.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rest.annotations.PrimeNumber;
import com.rest.annotations.ValidPassword;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {

    private Long id;

    @NotBlank(message = "Title must not be black")
    private String title;

    @JsonProperty("isActive")
    @NotNull(message = "active status must not be null")
    private Boolean isActive;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Past(message = "Date of created must be from past")
    @NotNull(message = "Date must not be null")
    private LocalDate createdAt;

    @NotBlank(message = "Password must not be null")
    @ValidPassword
    private String password;

    @CreditCardNumber(message = "Number must be valid credit card number")
    @NotNull(message = "Credit Number must not be null")
    private String creditNumber;

    @URL(message = "must be a valid Url")
    private String url;


    //Department number must be a prime number creating custom validation for it
    @Positive(message = "Number must be greater than 0")
    @PrimeNumber
    private Integer departmentNumber;

}
