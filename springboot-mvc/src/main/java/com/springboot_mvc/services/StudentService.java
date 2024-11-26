package com.springboot_mvc.services;

import com.springboot_mvc.dto.StudentDTO;
import com.springboot_mvc.entities.StudentEntity;
import com.springboot_mvc.repositories.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ModelMapper modelMapper;
    // ModelMapper class is used to convert StudentEntity into StudentDTO

    public Optional<StudentDTO> getStudentById(Long id){
        Optional<StudentEntity> studentEntity = studentRepository.findById(id);
        //return new StudentDTO(studentEntity.getName(),studentEntity.getAddress(),studentEntity.getId(),studentEntity.getIsPassed(),studentEntity.getResultDate());

        return studentEntity.map(studentEntity1 -> modelMapper.map(studentEntity1,StudentDTO.class));
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

    public Boolean isStudentExistsById(Long id){
        return studentRepository.existsById(id);
    }

    public StudentDTO updateStudentById(StudentDTO studentDTO, Long id){
        StudentEntity studentEntity = modelMapper.map(studentDTO, StudentEntity.class);
        studentEntity.setId(id);
        return modelMapper.map(studentRepository.save(studentEntity), StudentDTO.class);
    }

    public StudentDTO partiallyUpdateStudentById(Map<String, Object> updates, Long id){
        boolean isExists = isStudentExistsById(id);
        if(!isExists)return null;
        StudentEntity studentEntity = studentRepository.findById(id).get(); // Not null
        updates.forEach((field,value) -> {
            Field fieldToBeUpdate =  ReflectionUtils.findField( StudentEntity.class, field);
            if(fieldToBeUpdate != null){
                fieldToBeUpdate.setAccessible(true);
                ReflectionUtils.setField(fieldToBeUpdate,studentEntity,value);
            }
        });

        return modelMapper.map(studentRepository.save(studentEntity), StudentDTO.class);
    }

    public Boolean deleteStudentById(Long id){
        boolean isExists = isStudentExistsById(id);
        if(!isExists)return false;
        studentRepository.deleteById(id);
        return true;
    }
}
