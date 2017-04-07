package com.gafur.welcome.repository;

import com.gafur.welcome.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Role repository
 *
 * @author igafurov
 * @since 01.11.2016
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
}
