package com.example.gavin.drivingschool.Repositories;

import com.example.gavin.drivingschool.Domain.UserImage;
import com.example.gavin.drivingschool.Factory.Repository;

/**
 * Created by gavin.ackerman on 2017-05-24.
 */

public interface UserImageRepository extends Repository<UserImage,Long> {
    UserImage findByUserId(Long id);
}
