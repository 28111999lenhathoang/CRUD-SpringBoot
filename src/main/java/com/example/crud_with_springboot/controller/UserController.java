package com.example.crud_with_springboot.controller;

import com.example.crud_with_springboot.entity.User;
import com.example.crud_with_springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    //step2: call instance model
    @Autowired
    UserRepository userRepository;
    @RequestMapping("/")
    public String index(Model model){
        List<User> users = (List<User>) userRepository.findAll();
        //request.setAttribute("user"user);
        model.addAttribute("users",users);
        return "index";
    }
    @RequestMapping(value = "add")
    public String addUser(Model model){
        model.addAttribute("user" , new User());
        return "EditUser";
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(User user){
        userRepository.save(user);
        return "redirect:/";
    }
    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public String editUser(@RequestParam("id") Long userId, Model model){
        Optional<User> userEdit = userRepository.findAllById(userId);
        userEdit.ifPresent(user -> model.addAttribute("user", user));
        return "editUser";
    }
    @RequestParam(value = "/delete", method= RequestMethod.DELETE)
    public String deleteUser(@RequestParam("id") Long userId,Model model){
        userRepository.deleteById(userId);
        return "redirect:/";
    }
}
