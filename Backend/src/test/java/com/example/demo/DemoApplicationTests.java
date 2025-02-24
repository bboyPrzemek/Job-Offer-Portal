package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@SpringBootTest
@ContextConfiguration(loader=AnnotationConfigContextLoader.class, classes = DemoApplicationTests.class)

class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

}
