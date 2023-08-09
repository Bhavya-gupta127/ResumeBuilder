package com.example.resume;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ResumeBuilderApplicationTests {

	@Test
	void contextLoads() {
		ConfigurableApplicationContext context = SpringApplication.run(ResumeBuilderApplication.class);
		assertNotNull(context);
		context.close();
	}

}
