package org.wangqing.notebookk8s.notebook.controller;

import javax.validation.Valid;

import org.wangqing.notebookk8s.notebook.entity.Notebook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.wangqing.notebookk8s.notebook.repository.NotebookRepository;

@Controller
@RequestMapping("/notebook/")
public class NotebookController {

	private final NotebookRepository notebookRepository;

	@Autowired
	public NotebookController(NotebookRepository notebookRepository) {
		this.notebookRepository = notebookRepository;
	}

	@GetMapping("signup")
	public String showSignUpForm(Notebook notebook) {
		return "add-notebook";
	}

	@GetMapping("list")
	public String showUpdateForm(Model model) {
		model.addAttribute("notebooks", notebookRepository.findAll());
		return "index";
	}

	@PostMapping("add")
	public String addNote(@Valid Notebook notebook, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-notebook";
		}

		notebookRepository.save(notebook);
		return "redirect:list";
	}

	@GetMapping("edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Notebook notebook = notebookRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid notebook Id:" + id));
		model.addAttribute("notebook", notebook);
		return "update-notebook";
	}

	@PostMapping("update/{id}")
	public String updateNote(@PathVariable("id") long id, @Valid Notebook notebook, BindingResult result,
							 Model model) {
		if (result.hasErrors()) {
			notebook.setId(id);
			return "update-notebook";
		}

		notebookRepository.save(notebook);
		model.addAttribute("notebooks", notebookRepository.findAll());
		return "index";
	}

	@GetMapping("delete/{id}")
	public String deleteNote(@PathVariable("id") long id, Model model) {
		Notebook notebook = notebookRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid notebook Id:" + id));
		notebookRepository.delete(notebook);
		model.addAttribute("notebooks", notebookRepository.findAll());
		return "index";
	}
}
