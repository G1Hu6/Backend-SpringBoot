package com.springboot_mvc.dto;

import java.util.Date;

// Simple DTO(Data Transfer Object) for communicate between client and controllers
public class StudentDTO {

    private String name;

    private String address;

    private Long id;

    private Boolean isPassed;

    private Date resultDate;

    // Constructors
    public StudentDTO() {

    }

    public StudentDTO(String name, String address, Long id, Boolean isPassed, Date resultDate) {
        this.name = name;
        this.address = address;
        this.id = id;
        this.isPassed = isPassed;
        this.resultDate = resultDate;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getResultDate() {
        return resultDate;
    }

    public void setResultDate(Date resultDate) {
        this.resultDate = resultDate;
    }

    public Boolean getPassed() {
        return isPassed;
    }

    public void setPassed(Boolean passed) {
        isPassed = passed;
    }
}