package com.example.gavin.drivingschool.Services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.gavin.drivingschool.Domain.Score;
import com.example.gavin.drivingschool.Repositories.ScoreRepository;
import com.example.gavin.drivingschool.Repositories.Impl.ScoreRepositoryImpl;
import com.example.gavin.drivingschool.Services.ScoreService;

import java.util.ArrayList;

/**
 * Created by gavin on 2017/06/27.
 */
public class ScoreServiceImpl  extends Service implements ScoreService {
    final private ScoreRepository repository;

    private final IBinder localBinder = new ScoreServiceLocalBinder();

    private static ScoreServiceImpl service = null;

    public static ScoreServiceImpl getInstance()
    {
        if(service == null)
            service = new ScoreServiceImpl();
        return service;
    }

    public ScoreServiceImpl()
    {
        repository = new ScoreRepositoryImpl();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class ScoreServiceLocalBinder extends Binder {
        public ScoreServiceImpl getService() {
            return ScoreServiceImpl.this;
        }
    }


    @Override
    public Score addScore(Score animal) {
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
    public Score deleteScore(Score animal) {
        return repository.delete(animal);
    }

    @Override
    public ArrayList<Score> getAllScore() {
        try {
            ArrayList<Score> result = new ArrayList<>();
            if (result.addAll(repository.findAll()))
                return result;
            else
                return new ArrayList<Score>();
        } catch (Exception x) {
            x.printStackTrace();
        }
        return null;
    }

    @Override
    public void removeAllScore() {
        repository.deleteAll();
    }
    @Override
    public Score updateScore(Score animal) {
        return repository.update(animal);
    }

    @Override
    public Score getScore(Long Id) {
        return repository.findById(Id);
    }
}
