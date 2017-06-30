package com.example.gavin.drivingschool.Services;

import com.example.gavin.drivingschool.Domain.Client;

import java.util.ArrayList;

/**
 * Created by gavin on 2017/06/27.
 */
public interface ClientService {
    Client addClient(Client animal);
    Client updateClient( Client animal);
    Client deleteClient( Client animal);
    Client getClient(Long d);
    ArrayList<Client> getAllClient( );
    void removeAllClient();
}
