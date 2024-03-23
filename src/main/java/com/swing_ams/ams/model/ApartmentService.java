package com.swing_ams.ams.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "service")
@XmlAccessorType(XmlAccessType.FIELD)
public class ApartmentService implements Serializable {

    private int management;
    private int electricity;
    private int water;
    private int elevator;
    private boolean paid;
    private PaidDate date;
    
    @XmlRootElement(name = "paid-date")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class PaidDate {

        private Quarter quarter;
        private int year;

        public PaidDate() {
            quarter = Quarter.NONE;
            year = 0;
        }

        public PaidDate(Quarter quarter, int year) {
            this.quarter = quarter;
            this.year = year;
        }

        public Quarter getQuarter() {
            return quarter;
        }

        public void setQuarter(Quarter quarter) {
            this.quarter = quarter;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }
        
        public String toString() {
            return quarter.value + "/" + String.valueOf(year);
        }
    }

    public ApartmentService() {
        paid = false;
        management = electricity = water = elevator = 0;
        date = new PaidDate();

    }

    public ApartmentService(boolean paid, PaidDate data, int management, int electricity, int water, int elevator) {
        super();
        this.paid = paid;
        this.date = date;
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

    public int getTotal() {
        return management + electricity + elevator + water;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public PaidDate getDate() {
        return date;
    }

    public void setDate(PaidDate date) {
        this.date = date;
    }

    public enum Quarter {
        NONE, 
        I("I"), 
        II("II"), 
        III("III"), 
        IV("IV");
        
        public final String value;
        private Quarter() { value = ""; }
        private Quarter(String value) {
            this.value = value;
        }
    }
}
