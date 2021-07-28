package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Currently disabled main configuration by commenting
 * annotation @SpringBootTest as we are not doing integration testing now.
 * 
 * Integration Testing: Ideally, we should keep the integration tests separated
 * from the unit tests and should not run along with the unit tests. We can do
 * this by using a different profile to only run the integration tests. A couple
 * of reasons for doing this could be that the integration tests are
 * time-consuming and might need an actual database to execute.
 */

//@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
	}

}
