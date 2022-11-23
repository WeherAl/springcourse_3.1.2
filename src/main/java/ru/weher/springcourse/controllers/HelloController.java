package ru.weher.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.weher.springcourse.models.User;
import ru.weher.springcourse.service.UserService;

@Controller
public class HelloController {

    private final UserService userService;

    @Autowired
    public HelloController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getIndexWeb(Model model) {
        model.addAttribute("users", userService.listAll());
        return "index";
    }

    @GetMapping("/users")
    public String getUsersWeb(Model model) {
        model.addAttribute("users", userService.listAll());
        return "index";
    }

    @GetMapping("/create")
    public String getCreateUserForm(Model model) {
        model.addAttribute("user", new User());
        return "create";
    }

    @PatchMapping
    public String createUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping(value = "/{id}/edit")
    public String getEditForm(ModelMap model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.show(id));
        return "update";
    }

    @PatchMapping(value = "/users/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.update(id, user);
        return "redirect:/";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/";
    }

}
