package com.spe.iiitbcms.repository;

import com.spe.iiitbcms.model.Post;
import com.spe.iiitbcms.model.User;
import com.spe.iiitbcms.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}
