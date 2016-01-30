package com.unitn.local_database.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by erinda on 1/25/16.
 */
@Entity
@Getter
@Setter
public class MeasureData {

    @Id @GeneratedValue()
    private int id;

    @Column(nullable=false)
    private int idTelegram;

    @Column(nullable=false)
    private int steps;

    @Column(nullable=false)
    private long timestamp;

    public void MeasureData(){
        timestamp = System.currentTimeMillis();
    }



}
