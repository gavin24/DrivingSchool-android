package com.example.gavin.drivingschool.Factory.Impl;

import com.example.gavin.drivingschool.Domain.Score;
import com.example.gavin.drivingschool.Factory.ScoreFactory;

/**
 * Created by gavin on 2017/06/27.
 */
public class ScoreFactoryImpl implements ScoreFactory {
    private static ScoreFactoryImpl factory = null;

    private ScoreFactoryImpl(){

    }

    public static ScoreFactoryImpl getInstance(){
        if (factory == null)
            factory = new ScoreFactoryImpl();

        return factory;
    }


    public Score createScore(Long id,long ClientId, long UserId  ,int AlleyDockingRight,int AlleyDockingLeft,int ParallelParkingRight,int ParallelParkingLeft,int ThreePointTurn,int Driving,int LessonNumber)
    {
        Score ticket =  new Score.Builder().id(id).ClientId(ClientId).UserId(UserId).AlleyDockingRight(AlleyDockingRight).AlleyDockingLeft(AlleyDockingLeft).ParallelParkingRight(ParallelParkingRight).ParallelParkingLeft(ParallelParkingLeft).ThreePointTurn(ThreePointTurn).Driving(Driving).LessonNumber(LessonNumber)
                .build();

        return ticket;
    }
}
