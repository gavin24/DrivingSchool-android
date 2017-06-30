package com.example.gavin.drivingschool.Services;

import com.example.gavin.drivingschool.Domain.Appointment;

import java.util.ArrayList;

/**
 * Created by gavin on 2017/06/27.
 */
public interface AppointmentService {
    Appointment addAppointment(Appointment animal);
    Appointment updateAppointment( Appointment animal);
    Appointment deleteAppointment( Appointment animal);
    Appointment getAppointment(Long d);
    ArrayList<Appointment> getAllAppointment( );
    void removeAllAppointment();
}
