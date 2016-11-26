package com.gafur.welcome.dao;

import com.gafur.welcome.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Long> {
}
