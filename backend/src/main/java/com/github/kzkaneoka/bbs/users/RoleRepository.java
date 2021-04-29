package com.github.kzkaneoka.bbs.users;

import com.github.kzkaneoka.bbs.users.UserRole;
import com.github.kzkaneoka.bbs.users.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    Role findByName(UserRole name);
}
