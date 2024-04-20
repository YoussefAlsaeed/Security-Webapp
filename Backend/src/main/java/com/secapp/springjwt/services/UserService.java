package com.secapp.springjwt.services;

import com.secapp.springjwt.models.*;
import com.secapp.springjwt.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ListRepository listRepository;

  @Autowired
  private TaskRepository taskRepository;


  public TodoList createList(Long userId, String title) {
      Optional<User> userOptional = userRepository.findById(userId);
      if (userOptional.isPresent()) {
          User user = userOptional.get();
          TodoList list = new TodoList();
          list.setTitle(title);
          list.setUser(user);
          return listRepository.save(list);
      }
      return null;
  }
  
  public void deleteList(Long listId) {
	    listRepository.deleteById(listId);
	  }
  
  public Task createTask(Long listId, String title, ETaskStatus status) {
	    Optional<TodoList> listOptional = listRepository.findById(listId);
	    if (listOptional.isPresent()) {
	    	Task task = new Task();
	    	task.setStatus(status);
	    	task.setTitle(title);
	    	TodoList list = listOptional.get();
	    	task.setList(list);
	    	return taskRepository.save(task);
	    }
	    return null;
	  }

	  public void deleteTask(Long taskId) {
	    taskRepository.deleteById(taskId);
	  }

	  public Task updateTaskStatus(Long taskId, ETaskStatus status) {
	    Optional<Task> taskOptional = taskRepository.findById(taskId);
	    if (taskOptional.isPresent()) {
	      Task task = taskOptional.get();
	      task.setStatus(status);
	      return taskRepository.save(task);
	    }
	    return null;
	  }
	  
	  public List<TodoList> getUserLists(Long userId) {
		    Optional<User> userOptional = userRepository.findById(userId);
		    if (userOptional.isPresent()) {
		      return listRepository.findByUser(userOptional.get());
		    }
		    return Collections.emptyList();
		  }
}