package com.example.project.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import com.example.project.entity.role;

@Repository
public interface RoleRepository extends
        JpaRepository<role, Integer> {
    @Query("SELECT r FROM role r WHERE role_name=?1 ")
    Optional<role> findByRole_Name(String roleName);
}
