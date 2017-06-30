package com.example.gavin.drivingschool.Factory;

import com.example.gavin.drivingschool.Domain.Score;

/**
 * Created by gavin on 2017/06/27.
 */
public interface ScoreFactory {
    Score createScore(Long id,long ClientId, long UserId  ,int AlleyDockingRight,int AlleyDockingLeft,int ParallelParkingRight,int ParallelParkingLeft,int ThreePointTurn,int Driving,int LessonNumber);

}
