package com.pohjelmointi.tasks.domain;

import java.time.LocalDate;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String title;
	private String description;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate deadline;
	
	@ManyToOne
	@JsonManagedReference
    @JoinColumn(name = "statusid")
    private Status status;
	
	@ManyToOne
	@JsonManagedReference
    @JoinColumn(name = "assigneeid")
    private Assignee assignee;

	public Task() {
	}

	public Task(String title, String description, LocalDate deadline, Status status, Assignee assignee) {
		this.title = title;
		this.description = description;
		this.deadline = deadline;
		this.status = status;
		this.assignee = assignee;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public Assignee getAssignee() {
		return assignee;
	}

	public void setAssignee(Assignee assignee) {
		this.assignee = assignee;
	}

	@Override
	public String toString() {
		return ("Task id=" + id + ", title=" + title + ", description=" + description + ", deadline=" + deadline.toString() + ", status=" + this.status.getTitle() + ", assignee=" + this.assignee.getName());
	}
}