
package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;


@Controller
public class UsersController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/")
    public String listUsers(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("listUser", this.userService.listUsers());
        return "users";
    }

    @PostMapping(value = "/user/add")
    public String addUser(@ModelAttribute("user") User user) {
        this.userService.addUser(user);
        return "redirect:/";
    }

    @PostMapping("/remove")
    public String removeUser(@RequestParam("id") Long id) {
        this.userService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String showUpdateForm(@RequestParam("id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "update-user";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute("user") @Valid User user) {
        userService.updateUser(user);
        return "redirect:/";
    }
}

