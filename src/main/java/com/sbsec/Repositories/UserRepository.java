package com.sbsec.Repositories;

import com.sbsec.Modeles.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Integer> {

    Optional<User> findByUserName(String username);
}
