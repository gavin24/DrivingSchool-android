package com.example.gavin.drivingschool.Services;

import com.example.gavin.drivingschool.Domain.Address;

import java.util.ArrayList;

/**
 * Created by gavin on 2017/06/27.
 */
public interface AddressService {
    Address addAddress(Address animal);
    Address updateAddress( Address animal);
    Address deleteAddress( Address animal);
    Address getAddress(Long d);
    ArrayList<Address> getAllAddress( );
    void removeAllAddress();
}
