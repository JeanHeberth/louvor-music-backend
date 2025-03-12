package com.br.musicasbackend.controller;

import com.br.musicasbackend.entity.model.User;
import com.br.musicasbackend.entity.request.UserRequest;
import com.br.musicasbackend.entity.response.UserResponse;
import com.br.musicasbackend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("findUsers")
    public ResponseEntity<List<UserResponse>> findUser() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("findUserName")
    public ResponseEntity<List<UserResponse>> findUserName(@RequestParam String name) {
        List<UserResponse> users = userService.findByName(name);
        return ResponseEntity.ok(users);
    }

    @GetMapping("findUserCargo")
    public ResponseEntity<List<UserResponse>> findUserCargo(@RequestParam String cargo) {
        List<UserResponse> users = userService.findByCargo(cargo);
        return ResponseEntity.ok(users);
    }

    @PostMapping("registerUser")
    public ResponseEntity<User> registerUser(@RequestBody @Valid UserRequest userRequest) {
        return ResponseEntity.ok(userService.saveUser(userRequest));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable String uuid, @RequestBody UserRequest userRequest) {
        UserResponse updatedUser = userService.updateUser(uuid, userRequest);
        return ResponseEntity.ok(updatedUser);
    }
}
