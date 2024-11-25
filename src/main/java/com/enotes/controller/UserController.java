package com.enotes.controller;

import java.security.Principal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.enotes.entity.Notes;
import com.enotes.entity.User;
import com.enotes.repository.UserRepository;
import com.enotes.service.NotesService;

import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private NotesService notesService;
	
	@ModelAttribute
	public User getUser(Principal p,Model m) {
		String email = p.getName();
		
		User user = userRepo.findByEmail(email);
		m.addAttribute("user", user);
		return user;
	}
	
	
	@GetMapping("/addNotes")
	public String addNotes() {
		
		return "add_notes";
	}
	
	@GetMapping("/viewNotes")
	public String viewNotes() {
		
		return "view_notes";
	}
	
	@GetMapping("/editNotes")
	public String editNotes() {
		
		return "edit_notes";
	}
	
	@PostMapping("saveNotes")
	public String saveNotes(@ModelAttribute Notes notes, HttpSession session, Principal p, Model m) {
		
		notes.setDate(LocalDate.now());
		notes.setUser(getUser(p,m));
		
		Notes savedNotes =  notesService.saveNotes(notes); 
		if(savedNotes != null) {
			session.setAttribute("msg", "Note saved successfully");
		}else {
			session.setAttribute("msg", "An error occured");
		}
		
		return "redirect:/addNotes";
		
	}

}
