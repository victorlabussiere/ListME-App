package com.listme.api.web.users;

public record UpdateUserPasswordDTO(String newPassword, String oldPassword) {
}
