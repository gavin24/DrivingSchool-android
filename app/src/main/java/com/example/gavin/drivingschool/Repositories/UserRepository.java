package com.example.gavin.drivingschool.Repositories;

import com.example.gavin.drivingschool.Domain.User;
import com.example.gavin.drivingschool.Factory.Repository;

/**
 * Created by gavin.ackerman on 2016-06-21.
 */
public interface UserRepository extends Repository<User,Long> {
    User findByEmail(String email);
}
