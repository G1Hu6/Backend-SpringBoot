package com.testing.services;

import com.testing.TestContainerConfig;
import com.testing.dto.StudentDTO;
import com.testing.entities.StudentEntity;
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

import static org.assertj.core.api.Assertions.assertThat;
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
}