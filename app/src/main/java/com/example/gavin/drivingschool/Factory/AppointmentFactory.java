package com.example.gavin.drivingschool.Factory;

import com.example.gavin.drivingschool.Domain.Address;
import com.example.gavin.drivingschool.Domain.Appointment;

import java.util.Date;

/**
 * Created by gavin on 2017/06/27.
 */
public interface AppointmentFactory {
   Appointment createAppointment(Long id,long ClientId, String AppointmentDate ,String StartTime ,String EndTime ,long AppointmentUserId ,String Notes);

}
