package com.example.taskmanager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    private TaskRepository taskRepository;

    public HomeController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Task> tasks = taskRepository.findNotDone();
        List<Task> tasksDone = taskRepository.findDone();
        model.addAttribute("tasks", tasks);
        model.addAttribute("tasksDone", tasksDone);
        model.addAttribute("newTask", new Task());
        return "home";
    }

    @GetMapping("/done")
    public String markAsDone(@RequestParam Long id) {
        Task task = taskRepository.findById(id);
        task.setDone(true);
        taskRepository.save(task);
        return "redirect:/";
    }

    @PostMapping("/add")
    public String add(Task task) {
        taskRepository.save(task);
        return "redirect:/";
    }
}
