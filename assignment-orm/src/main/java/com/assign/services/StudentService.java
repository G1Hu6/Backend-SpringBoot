package com.assign.services;

import com.assign.dto.StudentDto;
import com.assign.entities.StudentEntity;
import com.assign.entities.SubjectEntity;
import com.assign.repositories.StudentRepository;
import com.assign.repositories.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<StudentDto> getAllStudents(){
        return studentRepository.findAll().stream().map(studentEntity ->modelMapper.map(studentEntity, StudentDto.class)).toList();
    }

    public StudentDto getStudentById(Long id){
        return modelMapper.map(studentRepository.findById(id).orElse(null), StudentDto.class);
    }

    public StudentDto createStudentById(StudentDto toCreateStudent){
        return modelMapper.map(studentRepository.save(modelMapper.map(toCreateStudent, StudentEntity.class)), StudentDto.class);
    }

    public StudentDto allocateCourseSubjectToStudent(Long studentId, Long subjectId) {
        Optional<SubjectEntity> subjectEntity = subjectRepository.findById(subjectId);
        Optional<StudentEntity> studentEntity = studentRepository.findById(studentId);

        return studentEntity.flatMap(student -> subjectEntity.map(subject -> {
            student.getCourseSubjects().add(subject);
            studentRepository.save(student);
            return  modelMapper.map(student, StudentDto.class);
        })).orElse(null);
    }
}
