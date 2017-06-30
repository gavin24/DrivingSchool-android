package com.example.gavin.drivingschool.Services;

import com.example.gavin.drivingschool.Domain.ContactInfo;

import java.util.ArrayList;

/**
 * Created by gavin on 2017/06/27.
 */
public interface ContactInfoService {
    ContactInfo addContactInfo(ContactInfo animal);
    ContactInfo updateContactInfo( ContactInfo animal);
    ContactInfo deleteContactInfo( ContactInfo animal);
    ContactInfo getContactInfo(Long d);
    ArrayList<ContactInfo> getAllContactInfo( );
    void removeAllContactInfo();
}
