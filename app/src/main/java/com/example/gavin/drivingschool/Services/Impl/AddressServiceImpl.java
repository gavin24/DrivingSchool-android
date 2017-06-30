package com.example.gavin.drivingschool.Services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.gavin.drivingschool.Domain.Address;
import com.example.gavin.drivingschool.Repositories.AddressRepository;
import com.example.gavin.drivingschool.Repositories.Impl.AddressRepositoryImpl;
import com.example.gavin.drivingschool.Services.AddressService;

import java.util.ArrayList;

/**
 * Created by gavin on 2017/06/27.
 */
public class AddressServiceImpl  extends Service implements AddressService {
    final private AddressRepository repository;

    private final IBinder localBinder = new AddressServiceLocalBinder();

    private static AddressServiceImpl service = null;

    public static AddressServiceImpl getInstance()
    {
        if(service == null)
            service = new AddressServiceImpl();
        return service;
    }

    public AddressServiceImpl()
    {
        repository = new AddressRepositoryImpl();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class AddressServiceLocalBinder extends Binder {
        public AddressServiceImpl getService() {
            return AddressServiceImpl.this;
        }
    }


    @Override
    public Address addAddress(Address animal) {
        try{
            return repository.save(animal);
        }
        catch(Exception x)

        {
            x.printStackTrace();
        }
        return null;
    }
    @Override
    public Address deleteAddress(Address animal) {
        return repository.delete(animal);
    }

    @Override
    public ArrayList<Address> getAllAddress() {
        try {
            ArrayList<Address> result = new ArrayList<>();
            if (result.addAll(repository.findAll()))
                return result;
            else
                return new ArrayList<Address>();
        } catch (Exception x) {
            x.printStackTrace();
        }
        return null;
    }

    @Override
    public void removeAllAddress() {
        repository.deleteAll();
    }
    @Override
    public Address updateAddress(Address animal) {
        return repository.update(animal);
    }

    @Override
    public Address getAddress(Long Id) {
        return repository.findById(Id);
    }

}
