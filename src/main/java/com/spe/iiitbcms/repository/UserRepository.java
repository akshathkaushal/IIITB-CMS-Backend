package com.spe.iiitbcms.repository;
import com.spe.iiitbcms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByRollNo(String rollNo);
}
