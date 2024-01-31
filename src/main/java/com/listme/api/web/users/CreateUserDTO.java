package com.listme.api.web.users;

public record CreateUserDTO(String name, String email, String password, String role) {
}
