package com.secapp.springjwt.payload.request;

import com.secapp.springjwt.models.ETaskStatus;

public class UpdateTaskStatusRequest {
    private Long taskId;
    private ETaskStatus status;
	public Long getTaskId() {
		return taskId;
	}
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
	public ETaskStatus getStatus() {
		return status;
	}
	public void setStatus(ETaskStatus status) {
		this.status = status;
	}
    
}
