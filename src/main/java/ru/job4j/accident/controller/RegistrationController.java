package ru.job4j.accident.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.accident.model.User;
import ru.job4j.accident.service.AuthorityService;
import ru.job4j.accident.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/users")
public class RegistrationController {

    private final PasswordEncoder encoder;
    private final UserService userService;
    private final AuthorityService authorityService;

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(authorityService.findByAuthority("ROLE_USER"));
        Optional<User> newUser = userService.save(user);
        if (newUser.isEmpty()) {
            return "redirect:/users/fail";
        }
        return "redirect:/users/login";
    }

    @GetMapping("/registration")
    public String getRegistrationPage() {
        return "registration";
    }

    @GetMapping("/fail")
    public String registrationFail() {
        return "registrationFail";
    }

}
