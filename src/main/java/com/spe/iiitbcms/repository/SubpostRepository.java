package com.spe.iiitbcms.repository;

import com.spe.iiitbcms.model.Subpost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubpostRepository extends JpaRepository<Subpost, Long> {

    Optional<Subpost> findByName(String subpostName);
}
