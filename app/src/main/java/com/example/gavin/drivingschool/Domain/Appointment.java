package com.example.gavin.drivingschool.Domain;

import java.util.Date;

/**
 * Created by gavin on 2017/06/27.
 */
public class Appointment {
    public long id ;
    public long ClientId ;
    public String AppointmentDate ;
    public String StartTime ;
    public String EndTime ;
    public long AppointmentUserId ;
    public String Notes ;
//score
    private Appointment() {
    }


    public String getAppointmentDate(){return AppointmentDate;}
    public String getStartTime(){return StartTime;}
    public String getEndTime() {
        return EndTime;
    }
    public long getId() {
        return id;
    }
    public long getAppointmentUserId() {
        return AppointmentUserId;
    }
    public long getClientId() {
        return ClientId;
    }
    public String getNotes(){return Notes;}


    public Appointment(Builder builder){
        this.id=builder.id;
        this.ClientId=builder.ClientId;
        this.AppointmentDate=builder.AppointmentDate;
        this.StartTime=builder.StartTime;
        this.EndTime=builder.EndTime;
        this.AppointmentUserId=builder.AppointmentUserId;
        this.Notes=builder.Notes;
    }

    public static class Builder {
        public long id ;
        public long ClientId ;
        public String AppointmentDate ;
        public String StartTime ;
        public String EndTime ;
        public long AppointmentUserId ;
        public String Notes ;

        public Builder ClientId(long value) {
            this.ClientId = value;
            return this;
        }


        public Builder id(long value) {
            this.id = value;
            return this;
        }

        public Builder AppointmentDate(String value) {
            this.AppointmentDate = value;
            return this;
        }

        public Builder StartTime(String value) {
            this.StartTime = value;
            return this;
        }


        public Builder EndTime(String value) {
            this.EndTime = value;
            return this;
        }

        public Builder AppointmentUserId(long value) {
            this.AppointmentUserId = value;
            return this;

        }


        public Builder Notes(String value) {
            this.Notes = value;
            return this;

        }

        public Builder copy(Appointment value) {
            this.id = value.id;
            this.ClientId = value.ClientId;
            this.AppointmentDate = value.AppointmentDate;
            this.StartTime = value.StartTime;
            this.EndTime = value.EndTime;
            this.AppointmentUserId = value.AppointmentUserId;
            this.Notes = value.Notes;
            return this;
        }

        public Appointment build() {
            return new Appointment(this);
        }
    }
}
