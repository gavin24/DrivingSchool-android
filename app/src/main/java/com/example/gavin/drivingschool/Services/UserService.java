package com.example.gavin.drivingschool.Services;

import com.example.gavin.drivingschool.Domain.User;

import java.util.ArrayList;

/**
 * Created by gavin.ackerman on 2016-06-21.
 */
public interface UserService {
    User addUser(User animal);
    User updateUser( User animal);
    User deleteUser( User animal);
    User getUser(Long d);
    User getUserByEmail(String email);
    ArrayList<User> getAllUsers( );

    boolean isAuthentic(String userName,String Password);
    void removeAllUsers();
}
