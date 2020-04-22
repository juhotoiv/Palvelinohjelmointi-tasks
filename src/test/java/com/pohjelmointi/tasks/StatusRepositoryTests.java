package com.pohjelmointi.tasks;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pohjelmointi.tasks.domain.Status;
import com.pohjelmointi.tasks.domain.StatusRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StatusRepositoryTests {

    @Autowired
    private StatusRepository repository;

    @Test
    public void findByTitleShouldReturnStatus() {
        List<Status> statuses = repository.findByTitle("Done");

        assertThat(statuses).hasSize(1);
    }

    @Test
    public void createNewStatus() {
    	Status status = new Status("Kesken");
    	repository.save(status);
    	assertThat(repository.findByTitle("Kesken").get(0).getStatusid()).isNotNull();
    }    

} 