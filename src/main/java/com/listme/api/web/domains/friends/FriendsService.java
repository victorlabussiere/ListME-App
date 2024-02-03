package com.listme.api.web.domains.friends;

import com.listme.api.models.FriendsListHasFriendshipModel;
import com.listme.api.models.FriendsListModel;
import com.listme.api.models.FriendshipModel;
import com.listme.api.models.UserModel;
import com.listme.api.web.domains.users.UserRepository;
import com.listme.api.web.errors.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FriendsService {

    private final FriendshipRepository friendshipRepository;
    private final FriendListRepository friendListRepository;
    private final FriendsListHasFriendshipRepository friendsListHasFriendshipRepository;
    private final UserRepository userRepository;

    public FriendsService(FriendshipRepository friendshipRepository,
                          UserRepository userRepository,
                          FriendListRepository friendListRepository,
                          FriendsListHasFriendshipRepository friendsListHasFriendshipRepository) {
        this.friendshipRepository = friendshipRepository;
        this.userRepository = userRepository;
        this.friendListRepository = friendListRepository;
        this.friendsListHasFriendshipRepository = friendsListHasFriendshipRepository;
    }


    public FriendshipModel create(CreateFriendshipDTO createFriendshipDTO) {
        if (createFriendshipDTO.friend_id().equals(createFriendshipDTO.user_id())) {
            throw new RuntimeException("You can't send friend request your own account");
        }

        // TODO: FRIENDSHIP ALREADY EXISTS EXCEPTION

        // CREATE FRIENDSHIP
        UserModel user = userRepository.findById(createFriendshipDTO.user_id()).orElseThrow(UserNotFoundException::new);
        UserModel friend = userRepository.findById(createFriendshipDTO.friend_id()).orElseThrow(UserNotFoundException::new);
        FriendshipModel friendship = new FriendshipModel(user, friend);

        // GET OR CREATE FRIENDS LIST
        Optional<FriendsListModel> optionalFriendsListModel = this.friendListRepository.findByUser(user);
        FriendsListModel friendsList;
        friendsList = optionalFriendsListModel.orElseGet(() -> new FriendsListModel(user));
        this.friendListRepository.save(friendsList);

        // ADD FRIENDSHIP INTO USERS FRIENDS LIST
        FriendsListHasFriendshipModel friendsListHasFriendship = new FriendsListHasFriendshipModel(friendsList, friendship);

        if(friendship != null && friendsList != null && friendsListHasFriendship != null) {
            this.friendshipRepository.save(friendship);
            this.friendsListHasFriendshipRepository.save(friendsListHasFriendship);
        } else throw new RuntimeException("Some error occurs. Your friendship was not saved");

        return friendship;
    }

    public List<UserModel> getAllUserFriends(Long id) {
        UserModel user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return this.friendshipRepository.findAll().stream()
                .filter(f -> f.getUser().getId().equals(id))
                .map(f -> {
                    return userRepository.findById(f.getFriend().getId()).orElse(new UserModel());
                }).toList();
    }
}
