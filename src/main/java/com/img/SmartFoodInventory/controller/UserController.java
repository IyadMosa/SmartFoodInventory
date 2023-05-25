package com.img.SmartFoodInventory.controller;

import com.img.SmartFoodInventory.dto.UserDTO;
import com.img.SmartFoodInventory.model.SharedItem;
import com.img.SmartFoodInventory.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Other methods...

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/whoami")
    public ResponseEntity<UserDTO> getHowAmI(Principal principal) {
        String username = principal.getName(); // Retrieve the username from the authenticated user
        UserDTO user = userService.getUserByName(username);
        return ResponseEntity.ok(user);
    }

}
