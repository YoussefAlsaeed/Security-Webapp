package com.secapp.springjwt.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secapp.springjwt.models.User;
import com.secapp.springjwt.payload.request.AssignAdminRequest;
import com.secapp.springjwt.services.AdminService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
  
	@GetMapping("/board")
	@PreAuthorize("hasRole('ADMIN')")
	@SecurityRequirement(name="BearerAuth")
	public String adminAccess() {
		return "Admin Board.";
	}
	
	@GetMapping("/getUsers")
	@PreAuthorize("hasRole('ADMIN')")
	@SecurityRequirement(name="BearerAuth")
	public List<User> getAllUsers() {
		return adminService.getAllUsers();
	}
	
	@PostMapping("/assignAdmin")
	@PreAuthorize("hasRole('ADMIN')")
	@SecurityRequirement(name="BearerAuth")
	public ResponseEntity<?> assignAdminRole(@RequestBody AssignAdminRequest request) {
	    Long userId = request.getUserId();
	    boolean isAssigned = adminService.assignAdminRoleToUser(userId);
	    if (isAssigned) {
	      return ResponseEntity.ok("Admin role assigned successfully.");
	    } else {
	      return ResponseEntity.badRequest().body("Failed to assign admin role. User or role not found.");
	    }
	 }
	
	

//  @GetMapping("/user")
//  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
//  public String userAccess() {
//    return "User Content.";
//  }
//
//  @GetMapping("/mod")
//  @PreAuthorize("hasRole('MODERATOR')")
//  public String moderatorAccess() {
//    return "Moderator Board.";
//  }
//
//  @GetMapping("/admin")
//  @PreAuthorize("hasRole('ADMIN')")
//  public String adminAccess() {
//    return "Admin Board.";
//  }
}
