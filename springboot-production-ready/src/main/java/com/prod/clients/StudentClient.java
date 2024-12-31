package com.prod.clients;

import com.prod.dto.StudentDto;

import java.util.List;

public interface StudentClient {

    public List<StudentDto> getAllStudent();

    public StudentDto getStudentById(Long id);

    public StudentDto createStudent(StudentDto studentDto);

    public StudentDto updateStudentById(StudentDto studentDto, Long id);
}
