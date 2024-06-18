package com.secapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.secapp.models.TodoList;
import com.secapp.models.User;

@Repository
public interface ListRepository extends JpaRepository<TodoList, Long> {

	List<TodoList> findByUser(User user);
}