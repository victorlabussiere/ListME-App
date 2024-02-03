package com.listme.api.models;

import jakarta.persistence.*;

@Entity(name = "tb_friend_list")
public class FriendsListModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long friend_list_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    UserModel user;

    public FriendsListModel() {
    }

    public FriendsListModel(UserModel user) {
        this.user = user;
    }
}
