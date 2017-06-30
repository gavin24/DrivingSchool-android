package com.example.gavin.drivingschool.Services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.gavin.drivingschool.Domain.Client;
import com.example.gavin.drivingschool.Repositories.ClientRepository;
import com.example.gavin.drivingschool.Repositories.Impl.ClientRepositoryImpl;
import com.example.gavin.drivingschool.Services.CarService;
import com.example.gavin.drivingschool.Services.ClientService;

import java.util.ArrayList;

/**
 * Created by gavin on 2017/06/27.
 */
public class ClientServiceImpl extends Service implements ClientService {
    final private ClientRepository repository;

    private final IBinder localBinder = new ClientServiceLocalBinder();

    private static ClientServiceImpl service = null;

    public static ClientServiceImpl getInstance()
    {
        if(service == null)
            service = new ClientServiceImpl();
        return service;
    }

    public ClientServiceImpl()
    {
        repository = new ClientRepositoryImpl();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class ClientServiceLocalBinder extends Binder {
        public ClientServiceImpl getService() {
            return ClientServiceImpl.this;
        }
    }


    @Override
    public Client addClient(Client animal) {
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
    public Client deleteClient(Client animal) {
        return repository.delete(animal);
    }

    @Override
    public ArrayList<Client> getAllClient() {
        try {
            ArrayList<Client> result = new ArrayList<>();
            if (result.addAll(repository.findAll()))
                return result;
            else
                return new ArrayList<Client>();
        } catch (Exception x) {
            x.printStackTrace();
        }
        return null;
    }

    @Override
    public void removeAllClient() {
        repository.deleteAll();
    }
    @Override
    public Client updateClient(Client animal) {
        return repository.update(animal);
    }

    @Override
    public Client getClient(Long Id) {
        return repository.findById(Id);
    }
}
