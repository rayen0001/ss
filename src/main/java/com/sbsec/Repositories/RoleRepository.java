package com.sbsec.Repositories;

import com.sbsec.Modeles.ERole;
import com.sbsec.Modeles.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}
