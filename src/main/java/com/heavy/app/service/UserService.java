package com.heavy.app.service;


import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;




/**
 * User: temaprof
 * Date: 19.09.2016
 */
public interface UserService {

    public String addUser(ModelMap model);

    public String getUsers(ModelMap model);

    public String deleteUser(Integer id);

    public String getUpdate(Integer id, Model model);
}
