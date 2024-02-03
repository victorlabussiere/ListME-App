package com.listme.api.web.domains.friends;

import com.listme.api.models.FriendshipModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendshipRepository extends JpaRepository<FriendshipModel, Long> {

    boolean existsByUserIdAndFriendId(Long userId, Long friendId);
}
