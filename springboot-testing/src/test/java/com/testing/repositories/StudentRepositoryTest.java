package com.testing.repositories;

import com.testing.TestContainerConfig;
import com.testing.entities.StudentEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

///Use @DataJpaTest annotation
// @SpringBootTest
// @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Slf4j
@Import(TestContainerConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    private StudentEntity student;

    @Test
    @BeforeEach
    void setUp(){
        log.info("Creating student...");
        student = StudentEntity.builder()
                .name("Raj Malusare")
                .email("raj@gmail.com")
                .build();
    }

    @Test
    void testFindByEmail_whenEmailIsPresent_thenReturnStudentsList() {
        // 1. Assign/Given
        studentRepository.save(student);
        log.info("Student saved in H2/TestContainer database with email : {}", student.getEmail());
        // 2. Act/When
        List<StudentEntity> allStudents = studentRepository.findByEmail(student.getEmail());

        // 3. Assert/Then
        assertThat(allStudents)
                .isNotNull()
                .isNotEmpty();
        assertThat(allStudents.get(0).getEmail()).isEqualTo(student.getEmail());
    }

    @Test
    void testFindByEmail_whenEmailIsNotPresent_thenReturnEmptyList(){
        // 1. Assign/Given
        String email = "notfound@gmail.com";
        log.info("Checking with email : " + email + " not present in H2/TestContainer database");
        // 2. Act/When
        List<StudentEntity> allStudents = studentRepository.findByEmail(email);

        // 3. Assert/Then
        assertThat(allStudents).isNotNull();
        assertThat(allStudents).isEmpty();
    }
}