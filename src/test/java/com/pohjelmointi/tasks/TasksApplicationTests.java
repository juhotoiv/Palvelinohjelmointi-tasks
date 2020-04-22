package com.pohjelmointi.tasks;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pohjelmointi.tasks.net.TaskController;
import com.pohjelmointi.tasks.net.UserController;

@SpringBootTest
class TasksApplicationTests {
	
	@Autowired
	private TaskController tcontroller;

	@Autowired
	private UserController ucontroller;

	// Smoketests for controllers
	@Test
	void contextLoads() {
		assertThat(tcontroller).isNotNull();
		assertThat(ucontroller).isNotNull();
	}

}
