package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/")
    public String adminPanel(ModelMap model) {
        List<User> users = userService.showAll();
        model.addAttribute("users", users);
        return "admin/index";
    }

    @GetMapping("/new")
    public String newUser(Model model) {

        model.addAttribute("user", new User());

        List<Role> roles = roleService.findAll();
        model.addAttribute("allRoles", roles);

        return "admin/new";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.show(id));
        return "admin/show";
    }

    @PostMapping("/")
    public String create(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin/";
    }

    @GetMapping(value = "/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.show(id));
        List<Role> roles = roleService.findAll();
        model.addAttribute("allRoles", roles);
        return "admin/edit";
    }


    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/admin/";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin/";
    }
}