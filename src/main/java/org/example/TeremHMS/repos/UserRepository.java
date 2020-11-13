package org.example.TeremHMS.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import org.example.TeremHMS.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByActivationCode(String code);
}