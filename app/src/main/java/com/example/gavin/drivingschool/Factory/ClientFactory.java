package com.example.gavin.drivingschool.Factory;

import com.example.gavin.drivingschool.Domain.Client;

/**
 * Created by gavin on 2017/06/27.
 */
public interface ClientFactory {
    Client createClient(Long id,String Email, String Password  ,long PhoneNumber ,String Name ,String Surname,long ClientAddressId);

}
