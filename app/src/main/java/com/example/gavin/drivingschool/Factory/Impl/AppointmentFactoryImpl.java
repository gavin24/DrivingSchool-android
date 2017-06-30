package com.example.gavin.drivingschool.Factory.Impl;

import com.example.gavin.drivingschool.Domain.Appointment;
import com.example.gavin.drivingschool.Factory.AppointmentFactory;

import java.util.Date;

/**
 * Created by gavin on 2017/06/27.
 */
public class AppointmentFactoryImpl implements AppointmentFactory {
    private static AppointmentFactoryImpl factory = null;

    private AppointmentFactoryImpl(){

    }

    public static AppointmentFactoryImpl getInstance(){
        if (factory == null)
            factory = new AppointmentFactoryImpl();

        return factory;
    }


    public Appointment createAppointment(Long id,long ClientId, String AppointmentDate ,String StartTime ,String EndTime ,long AppointmentUserId ,String Notes)
    {
        Appointment ticket =  new Appointment.Builder().id(id).ClientId(ClientId).AppointmentDate(AppointmentDate).StartTime(StartTime).EndTime(EndTime).AppointmentUserId(AppointmentUserId).Notes(Notes)
                .build();

        return ticket;
    }
}
