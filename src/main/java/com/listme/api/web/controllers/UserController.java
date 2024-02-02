package com.listme.api.web.controllers;

import com.listme.api.models.UserModel;
import com.listme.api.web.errors.EmailInUseException;
import com.listme.api.web.errors.NullPasswordException;
import com.listme.api.web.errors.UserNotFoundException;
import com.listme.api.web.users.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("users")
public class UserController{

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> store(@RequestBody CreateUserDTO createUserDTO) {
        try {
            UserModel result = this.userService.create(createUserDTO);
            RetrieveUserDTO response = new RetrieveUserDTO(result.getId(), result.getName(), result.getEmail(), result.getUserRole());
            return ResponseEntity.ok().body(response);
        } catch (EmailInUseException err) {
            return ResponseEntity.status(err.getStatusCode()).body(err.getMessage());
        } catch (RuntimeException err) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<?> index() {
        try{
            List<UserModel> result = this.userService.getAll();
            List<RetrieveUserDTO> response = result.stream().map(user -> {
                return new RetrieveUserDTO(user.getId(), user.getName(), user.getEmail(), user.getUserRole());
            }).collect(Collectors.toList());
            return ResponseEntity.ok().body(response);
        } catch (UserNotFoundException err) {
            return ResponseEntity.status(err.getStatusCode()).body(err.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> show(@PathVariable(name = "id")Long id) {
        try {
            UserModel result = this.userService.getById(id);
            RetrieveUserDTO response = new RetrieveUserDTO(result.getId(), result.getName(), result.getEmail(), result.getUserRole());
            return ResponseEntity.ok().body(response);
        } catch (UserNotFoundException err) {
            return ResponseEntity.status(err.getStatusCode()).body(err.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> edit(@PathVariable(name = "id") Long id, @RequestBody UpdateUserDTO updateUserDTO) {
        try {
            UserModel result = this.userService.updateUser(id, updateUserDTO);
            RetrieveUserDTO response = new RetrieveUserDTO(result.getId(), result.getName(), result.getEmail(), result.getUserRole());
            return ResponseEntity.ok().body(response);
        } catch (UserNotFoundException err) {
            return ResponseEntity.status(err.getStatusCode()).body(err.getMessage());
        }
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<?> editPassword(@PathVariable(name = "id") Long id, @RequestBody UpdateUserPasswordDTO updateUserPasswordDTO) {
        try {
            UserModel result = this.userService.updateUserPassword(id, updateUserPasswordDTO);
            RetrieveUserDTO response = new RetrieveUserDTO(result.getId(), result.getName(), result.getEmail(), result.getUserRole());
            return ResponseEntity.ok().body(response);
        } catch (NullPasswordException err) {
            return ResponseEntity.badRequest().body(err.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> destroy(@PathVariable(name = "id") Long id) {
        try {
            UserModel result = this.userService.remove(id);
            RetrieveUserDTO response = new RetrieveUserDTO(result.getId(), result.getName(), result.getEmail(), result.getUserRole());
            return ResponseEntity.ok().body(response);
        } catch (UserNotFoundException err) {
            return ResponseEntity.status(err.getStatusCode()).body(err.getMessage());
        }
    }
}
