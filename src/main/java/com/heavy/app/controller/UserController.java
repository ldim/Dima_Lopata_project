package com.heavy.app.controller;

import com.heavy.app.domain.User;
import com.heavy.app.repository.UserRepository;
import com.heavy.app.validation.UserValidator;
import org.hibernate.annotations.ParamDef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Ar4er on 17.04.16.
 */
@Controller
public class UserController {

    private UserRepository userRepository;

    private UserValidator userValidator;

    @Autowired
    public UserController(UserRepository userRepository, UserValidator userValidator){
        this.userRepository = userRepository;
        this.userValidator = userValidator;
    }
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getUsers(ModelMap model){
        List<User> users = this.userRepository.listAll();
        model.addAttribute("user", users);
        return "index";
    }

    @RequestMapping(value = "addUser", method = RequestMethod.GET)
    public String addUser(ModelMap model){
        return "addUser";
    }

    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    public String addUser(@RequestParam("name") String name, @RequestParam("age") String age, @RequestParam(value="isAdmin",
            required = true, defaultValue = "false") Boolean isAdmin)
    {
        User user = new User(name,Integer.parseInt(age),isAdmin);
//        this.userValidator.validate(user, bindingResult);
//        if (bindingResult.hasErrors())
//            return "addUser";
        this.userRepository.addUser(user);
        return "redirect:/";
    }
    @RequestMapping(value = "deleteUser/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable Integer id){
        this.userRepository.removeUser(id);
        return "redirect:/";
    }

    @RequestMapping(value = "updateUser/{id}", method = RequestMethod.GET)
    public String getUpdate(@PathVariable Integer id, Model model){
        model.addAttribute("userAttribute", userRepository.get(id));
        return "updateUser";
    }
}
