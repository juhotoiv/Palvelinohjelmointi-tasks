package com.pohjelmointi.tasks.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Assignee {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long assigneeid;
	private String name;
	
	@JsonBackReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "assignee")
	private List<Task> tasks;
	
	public Assignee() {}
	
	public Assignee(String name) {
		super();
		this.name = name;
	}

	public Long getAssigneeid() {
		return assigneeid;
	}

	public void setAssigneeid(Long assigneeid) {
		this.assigneeid = assigneeid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	@Override
	public String toString() {
		return "Assignee [assigneeid=" + assigneeid + ", name=" + name + "]";
	}
}
