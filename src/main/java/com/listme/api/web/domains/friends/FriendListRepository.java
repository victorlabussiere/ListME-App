package com.listme.api.web.domains.friends;

import com.listme.api.models.FriendsListModel;
import com.listme.api.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FriendListRepository extends JpaRepository<FriendsListModel, Long> {
    Optional<FriendsListModel> findByUser(UserModel user);
}
