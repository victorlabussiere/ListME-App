package com.listme.api.web.domains.friends;

import com.listme.api.models.FriendsListHasFriendshipModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendsListHasFriendshipRepository extends JpaRepository<FriendsListHasFriendshipModel, Long> {
}
