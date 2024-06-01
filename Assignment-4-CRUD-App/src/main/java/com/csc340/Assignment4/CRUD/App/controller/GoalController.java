package com.csc340.Assignment4.CRUD.App.controller;

import com.csc340.Assignment4.CRUD.App.model.Goal;
import com.csc340.Assignment4.CRUD.App.service.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/goals")
public class GoalController {
    private final GoalService goalService;

    @Autowired
    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @GetMapping("/create")
    public String showCreateGoalForm(Model model) {
        model.addAttribute("goal", new Goal());
        return "createGoal";
    }

    @PostMapping("/create")
    public String createGoal(@ModelAttribute Goal goal, Model model) {
        try {
            // Log the received Goal object
            System.out.println("Received Goal: " + goal);
            Goal createdGoal = goalService.createGoal(goal);
            return "redirect:/tasks/create?goalId=" + createdGoal.getGoalId();
        } catch (Exception e) {
            // Log the exception details
            e.printStackTrace();
            model.addAttribute("errorMessage", "Error creating goal: " + e.getMessage());
            return "createGoal";
        }
    }

    @GetMapping
    public String viewGoals(Model model) {
        List<Goal> goals = goalService.getAllGoalsForUser(1); // Assuming user ID 1 for simplicity
        model.addAttribute("goals", goals);
        return "viewGoals";
    }

    @GetMapping("/{goalId}")
    public String viewGoal(@PathVariable int goalId, Model model) {
        Goal goal = goalService.getGoalById(goalId).orElse(null);
        model.addAttribute("goal", goal);
        return "viewGoal";
    }

    @PostMapping("/update/{goalId}")
    public String updateGoal(@PathVariable int goalId, @ModelAttribute Goal goal) {
        goalService.updateGoal(goalId, goal);
        return "redirect:/goals";
    }

    @PostMapping("/delete/{goalId}")
    public String deleteGoal(@PathVariable int goalId) {
        goalService.deleteGoal(goalId);
        return "redirect:/goals";
    }
}
