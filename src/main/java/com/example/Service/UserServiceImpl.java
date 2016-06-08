package com.example.Service;

import com.example.Entity.User;
import com.example.Messaging.Publisher;
import com.example.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    private Publisher publisher;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findOne(id);
    }

    @Override
    public boolean updateUser(User user) {
        User fetchedUser = userRepository.findUserByEmail(user.getEmail());
        fetchedUser.setConfirmed(true);
        fetchedUser = userRepository.save(fetchedUser);
        return fetchedUser != null;
    }

    @Override
    public void deleteUser(User user) {
        User fetchedUser = findById(user.getId());
        userRepository.delete(user);
    }

    @Override
    public boolean confirmEmail(String link) {
        String encodedLink = new String(Base64Utils.decode(link.getBytes()));
        String email = encodedLink.split("\\|")[1];
        //Integer id = Integer.valueOf(encodedLink.split("\\|")[2]);
        User fetchedUser = userRepository.findUserByEmail(email);
        boolean isUpdated =  updateUser(fetchedUser);
        System.out.println("is Updated expected: true, returned: " + isUpdated);
        return isUpdated;
    }

    @Override
    public String createConfirmationLink(User user) {
        return Base64Utils.encodeToString((user.getPassword()+"|"+user.getEmail()+"|"+user.getId()).getBytes());
    }

    @Override
    public void publishMessage(User user) {
        userRepository.save(user);
        System.out.println("User id: " + user.getId());
        publisher.publishMessage(user);
    }


}
