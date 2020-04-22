package com.pohjelmointi.tasks.net;

import org.springframework.beans.factory.annotation.Autowired;

import com.pohjelmointi.tasks.domain.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
public class TaskController {
	@Autowired
	private TaskRepository trepository;
	
	@Autowired
	private StatusRepository srepository;
	
	@Autowired
	private AssigneeRepository arepository;

	// Login page
	@RequestMapping(value="/login")
    public String login() {	
        return "login";
	}
	
	// Index will redirect to tasklist
	@RequestMapping(value= {"/", "/index"})
	public String index() {
		return "redirect:tasklist";
	}
	
	// Show all tasks
	@RequestMapping("/tasklist")
	public String showTaskList(Model model) {
		model.addAttribute("tasks", trepository.findAll());
		model.addAttribute("assignees", arepository.findAll());
		return "tasklist";
	}
	
	// Add a new task
	@RequestMapping(value = "/addtask", method = RequestMethod.POST)
	public String addTask(Model model) {
		model.addAttribute("task", new Task());
		model.addAttribute("statuses", srepository.findAll());
		model.addAttribute("assignees", arepository.findAll());
		return "addtask";
	}
	
	// Save new task
	@RequestMapping(value = "/savetask", method = RequestMethod.POST)
	public String saveTask(Task task) {
		trepository.save(task);
		return "redirect:tasklist";
	}
	
	// Add a new assignee
	@RequestMapping(value = "/addassignee", method = RequestMethod.POST)
	public String addAssignee(Model model) {
		model.addAttribute("assignee", new Assignee());
		return "addassignee";
	}
	
	// Save new assignee
	@RequestMapping(value = "/saveassignee", method = RequestMethod.POST)
	public String saveAssignee(Assignee assignee) {
		arepository.save(assignee);
		return "redirect:tasklist";
	}
	
	// Delete a task
	// TODO @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/deletetask/{id}", method = RequestMethod.GET)
	public String deleteTask(@PathVariable("id") Long taskId, Model model) {
		trepository.deleteById(taskId);
		return "redirect:../tasklist";
	}
	
	// Delete an assignee
	// TODO @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/deleteassignee/{id}", method = RequestMethod.GET)
	public String deleteAssignee(@PathVariable("id") Long assigneeId, Model model) {
		arepository.deleteById(assigneeId);
		return "redirect:../tasklist";
	}
	
	// Edit task information
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editTask(@PathVariable("id") Long taskId, Model model){
		model.addAttribute("task", trepository.findById(taskId));
		model.addAttribute("statuses", srepository.findAll());
		model.addAttribute("assignees", arepository.findAll());
		return "edittask";
	}
	
    // Show all tasks for one assignee
    @RequestMapping("/assignee")
	public String showTaskList(@RequestParam String name, Model model) {
		model.addAttribute("assignee", arepository.findByName(name).get(0));
		model.addAttribute("tasks", trepository.findByAssignee(arepository.findByName(name).get(0)));
		return "/assigneeslist";
	}
	
	// RESTful service to get all tasks
    @RequestMapping(value="/tasks", method = RequestMethod.GET)
    public @ResponseBody List<Task> taskListRest() {	
        return (List<Task>) trepository.findAll();
    }
    
    // RESTful service to get task by id
    @RequestMapping(value="/task/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Task> findTaskRest(@PathVariable("id") Long taskId) {	
    	return trepository.findById(taskId);
    }
    
    // RESTful service to get all assignees
    @RequestMapping(value="/assignees", method = RequestMethod.GET)
    public @ResponseBody List<Assignee> assigneeListRest() {	
        return (List<Assignee>) arepository.findAll();
    }
    
    // RESTful service to get assignee by id
    @RequestMapping(value="/assignee/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Assignee> findAssigneeRest(@PathVariable("id") Long assigneeId) {	
    	return arepository.findById(assigneeId);
    }
   
}
