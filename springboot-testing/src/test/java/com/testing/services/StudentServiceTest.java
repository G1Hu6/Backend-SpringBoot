package com.testing.services;

import com.testing.TestContainerConfig;
import com.testing.dto.StudentDTO;
import com.testing.entities.StudentEntity;
import com.testing.exceptions.ResponseNotFoundException;
import com.testing.repositories.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Slf4j
//@SpringBootTest
//@DataJpaTest is used for repositories only.

@Import(TestContainerConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(MockitoExtension.class)
class StudentServiceTest{

    // (*)Mocking
    @Mock
    private StudentRepository studentRepository;

    // Spy is used when we need these dependency as it is defined.
    @Spy
    private ModelMapper mapper;

    @InjectMocks
    private StudentService studentService;
    private StudentEntity mockedStudent;
    private StudentDTO mockedStudentDto;

    @BeforeEach
    void setUp(){
        log.info("Mocking student entity and dto objects...");
        mockedStudent = StudentEntity.builder()
                .id(1L)
                .name("Pranav Patil")
                .email("pranav@gmail.com")
                .build();

        mockedStudentDto = mapper.map(mockedStudent, StudentDTO.class);
    }

    @Test
    void testGetStudentById_whenIdIsValid_thenReturnStudentDto(){
        log.info("Testing getStudentById...");
        Long id = mockedStudent.getId();
        // 1. Assign
        // (*)Stubbing
        when(studentRepository.findById(id)).thenReturn(Optional.of(mockedStudent));
        // 2. Act
        StudentDTO student = studentService.getStudentById(id);
        log.info("Student : {}", student );
        // 3. Assert
        assertThat(student).isNotNull();
        assertThat(student.getId()).isEqualTo(id);
        assertThat(student.getEmail()).isEqualTo(mockedStudent.getEmail());
        // (*)Verifying methods
        verify(studentRepository).findById(id);
//        verify(studentRepository).save(any()); // Test case failed
//        verify(studentRepository, never()).save(any()); // Test case passed
//        verify(studentRepository, times(1)).findById(id); // Test case passed
//        verify(studentRepository, atLeast(1)).findById(id);
//        verify(studentRepository, atMost(10)).findById(id);
    }

    @Test
    void testGetStudentById_whenIdIsNotValid_thenThrowException(){
        // 1. Assign
        when(studentRepository.findById(anyLong())).thenReturn(Optional.empty());
        // 2. Act and 3. Assert
        assertThatThrownBy(() -> {studentService.getStudentById(1L);})
                .isInstanceOf(ResponseNotFoundException.class)
                .hasMessage("Student not found with id : 1");
    }

    @Test
    void testInsertStudent_whenStudentIsValid_thenCreateNewStudent(){

        // 1. Assign
        when(studentRepository.findByEmail(anyString())).thenReturn(List.of());
        when(studentRepository.save(any(StudentEntity.class))).thenReturn(mockedStudent);
        // 2. Act
        StudentDTO studentDTO = studentService.insertStudent(mockedStudentDto);

        // 3. Assert
        assertThat(studentDTO).isNotNull();
        assertThat(studentDTO.getEmail()).isEqualTo(mockedStudentDto.getEmail());
        // (*)Argument Captor
        ArgumentCaptor<StudentEntity> argumentCaptor = ArgumentCaptor.forClass(StudentEntity.class);
        verify(studentRepository).save(argumentCaptor.capture());
        StudentEntity capturedStudentArgument = argumentCaptor.getValue();
        assertThat(studentDTO.getEmail()).isEqualTo(capturedStudentArgument.getEmail());
    }

    @Test
    void testInsertStudent_whenStudentIsNotValid_thenThrowException(){
        when(studentRepository.findByEmail(mockedStudentDto.getEmail()))
                .thenReturn(List.of(mockedStudent));

        assertThatThrownBy(() -> studentService.insertStudent(mockedStudentDto))
                .isInstanceOf(ResponseNotFoundException.class)
                .hasMessage("Student already exists with email :" + mockedStudentDto.getEmail());

        verify(studentRepository, only()).findByEmail(mockedStudentDto.getEmail());
        verify(studentRepository, never()).save(any(StudentEntity.class));
    }

    @Test
    void testUpdateStudentById_whenStudentIsValid_thenUpdateStudent(){
        when(studentRepository.existsById(mockedStudentDto.getId())).thenReturn(true);
        mockedStudentDto.setName("Dhiraj");
        StudentEntity newStudent = mapper.map(mockedStudentDto, StudentEntity.class);
        when(studentRepository.save(any())).thenReturn(newStudent);

        StudentDTO updatedStudent = studentService.updateStudentById(mockedStudentDto, mockedStudentDto.getId());
        assertThat(updatedStudent).isEqualTo(mockedStudentDto);
        verify(studentRepository).save(any());
        verify(studentRepository).existsById(1L);
    }

    @Test
    void testUpdateStudentById_whenStudentIsNotValid_thenThrowException(){
        when(studentRepository.existsById(mockedStudentDto.getId())).thenReturn(false);
        assertThatThrownBy(() -> studentService.updateStudentById(mockedStudentDto,mockedStudentDto.getId()))
                .isInstanceOf(ResponseNotFoundException.class)
                .hasMessage("Student not found with id : 1");

        verify(studentRepository, never()).save(any());
        verify(studentRepository).existsById(1L);
    }

    @Test
    void testDeleteStudentById_whenStudentIsExists_thenDeleteStudent(){
        when(studentRepository.existsById(1L)).thenReturn(false);
        assertThatThrownBy(() -> studentService.deleteStudentById(1L))
                .isInstanceOf(ResponseNotFoundException.class)
                .hasMessage("Student not found with id : 1");

        verify(studentRepository, never()).deleteById(1L);
        verify(studentRepository).existsById(1L);
    }

    @Test
    void testDeleteStudentById_whenStudentIsNotExists_thenThrowException(){
        when(studentRepository.existsById(1L)).thenReturn(true);
        assertThatCode(() -> studentService.deleteStudentById(1L)).doesNotThrowAnyException();

        verify(studentRepository).deleteById(1L);
    }
}