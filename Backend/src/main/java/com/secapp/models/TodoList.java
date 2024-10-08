package com.secapp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "lists")
public class TodoList {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @NotBlank
   @Size(max = 100)
   private String title;

   @ManyToOne
   @JoinColumn(name = "user_id", nullable = false)
   @JsonIgnore
   private User user;

   @OneToMany(mappedBy = "list", cascade = CascadeType.ALL, orphanRemoval = true)
   private Set<Task> tasks = new HashSet<>();

   public TodoList() {
   }

   public TodoList(Long id, @NotBlank @Size(max = 100) String title) {
	   this.id = id;
	   this.title = title;

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

   public User getUser() {
	   return user;
   }

   public void setUser(User user) {
	   this.user = user;
   }

   public Set<Task> getTasks() {
	   return tasks;
   }

   public void setTasks(Set<Task> tasks) {
	   this.tasks = tasks;
   }


}