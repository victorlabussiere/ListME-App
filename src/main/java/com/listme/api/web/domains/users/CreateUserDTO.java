package com.listme.api.web.domains.users;

public record CreateUserDTO(String name, String email, String password, String role) {
}
