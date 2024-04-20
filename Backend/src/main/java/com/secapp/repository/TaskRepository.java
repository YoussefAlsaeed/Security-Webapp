package com.secapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.secapp.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}