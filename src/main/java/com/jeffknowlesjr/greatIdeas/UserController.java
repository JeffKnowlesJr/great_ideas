package com.jeffknowlesjr.greatIdeas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeffknowlesjr.greatIdeas.models.Idea;
import com.jeffknowlesjr.greatIdeas.models.User;
import com.jeffknowlesjr.greatIdeas.services.IdeaService;
import com.jeffknowlesjr.greatIdeas.services.UserService;

@Controller
@RequestMapping( "/users" )
public class UserController {

	private UserService userService;
	private IdeaService ideaService;
	
	public UserController( UserService userService, IdeaService ideaService ) {
		this.userService = userService;
		this.ideaService = ideaService;
	}

	@GetMapping("/dashboard")
	public String dash(HttpSession session, Model model) {
		System.out.println("HITTING DASHBOARD ROUTE");
		if (session.getAttribute("user") == null) {
			return "redirect:";
		}
		Long id = (Long) session.getAttribute("user");
		User user = userService.findById(id);
		model.addAttribute("user", user);
		model.addAttribute("ideas", ideaService.findAll());
		model.addAttribute("users", userService.findAll());
		return "dashboard.jsp";
	}
	
	@GetMapping("/dashboard/high")
	public String dashhigh(HttpSession session, Model model) {
		System.out.println("HITTING HIGH DASHBOARD ROUTE");
		System.out.println("HITTING DASHBOARD ROUTE");
		if (session.getAttribute("user") == null) {
			return "redirect:";
		}
		List<Idea> temp = ideaService.findAll();
		Comparator<Idea> com = new Comparator<Idea>() {
			public int compare(Idea idea1, Idea idea2) {
				if ( idea1.getUsers().size() < idea2.getUsers().size()) {
					return 1;
				}
				else {
					return -1;
				}
			}
		};
		Collections.sort(temp, com);
		Long id = (Long) session.getAttribute("user");
		User user = userService.findById(id);
		model.addAttribute("user", user);
		model.addAttribute("ideas", temp);
		model.addAttribute("users", userService.findAll());
		return "dashboard.jsp";
	}
	
	@GetMapping("/dashboard/low")
	public String dashlow(HttpSession session, Model model) {
		System.out.println("HITTING LOW DASHBOARD ROUTE");
		System.out.println("HITTING DASHBOARD ROUTE");
		if (session.getAttribute("user") == null) {
			return "redirect:";
		}
		List<Idea> temp = ideaService.findAll();
		Comparator<Idea> com = new Comparator<Idea>() {
			public int compare(Idea idea1, Idea idea2) {
				if ( idea1.getUsers().size() > idea2.getUsers().size()) {
					return 1;
				}
				else {
					return -1;
				}
			}
		};
		Collections.sort(temp, com);
		Long id = (Long) session.getAttribute("user");
		User user = userService.findById(id);
		model.addAttribute("user", user);
		model.addAttribute("ideas", temp);
		model.addAttribute("users", userService.findAll());
		return "dashboard.jsp";
	}
	
	@ResponseBody
	@GetMapping("") // Backdoor
	public ArrayList<User> root( @ModelAttribute( "user" ) User user, Model model, HttpSession session ) {
		ArrayList<User> users = userService.findAll();
		model.addAttribute( users );
		return users;
	}
	
	@PostMapping("")
	public String register( @Valid @ModelAttribute( "user" ) User user, BindingResult bindingResult, Model model, HttpSession session ) {
		if ( bindingResult.hasErrors() ) {
			return "index.jsp";
		}
		if ( userService.findByEmail( user.getEmail() ) != null ) {
			model.addAttribute( "exists", "A user with this email already exists." );
			model.addAttribute( "user", new User() );
			return "index.jsp";
		}
		if ( !user.getPassword().equals( user.getConfirmPassword() ) ) {
			model.addAttribute( "regError", "Passwords must match.");
			model.addAttribute( "user", new User() );
			return "index.jsp";
		}
		userService.create(user);
		session.setAttribute("user", user.getId() );
		return "redirect:/users/dashboard";
	}

	@PostMapping("/login")
	public String login( HttpSession session, @RequestParam("email") String email, @RequestParam("password") String password, Model model ) {
		User user = userService.findByEmail(email);
		if ( user == null ) {
			model.addAttribute( "loginError","Invalid Credentials" );
			model.addAttribute( "user", new User() );
			return "redirect:";
		}
		if ( !userService.isPass(user, password) ) {
			model.addAttribute( "loginError2","Invalid Credentials" );
			model.addAttribute( "user", new User() );
			return "redirect:";
		}
		else {
			session.setAttribute("user", user.getId() );
			return "redirect:dashboard";
		}
	}
	
	@GetMapping("{userId}/ideas/{ideaId}")
	public String join( @PathVariable("ideaId") Long ideaId, @PathVariable("userId") Long userId, HttpSession session) {
		System.out.println("HITTING LIKE ROUTE");
		Idea idea = ideaService.findById(ideaId);
		System.out.println(idea);
		User user = userService.findById(userId);
		if (!idea.getUsers().contains(user)) {
			System.out.println(user);
			List<Idea> ideas = user.getIdeas();
			System.out.println(ideas);
			ideas.add(idea);
			System.out.println(ideas);
			userService.update(user);
		}
		return "redirect:/users/dashboard";
	}
	
	@GetMapping("{userId}/ideas/{ideaId}/unlike")
	public String unlike( @PathVariable("ideaId") Long ideaId, @PathVariable("userId") Long userId, HttpSession session) {
		System.out.println("HITTING UNLIKE ROUTE");
		Idea idea = ideaService.findById(ideaId);
		System.out.println(idea);
		User user = userService.findById(userId);
		if (idea.getUsers().contains(user)) {
			System.out.println(user);
			List<Idea> ideas = user.getIdeas();
			System.out.println(ideas);
			ideas.remove(idea);
			System.out.println(ideas);
			userService.update(user);
		}
		return "redirect:../../../dashboard";
	}
}
