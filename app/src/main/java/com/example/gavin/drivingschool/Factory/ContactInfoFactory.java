package com.example.gavin.drivingschool.Factory;

import com.example.gavin.drivingschool.Domain.ContactInfo;

/**
 * Created by gavin on 2017/06/27.
 */
public interface ContactInfoFactory {
    ContactInfo createInfo(Long id,long UserId, String Email ,long PhoneNumber ,String SecondaryEmail);

}
