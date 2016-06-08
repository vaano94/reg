package com.example.Repository;

import com.example.Entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by ivan on 03/06/16.
 */
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("SELECT u from User u where u.email = :email")
    public User findUserByEmail(@Param("email")String email);

}
