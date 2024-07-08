package com.BEFresherTP.repository;

import com.BEFresherTP.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public List<User> findAllByDeletedFalse();

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.firstName = :firstName, u.lastName = :lastName, u.email = :email, u.gender = :gender WHERE u.id = :id AND u.version = :version")
    int updateUserData(int id, String firstName, String lastName, String email, String gender, int version);

    public User findByEmail(String email);
}
