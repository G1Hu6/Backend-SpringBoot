package com.springboot_mvc.services;

import com.springboot_mvc.dto.StudentDTO;
import com.springboot_mvc.entities.StudentEntity;
import com.springboot_mvc.repositories.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ModelMapper modelMapper;
    // ModelMapper class is used to convert StudentEntity into StudentDTO

    public StudentDTO getStudentById(Long id){
        StudentEntity studentEntity = studentRepository.findById(id).orElse(null);
        //return new StudentDTO(studentEntity.getName(),studentEntity.getAddress(),studentEntity.getId(),studentEntity.getIsPassed(),studentEntity.getResultDate());

        return modelMapper.map(studentEntity, StudentDTO.class);
    }

    public StudentDTO insertStudent(StudentDTO studentDTO){
        // Here we perform different operations such as
        // log in...

        StudentEntity toSaveEntity = modelMapper.map(studentDTO,StudentEntity.class);
        studentRepository.save(toSaveEntity);
        return modelMapper.map(toSaveEntity,StudentDTO.class);
    }

    public List<StudentDTO> getAllStudents(){
        List<StudentEntity> allStudents = studentRepository.findAll();
        return allStudents
                .stream()
                .map(studentEntity -> modelMapper.map(studentEntity,StudentDTO.class))
                .collect(Collectors.toList());
    }

}
