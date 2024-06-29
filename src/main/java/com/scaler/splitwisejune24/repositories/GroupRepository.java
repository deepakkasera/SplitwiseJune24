package com.scaler.splitwisejune24.repositories;

import com.scaler.splitwisejune24.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {

    @Override
    Optional<Group> findById(Long groupId);
}
