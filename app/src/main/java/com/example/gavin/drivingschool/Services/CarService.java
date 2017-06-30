package com.example.gavin.drivingschool.Services;

import com.example.gavin.drivingschool.Domain.Car;

import java.util.ArrayList;

/**
 * Created by gavin on 2017/06/27.
 */
public interface CarService {
    Car addCar(Car animal);
    Car updateCar( Car animal);
    Car deleteCar( Car animal);
    Car getCar(Long d);
    ArrayList<Car> getAllCar( );
    void removeAllCar();
}
