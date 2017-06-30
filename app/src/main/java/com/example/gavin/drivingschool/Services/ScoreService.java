package com.example.gavin.drivingschool.Services;

import com.example.gavin.drivingschool.Domain.Score;

import java.util.ArrayList;

/**
 * Created by gavin on 2017/06/27.
 */
public interface ScoreService {
    Score addScore(Score animal);
    Score updateScore( Score animal);
    Score deleteScore( Score animal);
    Score getScore(Long d);
    ArrayList<Score> getAllScore( );
    void removeAllScore();
}
