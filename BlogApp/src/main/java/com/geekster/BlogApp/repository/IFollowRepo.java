package com.geekster.BlogApp.repository;

import com.geekster.BlogApp.model.Follow;
import com.geekster.BlogApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFollowRepo extends JpaRepository<Follow, Long> {
    List<Follow> findByCurrentUserAndCurrentUserFollower(User target, User follower);
}
