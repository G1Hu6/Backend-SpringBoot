package com.testing.controllers;

import com.testing.TestContainerConfig;
import com.testing.advices.ApiResponse;
import com.testing.dto.StudentDTO;
import com.testing.entities.StudentEntity;
import com.testing.repositories.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class DBControllerIntegrationTest extends AbstractIntegrationTest{
    @Autowired
    private ModelMapper modelMapper;

    //@Test
    void testGetStudentById_whenStudentIsExists_thenReturnStudent(){
        StudentEntity savedStudent = studentRepository.save(testStudent);

//  Way 1 :-
//                webTestClient.get()
//                .uri("/db/students/{stdId}", savedStudent.getId())
//                .exchange()                                                  // Executes the request and returns WebTestClient(ResponseSpec)
//                .expectStatus().isOk()                                       // Assert the status code of response
//                .expectBody()                                                // Assert the body of response
//                .jsonPath("$.data")
//                .isEqualTo(testStudentDto);

// Way 2 :-
        webTestClient.get()
                .uri("/db/students/{stdId}", savedStudent.getId())
                .exchange()                                                  // Executes the request and returns WebTestClient(ResponseSpec)
                .expectStatus().isOk()                                       // Assert the status code of response
                .expectBody(ApiResponse.class)
                .value(apiResponse -> {
                    HashMap linkedHashMap = (HashMap) apiResponse.getData();
                    log.info("Api Response -> data : {}", linkedHashMap.get("email"));
                    assertThat(linkedHashMap.get("email")).isEqualTo(testStudentDto.getEmail());
                    assertThat(linkedHashMap.get("id")).isEqualTo(testStudentDto.getId());
                });

    }

    @Test
    void testGetStudentById_whenStudentIsNotExists_thenThrowException(){
        webTestClient.get()
                .uri("/db/students/1")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testInsertStudent_whenStudentAlreadyExists_thenThrowException(){
        StudentEntity savedStudent = studentRepository.save(testStudent);

        webTestClient.post()
                .uri("/db/students")
                .bodyValue(testStudentDto)
                .exchange()
                .expectStatus().isNotFound();
        // throw RuntimeException -> .expectStatus().is5XXInternalServerError()
    }

    //@Test
    void testInsertStudent_whenStudentDoesNotExists_thenCreateNewStudent(){
        webTestClient.post()
                .uri("/db/students")
                .bodyValue(testStudentDto)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.data")
                .isEqualTo(testStudentDto);
    }

    @Test
    void testUpdateStudentById_whenStudentDoesNotExists_thenThrowException(){
        webTestClient.put()
                .uri("/db/students/9999")
                .bodyValue(testStudentDto)                                                // Executes the request and returns WebTestClient(ResponseSpec)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdateStudentById_whenStudentExists_thenUpdateStudent(){
        StudentEntity savedStudent = studentRepository.save(testStudent);
        testStudentDto.setId(savedStudent.getId());
        testStudentDto.setName("Kamalesh");
        webTestClient.put()
                .uri("/db/students/{stdId}", savedStudent.getId())
                .bodyValue(testStudentDto)                                                // Executes the request and returns WebTestClient(ResponseSpec)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.data")
                .isEqualTo(testStudentDto);
    }

    @Test
    void testDeleteStudentById_whenStudentIsPresent_thenDeleteStudent(){
        StudentEntity savedStudent = studentRepository.save(testStudent);
        webTestClient.delete()
                .uri("/db/students/{stdId}", savedStudent.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.data")
                .isEqualTo(true);
    }

    @Test
    void testDeleteStudentById_whenStudentIsNotPresent_thenThrowException(){

        webTestClient.delete()
                .uri("/db/students/7988")
                .exchange()
                .expectStatus().isNotFound();
    }
}