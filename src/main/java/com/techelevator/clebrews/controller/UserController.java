package com.techelevator.clebrews.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.clebrews.model.Brewery;
import com.techelevator.clebrews.model.BreweryDAO;
import com.techelevator.clebrews.model.User;
import com.techelevator.clebrews.model.UserDAO;


@Controller
public class UserController {

	private UserDAO userDAO;
	
	@Autowired
	BreweryDAO breweryDAO;

	@Autowired
	public UserController(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@RequestMapping(path="/users/new", method=RequestMethod.GET)
	public String displayNewUserForm(ModelMap modelHolder, @ModelAttribute Brewery brewery) {
		if(! modelHolder.containsAttribute("newUser")) {
			modelHolder.addAttribute("newUser", new User());
		}
		List<Brewery> breweries = breweryDAO.getBreweryWhereUserIdIsNULL();
		modelHolder.put("allBreweries", breweries);
		return "newUser";
	}
	
	@RequestMapping(path="/users", method=RequestMethod.POST)
	public String createUser(@Valid @ModelAttribute("newUser") User newUser, @RequestParam Long id, BindingResult result, RedirectAttributes flash) {
		
		flash.addFlashAttribute("newUser", newUser);
		
		if(result.hasErrors()) {
			flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "newUser", result);
			return "redirect:/users/new";
		}
		if(!userDAO.searchForUsername(newUser.getUserName())) { 
			int userId = userDAO.saveUser(newUser);
			breweryDAO.updateBreweryUserId(id, userId);
			
			return "redirect:/index"; 
		} else {
				flash.addFlashAttribute("message", "This username alreadys exists");
				return "redirect:/users/new";
		}

	}

	@RequestMapping(path="/users/{userName}/changePassword", method=RequestMethod.GET)
	public String displayChangePasswordForm(Map<String, Object> model, @PathVariable String userName) {
		model.put("userName", userName);
		return "changePassword";
	}
	
	@RequestMapping(path="/users/{userName}/changePassword", method=RequestMethod.POST)
	public String changePassword(@PathVariable String userName, @RequestParam String password) {
		userDAO.updatePassword(userName, password);
		return "userDashboard";
	}
}
