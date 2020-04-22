package com.pohjelmointi.tasks;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pohjelmointi.tasks.domain.Assignee;
import com.pohjelmointi.tasks.domain.AssigneeRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AssigneeRepositoryTests {

    @Autowired
    private AssigneeRepository repository;

    @Test
    public void findByNameShouldReturnAssignee() {
        List<Assignee> assignees = repository.findByName("Maija");

        assertThat(assignees).hasSize(1);
    }

    @Test
    public void createNewAssignee() {
    	Assignee assignee = new Assignee("Liisa");
    	repository.save(assignee);
    	assertThat(repository.findByName("Liisa").get(0).getAssigneeid()).isNotNull();
    }    

} 