package com.example.gavin.drivingschool.Services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.gavin.drivingschool.Config.Util.App;
import com.example.gavin.drivingschool.Domain.User;
import com.example.gavin.drivingschool.Repositories.Impl.UserRepositoryImpl;
import com.example.gavin.drivingschool.Repositories.UserRepository;
import com.example.gavin.drivingschool.Services.UserService;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by gavin.ackerman on 2016-06-21.
 */
public class UserServiceImpl extends Service implements UserService {
    final private UserRepository repository;

    private final IBinder localBinder = new UserServiceLocalBinder();

    private static UserServiceImpl service = null;

    public static UserServiceImpl getInstance()
    {
        if(service == null)
            service = new UserServiceImpl();
        return service;
    }

    public UserServiceImpl()
    {
        repository = new UserRepositoryImpl();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class UserServiceLocalBinder extends Binder {
        public UserServiceImpl getService() {
            return UserServiceImpl.this;
        }
    }
    public boolean isAuthentic(String username,String password)
    {
        ArrayList<User> users;
        users = repository.findAll();

        for (User user : users)

        {
            if (user.getEmail().equalsIgnoreCase(username) && user.getpassword().equals(password)) {
                return true;
            }

        }
        return false;
    }
    @Override
    public User addUser(User animal) {
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
    public User deleteUser(User animal) {
        return repository.delete(animal);
    }

    @Override
    public ArrayList<User> getAllUsers() {
        try {
            ArrayList<User> result = new ArrayList<>();
            if (result.addAll(repository.findAll()))
                return result;
            else
                return new ArrayList<User>();
        } catch (Exception x) {
            x.printStackTrace();
        }
        return null;
    }
    @Override
    public User getUserByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public void removeAllUsers() {
        repository.deleteAll();
    }
    @Override
    public User updateUser(User animal) {
        return repository.update(animal);
    }

    @Override
    public User getUser(Long Id) {
        return repository.findById(Id);
    }
}
