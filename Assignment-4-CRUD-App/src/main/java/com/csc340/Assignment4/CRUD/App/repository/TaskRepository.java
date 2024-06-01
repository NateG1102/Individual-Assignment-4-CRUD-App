package com.csc340.Assignment4.CRUD.App.repository;

import com.csc340.Assignment4.CRUD.App.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByGoal_GoalId(int goalId);
}
