package com.pun.poc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "gym")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gym {
    @Id
    private int id;
    private String name;
    private String code;
    private int branch;

    public Gym(String name, String code, int branch) {
        this.name = name;
        this.code = code;
        this.branch = branch;
    }
}
