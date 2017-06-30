package com.example.gavin.drivingschool.Factory.Impl;

import com.example.gavin.drivingschool.Domain.Client;
import com.example.gavin.drivingschool.Factory.ClientFactory;

/**
 * Created by gavin on 2017/06/27.
 */
public class ClientFactoryImpl implements ClientFactory {
    private static ClientFactoryImpl factory = null;

    private ClientFactoryImpl(){

    }

    public static ClientFactoryImpl getInstance(){
        if (factory == null)
            factory = new ClientFactoryImpl();

        return factory;
    }


    public Client createClient(Long id,String Email, String Password  ,long PhoneNumber ,String Name ,String Surname,long ClientAddressId)
    {
        Client ticket =  new Client.Builder().id(id).Email(Email).Password(Password).PhoneNumber(PhoneNumber).Name(Name).Surname(Surname).ClientAddressId(ClientAddressId)
                .build();

        return ticket;
    }
}
