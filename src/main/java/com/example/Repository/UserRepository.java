package com.example.Repository;

import com.example.Entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * User repository interface which reacts with Spring Data JPA.
 */
public interface UserRepository extends CrudRepository<User, Integer> {

    /**
     * Method to find user by email.
     * @param email String email
     * @return User user
     */
    @Query("SELECT u from User u where u.email = :email")
    User findUserByEmail(@Param("email") String email);

}
