package com.example.gavin.drivingschool.Services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.gavin.drivingschool.Domain.ContactInfo;
import com.example.gavin.drivingschool.Repositories.ContactInfoRepository;
import com.example.gavin.drivingschool.Repositories.Impl.ContactInfoRepositoryImpl;
import com.example.gavin.drivingschool.Services.ClientService;
import com.example.gavin.drivingschool.Services.ContactInfoService;

import java.util.ArrayList;

/**
 * Created by gavin on 2017/06/27.
 */
public class ContactInfoServiceImpl extends Service implements ContactInfoService {
    final private ContactInfoRepository repository;

    private final IBinder localBinder = new ContactInfoServiceLocalBinder();

    private static ContactInfoServiceImpl service = null;

    public static ContactInfoServiceImpl getInstance()
    {
        if(service == null)
            service = new ContactInfoServiceImpl();
        return service;
    }

    public ContactInfoServiceImpl()
    {
        repository = new ContactInfoRepositoryImpl();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class ContactInfoServiceLocalBinder extends Binder {
        public ContactInfoServiceImpl getService() {
            return ContactInfoServiceImpl.this;
        }
    }


    @Override
    public ContactInfo addContactInfo(ContactInfo animal) {
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
    public ContactInfo deleteContactInfo(ContactInfo animal) {
        return repository.delete(animal);
    }

    @Override
    public ArrayList<ContactInfo> getAllContactInfo() {
        try {
            ArrayList<ContactInfo> result = new ArrayList<>();
            if (result.addAll(repository.findAll()))
                return result;
            else
                return new ArrayList<ContactInfo>();
        } catch (Exception x) {
            x.printStackTrace();
        }
        return null;
    }

    @Override
    public void removeAllContactInfo() {
        repository.deleteAll();
    }
    @Override
    public ContactInfo updateContactInfo(ContactInfo animal) {
        return repository.update(animal);
    }

    @Override
    public ContactInfo getContactInfo(Long Id) {
        return repository.findById(Id);
    }
}
