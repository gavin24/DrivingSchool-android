package com.example.gavin.drivingschool.Factory.Impl;

import com.example.gavin.drivingschool.Domain.User;
import com.example.gavin.drivingschool.Factory.UserFactory;

/**
 * Created by gavin.ackerman on 2016-06-21.
 */
public class UserFactoryImpl implements UserFactory {
    private static UserFactoryImpl factory = null;

    private UserFactoryImpl(){

    }

    public static UserFactoryImpl getInstance(){
        if (factory == null)
            factory = new UserFactoryImpl();

        return factory;
    }


    public User createUser(Long id, String name, String surname, String email, String password)
    {
        User ticket =  new User.Builder().id(id).name(name).surname(surname).email(email).password(password)
                .build();

        return ticket;
    }
}
