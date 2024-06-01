package com.csc340.Assignment4.CRUD.App.service;

import com.csc340.Assignment4.CRUD.App.model.Goal;
import com.csc340.Assignment4.CRUD.App.repository.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GoalService {
    private final GoalRepository goalRepository;

    @Autowired
    public GoalService(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    public Goal createGoal(Goal goal) {
        return goalRepository.save(goal);
    }

    public Goal updateGoal(int goalId, Goal updatedGoal) {
        Optional<Goal> optionalGoal = goalRepository.findById(goalId);
        if (optionalGoal.isPresent()) {
            Goal existingGoal = optionalGoal.get();
            existingGoal.setTitle(updatedGoal.getTitle());
            existingGoal.setDetails(updatedGoal.getDetails());
            existingGoal.setTargetDate(updatedGoal.getTargetDate());
            existingGoal.setStatus(updatedGoal.getStatus());
            return goalRepository.save(existingGoal);
        }
        return null;
    }

    public boolean deleteGoal(int goalId) {
        if (goalRepository.existsById(goalId)) {
            goalRepository.deleteById(goalId);
            return true;
        }
        return false;
    }

    public List<Goal> getAllGoalsForUser(int userId) {
        return goalRepository.findByUserId(userId);
    }

    public Optional<Goal> getGoalById(int goalId) {
        return goalRepository.findById(goalId);
    }
}
