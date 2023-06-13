package com.Rent.repository;

import com.Rent.models.Product;
import com.Rent.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public boolean existsByEmail(String userEmail);
    public User findByEmail(String userEmail);

    public User findByUserName(String username);
    @Query("select u from User u where u.email = :email")
    public User getUserByUserName(@Param("email") String email);

}
