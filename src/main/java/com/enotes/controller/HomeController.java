package com.enotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.enotes.entity.User;
import com.enotes.service.UserService;

/*import ch.qos.logback.core.model.Model;*/
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String index() {
		
		return "index";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		/*
		 * String message = userService.removeSessionMessage();
		 * model.addAttribute("sessionMessage", message);
		 */
		
		return "register";
	}
	
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute User user, HttpSession session) {
		boolean f = userService.existEmailCheck(user.getEmail());
		
		if(f) {
			
			session.setAttribute("msg", "Email id Already exists");
		}else {
			User savedUser = userService.saveUser(user);
			if(savedUser != null) {
				session.setAttribute("msg", "Registration successfully");
			}else {
				session.setAttribute("msg", "An error occured");
			}
		}
		
		return "redirect:/register";
	}
	
	@GetMapping("/signin")
	public String login() {
		
		return "login";
	}

	


}
