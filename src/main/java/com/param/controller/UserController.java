package com.param.controller;

import java.util.List;			

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.param.model.User;
import com.param.service.UserService;


@Controller
public class UserController 
{
	@Autowired
	private UserService userService; 
	
	@GetMapping("/")
	public String ViewHomePage(Model model) 
	{
		return findPaginated(1, model);			//model.addAttribute("listUsers", userService.getAllUsers());
	}											//return "index"; 
	
	@GetMapping("/search")
	public String searchUser( Model model,@Param("keyword") String keyword)
	{
		if(keyword!=null)
		{
			model.addAttribute("listUsers", userService.findByKeyword(keyword));
		}
		else
		{
			model.addAttribute("listUsers", userService.getAllUsers());
		}
		return "index";
	}
	
	@GetMapping("/showNewUserForm")
	public String showNewUserForm(Model model)
	{
		User user=new User();
		model.addAttribute("user", user);
		return "new_user";
		
	}
	@PostMapping("/saveUser")
	public String saveUser(@Valid @ModelAttribute("user") User user ,BindingResult br,Model model)
	{
		
		if(br.hasErrors())
		{
			model.addAttribute("user", user);
			return "new_user";
		}
		userService.saveUser(user);
		return "redirect:/";
		
		
	}
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value="id")long id,Model model)
	{
		User user=userService.getUserById(id);
		model.addAttribute("user",user);
		return "update_user";
	}
	@PostMapping("/updateUser")
	public String updateUser(@Valid @ModelAttribute("user") User user ,BindingResult br,Model model)
	{
		if(br.hasErrors())
		{
			model.addAttribute("user", user);
			return "update_user";
		}
		userService.saveUser(user);
		return "redirect:/";
	}
	
	
	@GetMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable(value="id")long id)
	{
		this.userService.deleteUserById(id);
		return "redirect:/ ";
		
	}
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo,Model model) 
	{
		int pageSize = 5;
		
		Page<User> page = userService.findPaginated(pageNo, pageSize);
		List<User> listUsers = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listUsers", listUsers);
		return "index";
	}
	
}
