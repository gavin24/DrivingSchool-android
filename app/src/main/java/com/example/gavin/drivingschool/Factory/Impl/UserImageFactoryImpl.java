package com.example.gavin.drivingschool.Factory.Impl;

import com.example.gavin.drivingschool.Domain.Image;
import com.example.gavin.drivingschool.Domain.UserImage;
import com.example.gavin.drivingschool.Factory.UserImageFactory;

/**
 * Created by gavin.ackerman on 2017-05-24.
 */

public class UserImageFactoryImpl implements UserImageFactory {
    private static UserImageFactoryImpl factory = null;

    private UserImageFactoryImpl(){

    }

    public static UserImageFactoryImpl getInstance(){
        if (factory == null)
            factory = new UserImageFactoryImpl();

        return factory;
    }


    public UserImage createImage(Long id, long userId, String name, byte[] image)
    {
        UserImage ticket =  new UserImage.Builder().id(id).name(name).userId(userId).image(image)
                .build();

        return ticket;
    }
}
