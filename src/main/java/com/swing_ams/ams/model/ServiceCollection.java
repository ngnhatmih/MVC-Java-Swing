/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swing_ams.ams.model;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ngnhatmih
 */
@XmlRootElement(name = "services")
@XmlAccessorType(XmlAccessType.FIELD)
public class ServiceCollection {
    private List<ApartmentService> apartments;

    public List<ApartmentService> getApartments() {
        return apartments;
    }

    public void setApartments(List<ApartmentService> apartments) {
        this.apartments = apartments;
    }
}
