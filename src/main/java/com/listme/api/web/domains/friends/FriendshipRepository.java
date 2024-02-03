package com.listme.api.web.domains.friends;

import com.listme.api.models.FriendshipModel;
import com.listme.api.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface FriendshipRepository extends JpaRepository<FriendshipModel, Long> {}
