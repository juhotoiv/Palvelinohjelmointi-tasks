package com.pohjelmointi.tasks.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TaskRepository extends CrudRepository<Task, Long>{
	
	List<Task> findByTitle(String title);
	List<Task> findByAssignee(Assignee assignee);
	
}
