package com.jeffknowlesjr.greatIdeas;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeffknowlesjr.greatIdeas.models.Idea;
import com.jeffknowlesjr.greatIdeas.models.User;
import com.jeffknowlesjr.greatIdeas.services.IdeaService;
import com.jeffknowlesjr.greatIdeas.services.UserService;

@Controller
@RequestMapping( "/ideas" )
public class IdeaController {
	private IdeaService ideaService;
	private UserService userService;
	public IdeaController( IdeaService ideaService, UserService userService ) {
		this.ideaService = ideaService;
		this.userService = userService;
	}
	
	@ResponseBody
	@GetMapping("") // Backdoor
	public ArrayList<Idea> root( @ModelAttribute( "idea" ) Idea idea, Model model, HttpSession session ) {
		ArrayList<Idea> ideas = ideaService.findAll();
		model.addAttribute( ideas );
		return ideas;
	}
	
	@PostMapping("")
	public String createidea( @Valid @ModelAttribute( "idea" ) Idea idea, BindingResult bindingResult, Model model, HttpSession session ) {
		if ( bindingResult.hasErrors() ) {
			return "add.jsp";
		}
		if ( ideaService.findByName( idea.getName() ) != null ) {
			model.addAttribute( "exists", "A idea with this name already exists." );
			model.addAttribute( "idea", new Idea() );
			return "add.jsp";
		}
		ideaService.create(idea);
			return "redirect:users/dashboard";
	}
	
	@GetMapping("/add")
	public String addidea( @Valid @ModelAttribute( "idea" ) Idea idea, BindingResult bindingResult, Model model,
	HttpSession session ) {
		if (session.getAttribute("user") == null) {
			return "redirect:..";
		}
		Long id = (Long) session.getAttribute("user");
		User user = userService.findById(id);
		model.addAttribute("user", user);
		return "add.jsp";
	}

	@GetMapping("/{ideaId}")
	public String show( @PathVariable("ideaId") Long ideaId, Model model, HttpSession session ) {
		if (session.getAttribute("user") == null) {
			return "redirect:..";
		}
		Idea idea = ideaService.findById(ideaId);
		List<User> users = idea.getUsers();
		model.addAttribute("idea", idea);
		model.addAttribute("users", users);
		return "show.jsp";
	}
	
	@GetMapping("/delete/{ideaId}")
	public String delete( @PathVariable("ideaId") Long ideaId, HttpSession session ) {
		System.out.println("HITTING DELETE ROUTE");
		Idea idea = ideaService.findById(ideaId);
		System.out.println(session.getAttribute("user"));
		System.out.println(idea.getCreator_id());
		if (session.getAttribute("user").equals(idea.getCreator_id())) {
			System.out.println("Deleted");
			ideaService.destroy(idea);
			return "redirect:/users/dashboard";
		} else {
			return "/ideas/{ideadId}";
		}
	}
	
	@GetMapping("/edit/{ideaId}")
	public String edit( @Valid @ModelAttribute( "idea" ) Idea idea, BindingResult bindingResult, @PathVariable("ideaId") Long ideaId, Model model,
			HttpSession session ) {
		System.out.println("HITTING EDIT ROUTE");
		Idea ideaOld = ideaService.findById(ideaId);
		model.addAttribute("postId", ideaOld.getId());
		model.addAttribute("ideaOld", ideaOld);
		return "edit.jsp";
	}

	@PostMapping("/update/{ideaId}")
	public String updateIdea( @Valid @ModelAttribute( "idea" ) Idea idea, BindingResult bindingResult, @PathVariable("ideaId") Long ideaId, Model model,
			HttpSession session) {
		Idea ideaOld = ideaService.findById(ideaId);
		model.addAttribute("postId", ideaOld.getId());
		model.addAttribute("ideaOld", ideaOld);
		if ( bindingResult.hasErrors() ) {
			System.out.println(bindingResult);
			return "edit.jsp";
		}
		if ( ideaService.findByName( idea.getName() ) != null ) {
			model.addAttribute( "exists", "An idea with this name already exists." );
			System.out.println("test1");
			return "edit.jsp";
		}
		ideaOld.setName(idea.getName());
		ideaService.update(ideaOld);
		return "redirect:/users/dashboard";
		
	}

}
