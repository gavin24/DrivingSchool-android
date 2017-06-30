package com.example.gavin.drivingschool.Factory.Impl;

import com.example.gavin.drivingschool.Domain.Car;
import com.example.gavin.drivingschool.Factory.CarFactory;

/**
 * Created by gavin on 2017/06/27.
 */
public class CarFactoryImpl implements CarFactory {
    private static CarFactoryImpl factory = null;

    private CarFactoryImpl(){

    }

    public static CarFactoryImpl getInstance(){
        if (factory == null)
            factory = new CarFactoryImpl();

        return factory;
    }


    public Car createCar(Long id,long UserId, String Type ,String License ,int Year ,int Mileage)
    {
        Car ticket =  new Car.Builder().id(id).UserId(UserId).Type(Type).License(License).Year(Year).Mileage(Mileage)
                .build();

        return ticket;
    }
}
