package com.pohjelmointi.tasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import com.pohjelmointi.tasks.domain.Assignee;
import com.pohjelmointi.tasks.domain.AssigneeRepository;
import com.pohjelmointi.tasks.domain.Kayttaja;
import com.pohjelmointi.tasks.domain.KayttajaRepository;
import com.pohjelmointi.tasks.domain.Status;
import com.pohjelmointi.tasks.domain.StatusRepository;
import com.pohjelmointi.tasks.domain.Task;
import com.pohjelmointi.tasks.domain.TaskRepository;

@SpringBootApplication
public class TasksApplication {
	@Bean
    public Java8TimeDialect java8TimeDialect() {
        return new Java8TimeDialect();
    }
	
	private static final Logger log = LoggerFactory.getLogger(TasksApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TasksApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner TaskDemo(TaskRepository trepository, StatusRepository srepository, AssigneeRepository arepository, KayttajaRepository krepository) {
		return (args) -> {
			// drop old data
			trepository.deleteAll();
			srepository.deleteAll();
			arepository.deleteAll();
			krepository.deleteAll();
			
			log.info("save demo statuses to db");
			srepository.save(new Status("To do"));
			srepository.save(new Status("Working on"));
			srepository.save(new Status("To be reviewed"));
			srepository.save(new Status("Done"));
			
			log.info("save demo assignees to db");
			arepository.save(new Assignee("Pekka"));
			arepository.save(new Assignee("Maija"));
			arepository.save(new Assignee("Matti"));
			
			log.info("save demo tasks to db");
			trepository.save(new Task("Tietokanta", "Suunnittele tasklist tietokantarakenne", LocalDate.of(2020, 4, 23), srepository.findByTitle("Done").get(0), arepository.findByName("Pekka").get(0)));
			trepository.save(new Task("Backend", "Toteuta tasklist backend", LocalDate.of(2020, 4, 26), srepository.findByTitle("Working on").get(0), arepository.findByName("Maija").get(0)));
			trepository.save(new Task("Rest-rajapinta", "Toteuta rest-rajapinta", LocalDate.of(2020, 4, 29), srepository.findByTitle("To be reviewed").get(0), arepository.findByName("Pekka").get(0)));
			trepository.save(new Task("Käyttöliittymä", "Toteuta tasklist frontend", LocalDate.of(2020, 4, 27), srepository.findByTitle("To do").get(0), arepository.findByName("Matti").get(0)));
				
			log.info("add demo users");
			Kayttaja user1 = new Kayttaja("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			Kayttaja user2 = new Kayttaja("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			krepository.save(user1);
			krepository.save(user2);
		};
	}

}
