package com.example.gavin.drivingschool.Services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.gavin.drivingschool.Config.Util.App;
import com.example.gavin.drivingschool.Domain.Image;
import com.example.gavin.drivingschool.Repositories.Impl.ImageRepositoryImpl;
import com.example.gavin.drivingschool.Repositories.ImageRepository;
import com.example.gavin.drivingschool.Services.ImageService;
import com.example.gavin.drivingschool.Services.ImageService;

import java.util.ArrayList;

/**
 * Created by gavin.ackerman on 2016-06-22.
 */
public class ImageServiceImpl extends Service implements ImageService {
    final private ImageRepository repository;

    private final IBinder localBinder = new ImageServiceLocalBinder();

    private static ImageServiceImpl service = null;

    public static ImageServiceImpl getInstance()
    {
        if(service == null)
            service = new ImageServiceImpl();
        return service;
    }

    public ImageServiceImpl()
    {
        repository = new ImageRepositoryImpl();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class ImageServiceLocalBinder extends Binder {
        public ImageServiceImpl getService() {
            return ImageServiceImpl.this;
        }
    }


    @Override
    public Image addImage(Image animal) {
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
    public Image deleteImage(Image animal) {
        return repository.delete(animal);
    }

    @Override
    public ArrayList<Image> getAllImages() {
        try {
            ArrayList<Image> result = new ArrayList<>();
            if (result.addAll(repository.findAll()))
                return result;
            else
                return new ArrayList<Image>();
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
    public Image updateImage(Image animal) {
        return repository.update(animal);
    }

    @Override
    public Image getImage(Long Id) {
        return repository.findById(Id);
    }


}
