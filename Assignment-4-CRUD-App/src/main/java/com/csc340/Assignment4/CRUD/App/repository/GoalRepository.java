package com.csc340.Assignment4.CRUD.App.repository;

import com.csc340.Assignment4.CRUD.App.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


public interface GoalRepository extends JpaRepository<Goal, Integer> {
    List<Goal> findByUserId(int userId);
}
