package org.wangqing.microservices.guestbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wangqing.microservices.guestbook.entity.Notebook;
import org.wangqing.microservices.guestbook.repository.NotebookRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/guestbook/")
public class GuestbookController {

	private final NotebookRepository notebookRepository;

	@Autowired
	public GuestbookController(NotebookRepository notebookRepository) {
		this.notebookRepository = notebookRepository;
	}

	@RequestMapping(value = "signup", method = RequestMethod.GET)
	public String showSignUpForm(Notebook notebook, Model model) {
		model.addAttribute("add-notebook", new Notebook());
		return "add-notebook";
	}

	@RequestMapping("list")
	public String showUpdateForm(Model model) {
		model.addAttribute("notebooks", notebookRepository.findAll());
		return "index";
	}

	@RequestMapping("/")
	public String showAll(Model model) {
		model.addAttribute("notebooks", notebookRepository.findAll());
		return "index";
	}

	@RequestMapping("add")
	public String addNote(@Valid Notebook notebook, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-notebook";
		}

		notebookRepository.save(notebook);
		return "redirect:list";
	}

	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Notebook notebook = notebookRepository.findOne(id);

		model.addAttribute("notebook", notebook);
		return "update-notebook";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.POST)
	public String updateNote(@PathVariable("id") long id, Notebook notebook, BindingResult result,
							 Model model) {

//		if (result.hasErrors()) {
//			notebook.setId(id);
//			return "update-notebook";
//		}

		notebookRepository.save(notebook);
		model.addAttribute("notebook", notebook);
		model.addAttribute("notebooks", notebookRepository.findAll());
		return "index";
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public String deleteNote(@PathVariable("id") long id, Model model) {
		Notebook notebook = notebookRepository.findOne(id);
		if(notebook != null){
			notebookRepository.delete(notebook);

		}
		model.addAttribute("notebooks", notebookRepository.findAll());
		return "index";
	}
}
