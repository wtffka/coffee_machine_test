package com.example.test_task.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
public class CoffeeMachineOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean isOn;

    private String type;

    private LocalDateTime launchedAt;

    @Override
    public String toString() {
        if (null == this.type && this.isOn) return "Operation №" + this.id + ": Coffee machine is on";
        if (null == this.type) return "Operation №" + this.id + ": Coffee machine is off";
        return "Operation №" + this.id + ": " + this.type + " was brewed at " + this.launchedAt;
    }
}
