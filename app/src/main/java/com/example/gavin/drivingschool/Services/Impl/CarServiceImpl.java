package com.example.gavin.drivingschool.Services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.gavin.drivingschool.Domain.Car;
import com.example.gavin.drivingschool.Repositories.CarRepository;
import com.example.gavin.drivingschool.Repositories.Impl.AppointementRepositoryImpl;
import com.example.gavin.drivingschool.Repositories.Impl.CarRepositoryImpl;
import com.example.gavin.drivingschool.Services.AppointmentService;
import com.example.gavin.drivingschool.Services.CarService;

import java.util.ArrayList;

/**
 * Created by gavin on 2017/06/27.
 */
public class CarServiceImpl extends Service implements CarService {
    final private CarRepository repository;

    private final IBinder localBinder = new CarServiceLocalBinder();

    private static CarServiceImpl service = null;

    public static CarServiceImpl getInstance()
    {
        if(service == null)
            service = new CarServiceImpl();
        return service;
    }

    public CarServiceImpl()
    {
        repository = new CarRepositoryImpl();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class CarServiceLocalBinder extends Binder {
        public CarServiceImpl getService() {
            return CarServiceImpl.this;
        }
    }


    @Override
    public Car addCar(Car animal) {
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
    public Car deleteCar(Car animal) {
        return repository.delete(animal);
    }

    @Override
    public ArrayList<Car> getAllCar() {
        try {
            ArrayList<Car> result = new ArrayList<>();
            if (result.addAll(repository.findAll()))
                return result;
            else
                return new ArrayList<Car>();
        } catch (Exception x) {
            x.printStackTrace();
        }
        return null;
    }

    @Override
    public void removeAllCar() {
        repository.deleteAll();
    }
    @Override
    public Car updateCar(Car animal) {
        return repository.update(animal);
    }

    @Override
    public Car getCar(Long Id) {
        return repository.findById(Id);
    }
}
