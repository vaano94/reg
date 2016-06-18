package com.example.Entity;

import com.example.Entity.User;
import com.example.Repository.RepositoryConfiguration;
import com.example.Repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RepositoryConfiguration.class)
public class DBtest {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    public void TestUserFields() {
        User user = new User ();
        user.setId(1234);
        user.setConfirmed (true);
        user.setPassword ("123!!11");
        user.setEmail ("vaano94@yahoo.com");


        assertEquals (user.getId (), 1234);
        assertTrue (user.isConfirmed ());
        assertEquals (user.getPassword (), "123!!11");
        assertEquals (user.getEmail (), "vaano94@yahoo.com");
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
        assertEquals (user.getId (), 1);

        User userByEmail = userRepository.findUserByEmail("blah@blah.com");
        System.out.println("Fetched user by email: "+userByEmail.getEmail());

        //update description and save
        fetchedUser.setConfirmed(true);
        assertTrue (fetchedUser.isConfirmed());
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
