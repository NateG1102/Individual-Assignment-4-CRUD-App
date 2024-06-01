package com.csc340.Assignment4.CRUD.App.controller;

import com.csc340.Assignment4.CRUD.App.model.Task;
import com.csc340.Assignment4.CRUD.App.service.TaskService;
import com.csc340.Assignment4.CRUD.App.service.GoalService;
import com.csc340.Assignment4.CRUD.App.model.Goal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final GoalService goalService;

    @Autowired
    public TaskController(TaskService taskService, GoalService goalService) {
        this.taskService = taskService;
        this.goalService = goalService;
    }

    @GetMapping("/create")
    public String showCreateTaskForm(@RequestParam int goalId, Model model) {
        Task task = new Task();
        Goal goal = goalService.getGoalById(goalId).orElse(null);
        task.setGoal(goal);
        model.addAttribute("task", task);
        model.addAttribute("goalId", goalId);
        return "createTask";
    }

    @PostMapping("/create")
    public String createTask(@ModelAttribute Task task) {
        taskService.createTask(task);
        return "redirect:/goals/" + task.getGoal().getGoalId();
    }

    @GetMapping("/{taskId}")
    public String viewTask(@PathVariable int taskId, Model model) {
        Task task = taskService.getTaskById(taskId).orElse(null);
        model.addAttribute("task", task);
        return "viewTask";
    }

    @PostMapping("/update/{taskId}")
    public String updateTask(@PathVariable int taskId, @ModelAttribute Task task) {
        taskService.updateTask(taskId, task);
        return "redirect:/goals/" + task.getGoal().getGoalId();
    }

    @PostMapping("/delete/{taskId}")
    public String deleteTask(@PathVariable int taskId) {
        Task task = taskService.getTaskById(taskId).orElse(null);
        int goalId = task != null ? task.getGoal().getGoalId() : 0;
        taskService.deleteTask(taskId);
        return "redirect:/goals/" + goalId;
    }

    @GetMapping("/goal/{goalId}")
    public String viewTasksForGoal(@PathVariable int goalId, Model model) {
        List<Task> tasks = taskService.getAllTasksForGoal(goalId);
        model.addAttribute("tasks", tasks);
        model.addAttribute("goalId", goalId);
        return "viewTasks";
    }
}
