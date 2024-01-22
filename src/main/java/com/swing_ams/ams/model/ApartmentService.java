/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swing_ams.ams.model;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ngnhatmih
 */

@XmlRootElement(name = "service")
@XmlAccessorType(XmlAccessType.FIELD)
public class ApartmentService implements Serializable {
    private int management;
    private int electricity;
    private int water;
    private int elevator;
    
    public ApartmentService() {}
    
    public ApartmentService(int management, int electricity, int water, int elevator) {
        super();
        this.management = management;
        this.electricity = electricity;
        this.water = water;
        this.elevator = elevator;
    }

    public int getManagement() {
        return management;
    }

    public void setManagement(int management) {
        this.management = management;
    }

    public int getElectricity() {
        return electricity;
    }

    public void setElectricity(int electricity) {
        this.electricity = electricity;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public int getElevator() {
        return elevator;
    }

    public void setElevator(int elevator) {
        this.elevator = elevator;
    }
}
