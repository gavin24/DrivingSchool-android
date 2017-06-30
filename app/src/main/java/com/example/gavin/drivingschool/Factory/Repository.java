package com.example.gavin.drivingschool.Factory;

import com.example.gavin.drivingschool.Domain.User;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by gavin.ackerman on 2016-06-22.
 */
public interface Repository<E, ID> {
    E findById(ID id);

    E save(E entity);

    E update(E entity);

    E delete(E entity);

    ArrayList<E> findAll();

    int deleteAll();


}
