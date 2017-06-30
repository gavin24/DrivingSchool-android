package com.example.gavin.drivingschool.Factory;

import com.example.gavin.drivingschool.Domain.User;

/**
 * Created by gavin.ackerman on 2016-06-21.
 */
public interface UserFactory {
    User createUser(Long id, String name, String surname, String email, String password);
}
