package com.example.gavin.drivingschool.Factory;

import com.example.gavin.drivingschool.Domain.Address;

/**
 * Created by gavin on 2017/06/27.
 */
public interface AddressFactory {
    Address createAddress(Long id,long ClientId, String Line1 ,String Line2 ,String Line3,String Line4,String Line5);

}
