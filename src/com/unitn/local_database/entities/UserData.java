package com.unitn.local_database.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by erinda on 1/26/16.
 */
@Entity
@Getter
@Setter
public class UserData {
    @Id
    private int idTelegram;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    private long birthTimestamp;

    private int weight;
    private float  height;

    private long projectId;
}
