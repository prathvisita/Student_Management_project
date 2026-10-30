package com.pk.Student_Management_project.User;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Users> register(@RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(userService.register(userRequestDto));
    }

    @PostMapping("/login")
    public String login(@RequestBody UserRequestDto userRequestDto) {
        return userService.verify(userRequestDto);
    }
}
