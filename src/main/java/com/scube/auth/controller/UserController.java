package com.scube.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scube.auth.model.DbUser;
import com.scube.auth.service.AutoLoginService;
import com.scube.auth.service.UserService;
import com.scube.auth.validator.UserValidator;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AutoLoginService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value="/admin",method=RequestMethod.GET)
    public String admin(){
    	
    	return "admin";
    }
    
  
    
    
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new DbUser());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") DbUser userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

      securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }
    
  /*  @RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
    public String resetPassword(Model model) {
    
    	userService.resetPassword(username);
        model.addAttribute("userForm", new DbUser());

        return "ResetPassword";
    }
*/
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping(value = {"/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }
    
    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        return "home";
    }
    @RequestMapping("/403")
    public String accessDenied() {
        return "accessDenied";
    }
    
    @RequestMapping(value="/auth/users",  method = RequestMethod.GET)
    public @ResponseBody List<DbUser> getAllUsers() {
    	List<DbUser> users = userService.getAllUser();
        return users;
    }
    
    
}
