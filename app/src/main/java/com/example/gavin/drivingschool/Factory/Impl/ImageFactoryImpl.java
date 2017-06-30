package com.example.gavin.drivingschool.Factory.Impl;

import com.example.gavin.drivingschool.Domain.Image;
import com.example.gavin.drivingschool.Factory.ImageFactory;

/**
 * Created by gavin.ackerman on 2016-04-28.
 */
public class ImageFactoryImpl implements ImageFactory{
    private static ImageFactoryImpl factory = null;

    private ImageFactoryImpl(){

    }

    public static ImageFactoryImpl getInstance(){
        if (factory == null)
            factory = new ImageFactoryImpl();

        return factory;
    }


    public Image createImage(Long id, long userId, String name,byte[] image)
    {
        Image ticket =  new Image.Builder().id(id).name(name).userId(userId).image(image)
                .build();

        return ticket;
    }
}
