package com.listme.api.web.users;

import com.listme.api.models.UserModel;
import com.listme.api.web.errors.EmailInUseException;
import com.listme.api.web.errors.InvalidPasswordException;
import com.listme.api.web.errors.NullPasswordException;
import com.listme.api.web.errors.UserNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public UserModel create(CreateUserDTO createUserDTO) {
        boolean emailExists = this.userRepository.existsByEmail(createUserDTO.email());
        if(emailExists) throw new EmailInUseException();
        String password = encoder.encode(createUserDTO.password());
        UserModel user = new UserModel(createUserDTO.name(), createUserDTO.email(), password, createUserDTO.role());

        return this.userRepository.save(user);
    }

    public List<UserModel> getAll() {
        var result = this.userRepository.findAll();
        if(result.isEmpty()) throw new UserNotFoundException();
        return result;
    }

    public UserModel getById(Long id) {
        return this.userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public UserModel updateUser(Long id, UpdateUserDTO updateUserDTO) {
        UserModel user = this.userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        if(updateUserDTO.name() != null) user.setName(updateUserDTO.name());
        if(updateUserDTO.email() != null) user.setEmail(updateUserDTO.email());
        if(updateUserDTO.role() != null) user.setUserRole(updateUserDTO.role());
        return this.userRepository.save(user);
    }

    public UserModel updateUserPassword(Long id, UpdateUserPasswordDTO updateUserPasswordDTO) {
        if(updateUserPasswordDTO.oldPassword() == null || updateUserPasswordDTO.newPassword() == null) throw new NullPasswordException();
        UserModel user = this.userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        if (!encoder.matches(updateUserPasswordDTO.oldPassword(), user.getPassword())) throw new InvalidPasswordException();
        String newPassword = encoder.encode(updateUserPasswordDTO.newPassword());
        user.setPassword(newPassword);
        return this.userRepository.save(user);
    }

    public UserModel remove(Long id) {
        UserModel user = this.userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        this.userRepository.delete(user);
        return user;
    }
}
