package com.idp.api;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Created by Edi on 4/19/2015.
 */

@Entity
public class Receiver {
    @Id
    Long id;
    String startCity;
    String Destination;
    String date;

    public Receiver(){}

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    public String getStartCity() {
        return startCity;
    }

    public void setDestination(String destination) {
        Destination = destination;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }
}
