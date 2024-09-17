package com.kdu.jpa.repository;

import com.kdu.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> findAllByTenantId(UUID tenantId);
    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET username = :username, logged_in = :loggedIn, time_zone = :timezone WHERE id = :id", nativeQuery = true)
    int updateUser(@Param("id") UUID userId, @Param("username") String username, @Param("loggedIn") int loggedIn, @Param("timezone") String timeZone);
}