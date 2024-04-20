package com.secapp.springjwt.repository;

import com.secapp.springjwt.models.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListRepository extends JpaRepository<TodoList, Long> {
}