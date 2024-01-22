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
@XmlRootElement(name = "apartment")
@XmlAccessorType(XmlAccessType.FIELD)
public class Apartment implements Serializable {
    private long id;
    private String block;
    private int floor;
    private ApartmentService service;
    
    public Apartment() {
        id = 0;
        block = "";
        floor = 1;
        service = new ApartmentService();
    }
    
    public Apartment(int id, int floor, String block, ApartmentService service) {
        super();
        this.id = id;
        this.floor = floor;
        this.block = block;
        this.service = service;
    }
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public ApartmentService getService() {
        return service;
    }

    public void setService(ApartmentService service) {
        this.service = service;
    }
}
