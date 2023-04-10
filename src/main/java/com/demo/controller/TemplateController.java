package com.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.demo.dto.AddTemplateDTO;
import com.demo.dto.UpdateTemplateDTO;
import com.demo.model.Template;
import com.demo.service.TemplateService;

@RestController
public class TemplateController {

	@Autowired
	TemplateService templateService;

	@RequestMapping("/test")
	public String showMessage() {
		return "its working !!!";
	}

	@PostMapping("/addTemplate")
	public RedirectView addTemplate(@ModelAttribute AddTemplateDTO request, HttpSession session) {
		Template template = templateService.getTemplateName(request.getTemplateName());
		if (request.getTemplateName().isEmpty() || request.getContent().isBlank()) {
			session.setAttribute("msg", "Content can`t be empty ");
			return new RedirectView("/createTemplate");
		} else if (template != null) {
			session.setAttribute("msg", "Template name already exist ");
			return new RedirectView("/createTemplate");
		}
		templateService.addTemplate(request);
		session.setAttribute("msg", "Add Template Sucessfully ");
		return new RedirectView("/home");
	}

	@RequestMapping("/createTemplate")
	public ModelAndView getTemplate(Model model) {
		Template template = new Template();
		model.addAttribute("template", template);
		return new ModelAndView("create_template");
	}

	@GetMapping("/home")
	public ModelAndView home(@RequestParam(value = "pageNo", defaultValue = "0", required = false) Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
			@RequestParam(defaultValue = "", required = false) String keyword, Model model) {

		Page<Template> request = templateService.getTemplateList(pageNo, pageSize, sortBy, keyword);
		model.addAttribute("getTemplateList", request.getContent());
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("sortBy", sortBy);
		model.addAttribute("totalPages", request.getTotalPages());
		model.addAttribute("totalItems", request.getTotalElements());
		model.addAttribute("keyword", keyword);
		return new ModelAndView("home");
	}

	@GetMapping("/deleteTemplate/{id}")
	public RedirectView deleteTemplate(@PathVariable(value = "id") Long id, HttpSession session) {
		templateService.deleteTemplate(id);
		session.setAttribute("msg", "Delete Template Sucessfully ");
		return new RedirectView("/home");
	}

	@GetMapping("/updateTemplate/{id}")
	public ModelAndView updateTemplate(@PathVariable(value = "id") Long id, Model model) {
		Template template = templateService.getById(id);
		model.addAttribute("template", template);
		return new ModelAndView("update_template");
	}

	@PostMapping("/editTemplate")
	public RedirectView editTemplate(@ModelAttribute UpdateTemplateDTO request, HttpSession session) {
		if (!request.getContent().isEmpty()) {
			templateService.editTemplate(request);
			session.setAttribute("msg", "Update Template Sucessfully ");
			return new RedirectView("/home");
		}
		session.setAttribute("msg", "Content can`t be empty ");
		return new RedirectView("/updateTemplate/" + request.getId());

	}
}
