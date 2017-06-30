package com.example.gavin.drivingschool.Services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.gavin.drivingschool.Domain.Image;
import com.example.gavin.drivingschool.Domain.UserImage;
import com.example.gavin.drivingschool.Repositories.ImageRepository;
import com.example.gavin.drivingschool.Repositories.Impl.ImageRepositoryImpl;
import com.example.gavin.drivingschool.Repositories.Impl.UserImageRepositoryImpl;
import com.example.gavin.drivingschool.Repositories.UserImageRepository;
import com.example.gavin.drivingschool.Services.ImageService;
import com.example.gavin.drivingschool.Services.UserImageService;

import java.util.ArrayList;

/**
 * Created by gavin.ackerman on 2017-05-24.
 */

public class UserImageServiceImpl extends Service implements UserImageService {
    final private UserImageRepository repository;

    private final IBinder localBinder = new UserImageServiceLocalBinder();

    private static UserImageServiceImpl service = null;

    public static UserImageServiceImpl getInstance()
    {
        if(service == null)
            service = new UserImageServiceImpl();
        return service;
    }

    public UserImageServiceImpl()
    {
        repository = new UserImageRepositoryImpl();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class UserImageServiceLocalBinder extends Binder {
        public UserImageServiceImpl getService() {
            return UserImageServiceImpl.this;
        }
    }


    @Override
    public UserImage addImage(UserImage animal) {
        //     try{
        return repository.save(animal);
        //      }
        //      catch(Exception x)

        //  {
        //      x.printStackTrace();
        //  }
        //  return null ;
    }
    @Override
    public UserImage deleteImage(UserImage animal) {
        return repository.delete(animal);
    }

    @Override
    public ArrayList<UserImage> getAllImages() {
        try {
            ArrayList<UserImage> result = new ArrayList<>();
            if (result.addAll(repository.findAll()))
                return result;
            else
                return new ArrayList<UserImage>();
        } catch (Exception x) {
            x.printStackTrace();
        }
        return null;
    }

    @Override
    public void removeAllImages() {
        repository.deleteAll();
    }
    @Override
    public UserImage updateImage(UserImage animal) {
        return repository.update(animal);
    }

    @Override
    public UserImage getImage(Long Id) {
        return repository.findById(Id);
    }
    @Override
    public UserImage getImageByUserId(Long Id) {
        return repository.findByUserId(Id);
    }
}
