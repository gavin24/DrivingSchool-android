package com.example.gavin.drivingschool.Factory.Impl;

import com.example.gavin.drivingschool.Domain.Address;
import com.example.gavin.drivingschool.Factory.AddressFactory;

/**
 * Created by gavin on 2017/06/27.
 */
public class AddressFactoryImpl implements AddressFactory {
    private static AddressFactoryImpl factory = null;

    private AddressFactoryImpl(){

    }

    public static AddressFactoryImpl getInstance(){
        if (factory == null)
            factory = new AddressFactoryImpl();

        return factory;
    }


    public Address createAddress(Long id,long ClientId, String Line1 ,String Line2 ,String Line3,String Line4,String Line5)
    {
        Address ticket =  new Address.Builder().id(id).ClientId(ClientId).Line1(Line1).Line2(Line2).Line3(Line3).Line4(Line4).Line5(Line5)
                .build();

        return ticket;
    }
}
