package com.jeffknowlesjr.greatIdeas;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.jeffknowlesjr.greatIdeas.models.User;

@Controller
public class RouteController {

	@GetMapping("")
	public String index(@Valid @ModelAttribute( "user" ) User user, BindingResult bindingResult, Model model, HttpSession session) {
		System.out.println("HITTING INDEX ROUTE");
		return "index.jsp";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:";
	}
	
}
