package com.secapp.springjwt.payload.request;

public class AssignAdminRequest {
	Long userId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public AssignAdminRequest(Long userId) {
		this.userId = userId;
	}

	public AssignAdminRequest() {
	}

	

}