package com.listme.api.models;

import jakarta.persistence.*;

@Entity(name = "friends_list_has_friendship")
public class FriendsListHasFriendshipModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "friends_list_id", nullable = false)
    private FriendsListModel friendList;

    @ManyToOne
    @JoinColumn(name = "friendship_id", nullable = false)
    private FriendshipModel friendship;

    public FriendsListHasFriendshipModel() {
    }

    public FriendsListHasFriendshipModel(FriendsListModel friendList, FriendshipModel friendship) {
        this.friendList = friendList;
        this.friendship = friendship;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FriendsListModel getFriendList() {
        return friendList;
    }

    public void setFriendList(FriendsListModel friendList) {
        this.friendList = friendList;
    }

    public FriendshipModel getFriendship() {
        return friendship;
    }

    public void setFriendship(FriendshipModel friendship) {
        this.friendship = friendship;
    }
}
