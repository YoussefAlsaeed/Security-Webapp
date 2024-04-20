package com.secapp.springjwt.payload.request;

import com.secapp.springjwt.models.Task;

public class CreateTaskRequest {
	
	
	private Long listId;
	private Task task;
	 
	public Long getListId() {
		return listId;
	}
	public void setListId(Long listId) {
		this.listId = listId;
	}
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	 
	 

}
