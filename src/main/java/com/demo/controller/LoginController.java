package com.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.demo.dto.LoginDTO;
import com.demo.model.Login;
import com.demo.service.LoginService;

@RestController
public class LoginController {

	@Autowired
	LoginService loginservice;

	@GetMapping("/login")
	public ModelAndView loginShowPage() {
		return new ModelAndView("/login");
	}

	@PostMapping(path = "/login", consumes = "application/x-www-form-urlencoded")
	public RedirectView loginPage(@ModelAttribute LoginDTO request, HttpSession session) {
		Login login = loginservice.findByUserNameAndPassword(request.getUserName(), request.getPassword());
		if (login != null) {
			session.setAttribute("msg", "Login  Sucessfully ");
			return new RedirectView("/home");
		}
		session.setAttribute("msg", "Something went wrong ");
		return new RedirectView("/login");
	}
}