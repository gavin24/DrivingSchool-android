package com.example.gavin.drivingschool.Factory;

import com.example.gavin.drivingschool.Domain.Image;
import com.example.gavin.drivingschool.Domain.UserImage;

/**
 * Created by gavin.ackerman on 2017-05-24.
 */

public interface UserImageFactory {
    UserImage createImage(Long id, long userId, String name, byte[] image);
}
