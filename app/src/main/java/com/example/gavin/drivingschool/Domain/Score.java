package com.example.gavin.drivingschool.Domain;

import java.util.Date;

/**
 * Created by gavin on 2017/06/27.
 */
public class Score {
    public long id ;
    public long ClientId ;
    public long UserId ;
    public int AlleyDockingRight ;
    public int AlleyDockingLeft;
    public int ParallelParkingRight ;
    public int ParallelParkingLeft ;
    public int ThreePointTurn;
    public int Driving ;
    public int LessonNumber ;
//appointment id
    private Score() {
    }


    public int getAlleyDockingRight(){return AlleyDockingRight;}
    public int getAlleyDockingLeft(){return AlleyDockingLeft;}
    public int getParallelParkingRight(){return ParallelParkingRight;}
    public int getParallelParkingLeft(){return ParallelParkingLeft;}
    public int getThreePointTurn(){return ThreePointTurn;}
    public int getDriving(){return Driving;}
    public int getLessonNumber() {
        return LessonNumber;
    }
    public long getId() {
        return id;
    }
    public long getUserId() {
        return UserId;
    }
    public long getClientId() {
        return ClientId;
    }


    public Score(Builder builder){
        this.id=builder.id;
        this.ClientId=builder.ClientId;
        this.UserId=builder.UserId;
        this.AlleyDockingRight=builder.AlleyDockingRight;
        this.AlleyDockingLeft=builder.AlleyDockingLeft;
        this.ParallelParkingRight=builder.ParallelParkingRight;
        this.ParallelParkingLeft=builder.ParallelParkingLeft;
        this.ThreePointTurn=builder.ThreePointTurn;
        this.Driving=builder.Driving;
        this.LessonNumber=builder.LessonNumber;
    }

    public static class Builder {
        public long id ;
        public long ClientId ;
        public long UserId ;
        public int AlleyDockingRight ;
        public int AlleyDockingLeft;
        public int ParallelParkingRight ;
        public int ParallelParkingLeft ;
        public int ThreePointTurn;
        public int Driving ;
        public int LessonNumber ;

        public Builder ClientId(long value) {
            this.ClientId = value;
            return this;
        }


        public Builder id(long value) {
            this.id = value;
            return this;
        }

        public Builder UserId(long value) {
            this.UserId = value;
            return this;
        }

        public Builder AlleyDockingRight(int value) {
            this.AlleyDockingRight = value;
            return this;
        }


        public Builder AlleyDockingLeft(int value) {
            this.AlleyDockingLeft = value;
            return this;
        }

        public Builder ParallelParkingRight(int value) {
            this.ParallelParkingRight = value;
            return this;

        }
        public Builder ParallelParkingLeft(int value) {
            this.ParallelParkingLeft = value;
            return this;

        }
        public Builder ThreePointTurn(int value) {
            this.ThreePointTurn = value;
            return this;

        }
        public Builder Driving(int value) {
            this.Driving = value;
            return this;

        }
        public Builder LessonNumber(int value) {
            this.LessonNumber = value;
            return this;

        }

        public Builder copy(Score value) {
            this.id = value.id;
            this.ClientId = value.ClientId;
            this.UserId = value.UserId;
            this.AlleyDockingRight = value.AlleyDockingRight;
            this.AlleyDockingLeft = value.AlleyDockingLeft;
            this.ParallelParkingRight = value.ParallelParkingRight;
            this.ParallelParkingLeft = value.ParallelParkingLeft;
            this.ThreePointTurn = value.ThreePointTurn;
            this.Driving = value.Driving;
            this.LessonNumber = value.LessonNumber;
            return this;
        }

        public Score build() {
            return new Score(this);
        }
    }
}
