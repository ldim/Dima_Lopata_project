package com.heavy.app.service;



import com.heavy.app.domain.User;
import com.heavy.app.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * User: temaprof
 * Date: 19.09.2016
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getUsers(ModelMap model){
        List<User> users = this.userRepository.listAll();
        model.addAttribute("user", users);
        return "index";
    }

    @Override
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

    @Override
    @RequestMapping(value = "deleteUser/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable Integer id){
        this.userRepository.removeUser(id);
        return "redirect:/";
    }

    @Override
    @RequestMapping(value = "updateUser/{id}", method = RequestMethod.GET)
    public String getUpdate(@PathVariable Integer id, Model model){
        model.addAttribute("userAttribute", userRepository.get(id));
        return "updateUser";
    }
}
