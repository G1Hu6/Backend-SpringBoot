package com.testing.controllers;


import com.testing.TestContainerConfig;
import com.testing.dto.StudentDTO;
import com.testing.entities.StudentEntity;
import com.testing.repositories.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;

@AutoConfigureWebTestClient(timeout = "100000")
@Import(TestContainerConfig.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AbstractIntegrationTest {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    StudentRepository studentRepository;

    StudentEntity testStudent;
    StudentDTO testStudentDto;

    @Test
    @BeforeEach
    void setUp(){
        testStudent = StudentEntity.builder()
                .name("Rushi")
                .email("rushi.karle@gmail.com")
                .build();

        testStudentDto = StudentDTO.builder()
                .name("Rushi")
                .email("rushi.karle@gmail.com")
                .build();
        studentRepository.deleteAll();
    }

}
