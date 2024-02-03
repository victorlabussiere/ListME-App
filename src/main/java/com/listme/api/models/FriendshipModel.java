package com.listme.api.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "tb_friendship")
public class    FriendshipModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long friendship_id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserModel user;

    @ManyToOne
    @JoinColumn(name = "friend_id")
    private UserModel friend;

    public FriendshipModel() {
    }

    public FriendshipModel(UserModel user, UserModel friend) {
        this.user = user;
        this.friend = friend;
    }

    public FriendshipModel(Long aLong, Long aLong1) {
    }

    public Long getFriendship_id() {
        return friendship_id;
    }

    public UserModel getUser() {
        return user;
    }

    public UserModel getFriend() {
        return friend;
    }
}
