package com.example.gavin.drivingschool.Domain;

import java.util.Date;

/**
 * Created by gavin on 2017/06/27.
 */
public class Car {
    public long id ;
    public long UserId ;
    public String Type;
    public String License ;
    public int Year ;
    public int Mileage ;
    private Car() {
    }


    public int getMileage(){return Mileage;}
    public String getType(){return Type;}
    public String getLicense() {
        return License;
    }
    public long getId() {
        return id;
    }
    public long getUserId() {
        return UserId;
    }
    public int getYear() {
        return Year;
    }

    public Car(Builder builder){
        this.id=builder.id;
        this.UserId=builder.UserId;
        this.Type=builder.Type;
        this.License=builder.License;
        this.Year=builder.Year;
        this.Mileage=builder.Mileage;

    }

    public static class Builder {
        public long id ;
        public long UserId ;
        public String Type;
        public String License ;
        public int Year ;
        public int Mileage ;

        public Builder UserId(long value) {
            this.UserId = value;
            return this;
        }


        public Builder id(long value) {
            this.id = value;
            return this;
        }

        public Builder Type(String value) {
            this.Type = value;
            return this;
        }

        public Builder License(String value) {
            this.License = value;
            return this;
        }


        public Builder Year(int value) {
            this.Year = value;
            return this;
        }

        public Builder Mileage(int value) {
            this.Mileage = value;
            return this;

        }



        public Builder copy(Car value) {
            this.id = value.id;
            this.UserId = value.UserId;
            this.Type = value.Type;
            this.License = value.License;
            this.Year = value.Year;
            this.Mileage = value.Mileage;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }
}
