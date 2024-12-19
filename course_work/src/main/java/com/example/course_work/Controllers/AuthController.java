package com.example.course_work.Controllers;

import com.example.course_work.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${isAdminLaunch:false}")
    private boolean isAdminLaunch;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("isAdminLaunch", isAdminLaunch);
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(String username, String password, String role, Model model) {
        if (role == null || role.isEmpty()) {
            role = "STUDENT";
        }

        userService.registerUser(username, password, role, passwordEncoder);
        model.addAttribute("message", "Регистрация прошла успешно! Вы можете войти.");
        return "login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/about")
    public String showAboutForm() {
        return "about_author";
    }

    @GetMapping("/menu")
    public String showMenu() {
        return "menu";
    }
}

