package com.example.gavin.drivingschool.Factory.Impl;

import com.example.gavin.drivingschool.Domain.ContactInfo;
import com.example.gavin.drivingschool.Factory.ContactInfoFactory;

/**
 * Created by gavin on 2017/06/27.
 */
public class ContactInfoFactoryImpl implements ContactInfoFactory {
    private static ContactInfoFactoryImpl factory = null;

    private ContactInfoFactoryImpl(){

    }

    public static ContactInfoFactoryImpl getInstance(){
        if (factory == null)
            factory = new ContactInfoFactoryImpl();

        return factory;
    }


    public ContactInfo createInfo(Long id,long UserId, String Email ,long PhoneNumber ,String SecondaryEmail)
    {
        ContactInfo ticket =  new ContactInfo.Builder().id(id).UserId(UserId).Email(Email).PhoneNumber(PhoneNumber).SecondaryEmail(SecondaryEmail)
                .build();

        return ticket;
    }
}
