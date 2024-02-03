package com.listme.api.web.domains.users;

public record UpdateUserPasswordDTO(String newPassword, String oldPassword) {
}
