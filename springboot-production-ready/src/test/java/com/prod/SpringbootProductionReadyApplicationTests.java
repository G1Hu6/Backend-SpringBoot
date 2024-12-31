package com.prod;

import com.prod.clients.StudentClient;
import com.prod.clients.impl.StudentClientImpl;
import com.prod.dto.StudentDto;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
class SpringbootProductionReadyApplicationTests {

	@Autowired
	private StudentClientImpl studentClient;

	@Test
	void contextLoads() {
	}

	@Test
	void getAllStudents(){
		System.out.println(studentClient.getAllStudent());
	}

	@Test
	void getStudentById(){
		System.out.println(studentClient.getStudentById(1L));
	}

	@Test
	void createStudent(){
		StudentDto student = StudentDto.builder()
						.age(19).email("djskfjsdbf@gmail.com").name("Virat King 18").fees(100000.0).role("USER").address("Delhi").isPassed(false).resultDate(LocalDate.now()).build();
		System.out.println(studentClient.createStudent(student));
	}

}
