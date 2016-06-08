package com.example;

import com.example.Entity.User;
import com.example.Repository.RepositoryConfiguration;
import com.example.Repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by ivan on 03/06/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RepositoryConfiguration.class)
public class DBtest {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    public void testSaveProduct() {
        User user = new User();
        user.setPassword("1112!!as");
        user.setEmail("blah@blah.com");
        user.setConfirmed(false);
        user.setId(1);

        userRepository.save(user);

        User fetchedUser = userRepository.findOne(user.getId());
        assertNotNull(fetchedUser);

        User userByEmail = userRepository.findUserByEmail("blah@blah.com");
        System.out.println("Fetched user by email: "+userByEmail.getEmail());

        //update description and save
        fetchedUser.setConfirmed(true);
        userRepository.save(fetchedUser);

        User fetchedUpdateUser = userRepository.findOne(user.getId());
        assertEquals(fetchedUpdateUser.isConfirmed(), true);

        int count = 0;
        Iterable<User> users = userRepository.findAll();
        for(User u : users) {
            count++;
        }
        assertEquals(count,1);

        User fetchedByMail = userRepository.findUserByEmail(user.getEmail());
        assertEquals(fetchedByMail.getEmail(),user.getEmail());


    }

}
