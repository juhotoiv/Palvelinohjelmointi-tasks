package com.pohjelmointi.tasks;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pohjelmointi.tasks.domain.StatusRepository;
import com.pohjelmointi.tasks.domain.Task;
import com.pohjelmointi.tasks.domain.TaskRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TaskRepositoryTests {

    @Autowired
    private TaskRepository repository;
    
    @Autowired
    private StatusRepository srepository;

    @Test
    public void findByTitleShouldReturnTask() {
        List<Task> tasks = repository.findByTitle("Tietokanta");

        assertThat(tasks).hasSize(1);
        assertThat(tasks.get(0).getStatus()).isEqualTo(srepository.findByTitle("Done").get(0));
    }

} 