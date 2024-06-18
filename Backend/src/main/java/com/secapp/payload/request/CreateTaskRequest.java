package com.secapp.payload.request;

import com.secapp.models.ETaskStatus;
import com.secapp.models.Task;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateTaskRequest {


	private Long listId;
	@NotBlank
	@Size(max = 100)
	private String title;


	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ETaskStatus status;

	public Long getListId() {
		return listId;
	}
	public void setListId(Long listId) {
		this.listId = listId;
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
	



}