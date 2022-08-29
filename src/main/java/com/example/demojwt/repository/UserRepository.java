package com.example.demojwt.repository;

import com.example.demojwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username); //  tim kiem username co ton tai trong database khong?
    Boolean existsByUsername(String username); //Kiem tra xem username co ton tai ko, khi tao du lieu?
    Boolean existsByEmail(String email); //kiem tra xem co ton tai email trong DB khi tao du lieu
}
