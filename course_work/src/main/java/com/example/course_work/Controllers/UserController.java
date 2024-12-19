package com.example.course_work.Controllers;

import com.example.course_work.Models.Role;
import com.example.course_work.Models.User;
import com.example.course_work.Repositories.RoleRepository;
import com.example.course_work.Services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;
import java.util.Optional;


@Slf4j
@Controller
@RequestMapping("/admin/users")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/")
    @Secured("ADMIN")
    public String listUsers(Model model) {
        List<User> listUser = userService.getAllUsers();
        List<Role> roles = userService.getAllRoles();
        model.addAttribute("listUser", listUser);
        model.addAttribute("roles", roles);
        return "users";
    }
    @GetMapping("/edit/{id}")
    public ModelAndView showEditUserRolesForm(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_user");
        User user = userService.getById(id);
        List<Role> allRoles = roleRepository.findAll();
        mav.addObject("user", user);
        mav.addObject("allRoles", allRoles);
        return mav;
    }


    @PostMapping("/save/{id}")
    public String saveUser(@PathVariable(name = "id") Long id, @RequestParam("roles") Long roleIds) {
        Role role = roleRepository.findById(roleIds).orElseThrow(() -> new RuntimeException("Role not found"));
        userService.updateRoles(id, role);
        return "redirect:/admin/users/";
    }

    @GetMapping("/delete/{id}")
    public String deleteCargo(@PathVariable(name = "id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users/";
    }

    @GetMapping("/search")
    public String searchPosts(Model model, @RequestParam("username") String username) {

        Optional<User> user = userService.getUserByUsername(username);
        model.addAttribute("user", user);
        return "users";
    }


}