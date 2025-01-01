package com.prod.clients.impl;

import com.prod.advices.ApiResponse;
import com.prod.clients.StudentClient;
import com.prod.dto.StudentDto;
import com.prod.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentClientImpl implements StudentClient {

    private final RestClient restClient;
    Logger log = LoggerFactory.getLogger(StudentClientImpl.class);


    @Override
    public List<StudentDto> getAllStudent(){
//        log.error("Error Log");
//        log.warn("Warn Log");
//        log.info("Executing getAllStudents Method...");
//        log.debug("Debug Log");
//        log.trace("Trace Log");

        try{
            log.info("Executing getAllStudents Method...");
            ApiResponse<List<StudentDto>> allStudents = restClient.get()
                    .uri("db/students")
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,(request, response) -> {
                        log.error("4xxERROR occurred at getAllStudent , Error : {}", new String(response.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("Students Not found in database");
                    })
                    .body(new ParameterizedTypeReference<>(){});

            log.info("SUCCESS in getAllStudents Method...");
            return allStudents.getData();
        } catch (Exception e) {
            log.error("Client Error getAllStudent Method...");
            throw new RuntimeException(e);
        }
    }


    @Override
    public StudentDto getStudentById(Long id) {
        try{
            log.info("Executing getStudentById Method...");
            ApiResponse<StudentDto> studentDtoApiResponse = restClient.get()
                    .uri("db/students/{id}", id)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,(request, response) -> {
                        log.error("4xxERROR occurred at getStudentById , Error : {}",new String(response.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("Student Not Found wit id + " + id);
                    })
                    .body(new ParameterizedTypeReference<ApiResponse<StudentDto>>() {
                    });

            log.info("SUCCESS in getStudentById Method...");
            log.trace("Returned Student : {}",studentDtoApiResponse.getData());
            return studentDtoApiResponse.getData();
        } catch (Exception e) {
            log.error("Client Error Occurred getStudentById ...");
            throw new RuntimeException(e);
        }
    }

    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        try {
            log.info("Executing createStudent Method...");
            ResponseEntity<ApiResponse<StudentDto>> apiResponseResponseEntity = restClient.post()
                    .uri("db/students")
                    .body(studentDto)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,(request, response) -> {
                        log.error("4xxERROR occurred at createStudent , Error : {}",new String(response.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("Could Not Create the Student");
                    }).toEntity(new ParameterizedTypeReference<ApiResponse<StudentDto>>() {
                    });

            log.info("SUCCESS in createStudent Method...");
            log.trace("Saved Student : {}", apiResponseResponseEntity.getBody().getData());
            return apiResponseResponseEntity.getBody().getData();
        } catch (Exception e) {
            log.error("Client Error Occurred createStudent...");
            throw new RuntimeException(e);
        }
    }

    @Override
    public StudentDto updateStudentById(StudentDto studentDto, Long id) {
        return null;
    }
}
