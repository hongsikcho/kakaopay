package com.example.demo.owner;

import com.example.demo.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner,Long> {
    Optional<Owner> findByLoginId(String username);
}
