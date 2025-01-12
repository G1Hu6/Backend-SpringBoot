package com.testing;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
@SpringBootTest
class SpringbootTestingApplicationTests {

	@Test
	void contextLoads() {
	}

	@BeforeAll
	static void setUp(){
		log.info("Setting test essentials exactly once");
	}

	@AfterAll
    static void closingUp(){
		log.info("Closing test case");
	}

	@Test
	// DisplayName annotation is used to define custom test name
	@DisplayName("testOne")
	void firstTestCase(){
		log.info("First test case is running...");

		int num1 = 10, num2 = 13;
		int actualResult = Calculator.addTwoNumbers(num1, num2);

		// org.junit.jupiter.api.Assertions
		// Junit class Assertions not provide support for method chaining for testing multiple test case
		int expectedValue = 23;
		//Assertions.assertEquals(expectedValue, actualResult);

		// org.assertj.core.api.Assertions
		assertThat(actualResult)
				.isOdd()
				.isEqualTo(expectedValue)
				.isGreaterThan(10)
				.isBetween(1, 100)
				.isCloseTo(24, Offset.offset(1));
		// importing statically
		assertThat("Mango")
				.isEqualTo("Mango")
				.startsWith("Man")
				.endsWith("go")
				.hasSize(5);
	}

	@Test
	void testCaseForHandlingArithmeticException(){
		int num1 = 10;
		int num2 = 0;

		assertThatThrownBy(()-> Calculator.divideTwoNumbers(num1, num2))
				.isInstanceOf(ArithmeticException.class)
				.hasMessage("Divide by zero...");
	}

	@Test
	@Disabled
	//These disabled test is not executed
	void disabledTestCase(){
		log.info("Disabled test case is never runs...");
	}


	@BeforeEach
	// BeforeEach annotated methods should be executed before each test case
	void testRunningBeforeEachMethod(){
		log.info("Test case running before each method...");
	}

	@AfterEach
	//AfterEach annotated methods should be executed after each test case
	void testRunningAfterEachMethod(){
		log.info("Test case running after each method...");
	}

	@Test
	@DisplayName("testTwo")
	void secondTestCase(){
		log.info("Second test case is running...");
	}
}

@Slf4j
class Calculator{

	public static int addTwoNumbers(int a, int b){
		return a + b;
	}

	public static double divideTwoNumbers(int a, int b){
		try {
			return a/b;
		} catch (ArithmeticException e) {
			log.error("Arithmetic exception occurred while executing divideTwoNumbers method : " + e.getLocalizedMessage());
			throw new ArithmeticException("Divide by zero...");
		}
	}
}
