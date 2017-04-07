package com.gafur.welcome.repository;

import com.gafur.welcome.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User repository
 *
 * @author igafurov
 * @since 01.11.2016
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find user by user name
     *
     * @param username user name
     * @return user
     */
    User findByUsername(String username);
}
