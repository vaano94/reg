package com.example.Repository;

import com.example.Entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ivan on 03/06/16.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
}
