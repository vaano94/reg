package com.example.Service;

import com.example.Entity.User;
/**
 * User service to react with user actions.
 */
public interface UserService {
    /**
     * Finds user by id.
     * @param id int id
     * @return User instance
     */
    User findById(Integer id);

    /**
     * Updated given user.
     * @param user User
     * @return User instance
     */
    boolean updateUser(User user);

    /**
     * Delets given user.
     * @param user User user
     */
    void deleteUser(User user);

    /**
     * Confirms user email with a given link.
     * @param link String link
     * @return returns true if confirmed.
     */
    boolean confirmEmail(String link);

    /**
     * Generates confirmation link based on user details.
     * @param user User user
     * @return String confirmation link
     */
    String createConfirmationLink(User user);

    /**
     * Says {@link com.example.Messaging.Publisher} to send message in ActiveMQ.
     * @param user User sent to Publisher.
     */
    void publishMessage(User user);

}
