package com.listme.api.web.controllers;

import com.listme.api.models.UserModel;
import com.listme.api.web.domains.friends.CreateFriendshipDTO;
import com.listme.api.web.domains.friends.FriendsService;
import com.listme.api.web.domains.users.RetrieveUserDTO;
import com.listme.api.web.domains.users.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("friends")
public class FriendsController {
    private final FriendsService friendsService;
    private final UserService userService;

    public FriendsController(FriendsService friendsService, UserService userService) {
        this.friendsService = friendsService;
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<?> addFriend(@RequestBody CreateFriendshipDTO createFriendshipDTO) {
        try {
            com.listme.api.models.FriendshipModel result = this.friendsService.create(createFriendshipDTO);
            UserModel friend = this.userService.getById(result.getFriend().getId());
            RetrieveUserDTO response = new RetrieveUserDTO(friend.getId(), friend.getName(), friend.getEmail(), friend.getUserRole());
            return ResponseEntity.status(HttpStatus.CREATED).body(String.format("Friend successfully added: %s", response.name()));
        } catch (RuntimeException err) {
            return ResponseEntity.badRequest().body(err.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getUserFriendships(@RequestParam(value = "id", defaultValue = "") Long id) {
        try{
            List<UserModel> result = this.friendsService.getAllUserFriends(id);
            List<RetrieveUserDTO> response = result.stream().map(r -> new RetrieveUserDTO(r.getId(), r.getName(), r.getEmail(), r.getUserRole())).toList();
            return ResponseEntity.ok().body(response);
        } catch (RuntimeException err) {
            return ResponseEntity.badRequest().body(err.getMessage());
        }
    }

}
