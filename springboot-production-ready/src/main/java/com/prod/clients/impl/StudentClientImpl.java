package com.prod.clients.impl;

import com.prod.advices.ApiResponse;
import com.prod.clients.StudentClient;
import com.prod.dto.StudentDto;
import com.prod.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
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

    @Override
    public List<StudentDto> getAllStudent(){
        try{
            ApiResponse<List<StudentDto>> allStudents = restClient.get()
                    .uri("db/students")
                    .retrieve()
                    .body(new ParameterizedTypeReference<>(){});


            return allStudents.getData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public StudentDto getStudentById(Long id) {
        try{
            ApiResponse<StudentDto> studentDtoApiResponse = restClient.get()
                    .uri("db/students/{id}", id)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,(request, response) -> {
                        System.out.println(new String(response.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("Student Not Found wit id + " + id);
                    })
                    .body(new ParameterizedTypeReference<ApiResponse<StudentDto>>() {
                    });
            return studentDtoApiResponse.getData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        try {
            ResponseEntity<ApiResponse<StudentDto>> apiResponseResponseEntity = restClient.post()
                    .uri("db/students")
                    .body(studentDto)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,(request, response) -> {
                        System.out.println(new String(response.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("Could Not Create the Student");
                    }).toEntity(new ParameterizedTypeReference<ApiResponse<StudentDto>>() {
                    });

            return apiResponseResponseEntity.getBody().getData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public StudentDto updateStudentById(StudentDto studentDto, Long id) {
        return null;
    }
}
