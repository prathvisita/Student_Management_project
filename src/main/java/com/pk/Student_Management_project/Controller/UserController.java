package com.pk.Student_Management_project.Controller;

import com.pk.Student_Management_project.DTO.UserRequestDto;
import com.pk.Student_Management_project.Model.Users;
import com.pk.Student_Management_project.Service.UserService;
import lombok.AllArgsConstructor;
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
}
