package com.secapp.springjwt.models;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tasks")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 100)
	private String title;


	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ETaskStatus status;

	@ManyToOne
	@JoinColumn(name = "list_id", nullable = false)
	@JsonIgnore
	private TodoList list;

	public Task() {
	}

	public Task(Long id, @NotBlank @Size(max = 100) String title,
			ETaskStatus status, TodoList list) {
		this.id = id;
		this.title = title;
		this.status = status;
		this.list = list;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public ETaskStatus getStatus() {
		return status;
	}

	public void setStatus(ETaskStatus status) {
		this.status = status;
	}

	public TodoList getList() {
		return list;
	}

	public void setList(TodoList list) {
		this.list = list;
	}




}