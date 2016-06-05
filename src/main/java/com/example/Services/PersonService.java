package com.example.Services;

import com.example.Entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by ivan on 03/06/16.
 */
@Service
public class PersonService {

    @Autowired
    private JdbcTemplate template;

    private static Logger logger = Logger.getLogger(PersonService.class);

    /**
     * @param user Entity to insert into db. Must have email and password fields
     * @return returns 1 if added and 0 if not
     */
    public  boolean addUser(User user) {
        System.out.println();
        User user1 = new User();
        user1.setEmail(user.getEmail());
        user1.setPassword(user1.getPassword());
        System.out.println("try to insert data :" + user.getEmail() + user.getPassword());
        String sql = "INSERT INTO USER (email, password, is_confirmed) VALUES (?,?,?)";
        int result = template.update(sql, user1.getEmail(), user1.getPassword(), false);
        logger.info("Attempt to insert User in database: " + result);
        return result>0;
    }

    public static boolean updateUser(User user) {

        return true;
    }


}
