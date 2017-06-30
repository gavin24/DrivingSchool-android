package com.example.gavin.drivingschool.Services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.gavin.drivingschool.Domain.Address;
import com.example.gavin.drivingschool.Domain.Appointment;
import com.example.gavin.drivingschool.Repositories.AddressRepository;
import com.example.gavin.drivingschool.Repositories.AppointmentRepository;
import com.example.gavin.drivingschool.Repositories.Impl.AddressRepositoryImpl;
import com.example.gavin.drivingschool.Repositories.Impl.AppointementRepositoryImpl;
import com.example.gavin.drivingschool.Services.AddressService;
import com.example.gavin.drivingschool.Services.AppointmentService;

import java.util.ArrayList;

/**
 * Created by gavin on 2017/06/27.
 */
public class AppointmentServiceImpl extends Service implements AppointmentService {
    final private AppointmentRepository repository;

    private final IBinder localBinder = new AppointmentServiceLocalBinder();

    private static AppointmentServiceImpl service = null;

    public static AppointmentServiceImpl getInstance()
    {
        if(service == null)
            service = new AppointmentServiceImpl();
        return service;
    }

    public AppointmentServiceImpl()
    {
        repository = new AppointementRepositoryImpl();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class AppointmentServiceLocalBinder extends Binder {
        public AppointmentServiceImpl getService() {
            return AppointmentServiceImpl.this;
        }
    }


    @Override
    public Appointment addAppointment(Appointment animal) {
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
    public Appointment deleteAppointment(Appointment animal) {
        return repository.delete(animal);
    }

    @Override
    public ArrayList<Appointment> getAllAppointment() {
        try {
            ArrayList<Appointment> result = new ArrayList<>();
            if (result.addAll(repository.findAll()))
                return result;
            else
                return new ArrayList<Appointment>();
        } catch (Exception x) {
            x.printStackTrace();
        }
        return null;
    }

    @Override
    public void removeAllAppointment() {
        repository.deleteAll();
    }
    @Override
    public Appointment updateAppointment(Appointment animal) {
        return repository.update(animal);
    }

    @Override
    public Appointment getAppointment(Long Id) {
        return repository.findById(Id);
    }

}
