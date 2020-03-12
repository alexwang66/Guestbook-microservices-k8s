package org.wangqing.microservices.guestbook.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wangqing.microservices.guestbook.entity.Notebook;
import org.wangqing.microservices.guestbook.repository.NotebookRepository;

import javax.validation.Valid;

@Controller
public class Api {

	private final NotebookRepository notebookRepository;

	@Autowired
	public Api(NotebookRepository notebookRepository) {
		this.notebookRepository = notebookRepository;
	}


	@RequestMapping("/")
	public String showAll(Model model) {
		model.addAttribute("notebooks", notebookRepository.findAll());
		return "index";
	}


}
