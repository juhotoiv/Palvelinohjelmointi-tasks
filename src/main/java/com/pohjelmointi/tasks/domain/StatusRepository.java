package com.pohjelmointi.tasks.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface StatusRepository extends CrudRepository<Status, Long>{
	
	List<Status> findByTitle(String title);
	
}
