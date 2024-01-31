package com.listme.api.web.users;

import com.listme.api.models.UserModel;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final String USER_NOT_FOUND= "User not found exception";
    private final String EMAIL_EXISTS = "E-mail already in use exception";

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public UserModel create(CreateUserDTO createUserDTO) {
        boolean emailExists = this.userRepository.existsByEmail(createUserDTO.email());
        if(emailExists) throw new RuntimeException(EMAIL_EXISTS);
        String password = encoder.encode(createUserDTO.password());
        UserModel user =(UserModel) new UserModel();
        user.setName(createUserDTO.name());
        user.setEmail(createUserDTO.email());
        user.setPassword(password);
        user.setUserRole(createUserDTO.role());
        return this.userRepository.save(user);
    }

    public List<UserModel> getAll() {
        var result = this.userRepository.findAll();
        if(result.isEmpty()) throw new RuntimeException(USER_NOT_FOUND);
        return result;
    }

    public UserModel getById(Long id) {
        var result = this.userRepository.findById(id);
        if(result.isEmpty()) throw new RuntimeException(USER_NOT_FOUND);
        return result.get();
    }

    public UserModel updateUser(Long id, UpdateUserDTO updateUserDTO) {
        Optional<UserModel> optionalUser = this.userRepository.findById(id);
        if(optionalUser.isEmpty()) throw new RuntimeException(USER_NOT_FOUND);
        UserModel user = optionalUser.get();

        if(updateUserDTO.name() != null) user.setName(updateUserDTO.name());
        if(updateUserDTO.email() != null) user.setEmail(updateUserDTO.email());
        if(updateUserDTO.role() != null) user.setUserRole(updateUserDTO.role());

        return this.userRepository.save(user);
    }

    public UserModel updateUserPassword(Long id, UpdateUserPasswordDTO updateUserPasswordDTO) {
        Optional<UserModel> optionalUser = this.userRepository.findById(id);
        if(optionalUser.isEmpty()) throw new RuntimeException(USER_NOT_FOUND);
        UserModel user = optionalUser.get();

        if(updateUserPasswordDTO == null) throw new RuntimeException("Password is null exception");
        boolean valid = encoder.matches(updateUserPasswordDTO.userPassword(), user.getPassword());
        if(!valid) throw new RuntimeException("User password doesn't match");

        String password = encoder.encode(updateUserPasswordDTO.password());
        user.setPassword(password);
        return this.userRepository.save(user);
    }

    public UserModel remove(Long id) {
        Optional<UserModel> optionalUser = this.userRepository.findById(id);
        if(optionalUser.isEmpty()) throw new RuntimeException(USER_NOT_FOUND);
        UserModel user = optionalUser.get();

        this.userRepository.delete(user);
        return user;
    }
}
