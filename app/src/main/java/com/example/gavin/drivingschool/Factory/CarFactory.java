package com.example.gavin.drivingschool.Factory;

import com.example.gavin.drivingschool.Domain.Appointment;
import com.example.gavin.drivingschool.Domain.Car;

import java.util.Date;

/**
 * Created by gavin on 2017/06/27.
 */
public interface CarFactory {
    Car createCar(Long id,long UserId, String Type ,String License ,int Year ,int Mileage);


}
