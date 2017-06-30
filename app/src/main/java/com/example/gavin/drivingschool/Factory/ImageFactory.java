package com.example.gavin.drivingschool.Factory;

import com.example.gavin.drivingschool.Domain.Image;

/**
 * Created by gavin.ackerman on 2016-04-28.
 */
public interface ImageFactory {

    Image createImage(Long id, long userId, String name,byte[] image);

}
