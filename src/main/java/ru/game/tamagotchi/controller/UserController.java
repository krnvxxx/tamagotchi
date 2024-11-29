package ru.game.tamagotchi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import ru.game.tamagotchi.model.UserEntity;
import ru.game.tamagotchi.services.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    public UserEntity createUser(@RequestBody UserEntity user) {
        return userService.create(user);
    }

    @GetMapping("/myprofile")
    public UserEntity profileUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userService.findByLogin(user.getUsername());
    }
}

