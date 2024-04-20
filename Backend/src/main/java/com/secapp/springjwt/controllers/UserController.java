package com.secapp.springjwt.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.secapp.springjwt.models.*;
import com.secapp.springjwt.payload.request.CreateListRequest;
import com.secapp.springjwt.payload.request.CreateTaskRequest;
import com.secapp.springjwt.payload.request.DeleteListRequest;
import com.secapp.springjwt.payload.request.DeleteTaskRequest;
import com.secapp.springjwt.payload.request.GetUserListsRequests;
import com.secapp.springjwt.payload.request.UpdateTaskStatusRequest;
import com.secapp.springjwt.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
@PreAuthorize("hasRole('USER')")
public class UserController {

    @Autowired
    private UserService userService;


    @SecurityRequirement(name="BearerAuth")
    @PostMapping("/createList")
    public ResponseEntity<?> createList(@RequestBody CreateListRequest request) {
        TodoList newList = userService.createList(request.getUserId(), request.getTitle());
        if (newList != null) {
            return ResponseEntity.ok(newList);
        } else {
            return ResponseEntity.badRequest().body("Failed to create list. User not found.");
        }
    }
    
    @SecurityRequirement(name="BearerAuth")
    @DeleteMapping("/deleteList")
    public ResponseEntity<?> deleteList(@RequestBody DeleteListRequest request) {
        userService.deleteList(request.getListId());
        return ResponseEntity.ok("List deleted successfully.");
    }

    @SecurityRequirement(name="BearerAuth")
    @PostMapping("/createTask")
    public ResponseEntity<?> createTask(@RequestBody CreateTaskRequest request) {
        Task createdTask = userService.createTask(request.getListId(),request.getTitle(),request.getStatus());
        if (createdTask != null) {
            return ResponseEntity.ok(createdTask);
        } else {
            return ResponseEntity.badRequest().body("Failed to create task. List not found.");
        }
    }

    @SecurityRequirement(name="BearerAuth")
    @DeleteMapping("/deleteTask")
    public ResponseEntity<?> deleteTask(@RequestBody DeleteTaskRequest request) {
        userService.deleteTask(request.getTaskId());
        return ResponseEntity.ok("Task deleted successfully.");
    }

    @SecurityRequirement(name="BearerAuth")
    @PutMapping("/updateTaskStatus")
    public ResponseEntity<?> updateTaskStatus(@RequestBody UpdateTaskStatusRequest request) {
        Task updatedTask = userService.updateTaskStatus(request.getTaskId(), request.getStatus());
        if (updatedTask != null) {
            return ResponseEntity.ok(updatedTask);
        } else {
            return ResponseEntity.badRequest().body("Failed to update task status. Task not found.");
        }
    }
    
    @SecurityRequirement(name="BearerAuth")
    @GetMapping("/lists")
    public ResponseEntity<?> getUserLists(@RequestBody GetUserListsRequests requestBody) {
      Long userId = requestBody.getUserId();
      if (userId != null) {
        List<TodoList> userLists = userService.getUserLists(userId);
        return ResponseEntity.ok(userLists);
      } else {
        return ResponseEntity.badRequest().body("Invalid request body.");
      }
    }

}