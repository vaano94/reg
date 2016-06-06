package com.example.Service;

import com.example.Entity.User;
import org.springframework.stereotype.Component;

public interface UserService {

    public User findById(Integer id);
    public boolean updateUser(User user);
    public void deleteUser(User user);
    public boolean confirmEmail(String link);
    public String createConfirmationLink(User user);
    public void publishMessage(User user);

}
