package com.secapp.springjwt.models;

import java.util.HashSet;
import java.util.Set;

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

	@Size(max = 500)
	private String description;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ETaskStatus status;

	@ManyToOne
	@JoinColumn(name = "list_id", nullable = false)
	private List list;
	
	public Task() {
	}

	public Task(Long id, @NotBlank @Size(max = 100) String title, @Size(max = 500) String description,
			ETaskStatus status, List list) {
		this.id = id;
		this.title = title;
		this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ETaskStatus getStatus() {
		return status;
	}

	public void setStatus(ETaskStatus status) {
		this.status = status;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}
	
	
  
  
}