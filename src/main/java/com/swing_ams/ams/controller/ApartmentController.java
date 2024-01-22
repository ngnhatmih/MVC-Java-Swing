/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.swing_ams.ams.controller;

import main.java.com.swing_ams.ams.view.ApartmentView;

/**
 *
 * @author ngnhatmih
 */
public class ApartmentController {
    private final ApartmentView apartmentView;

    public ApartmentController(ApartmentView apartmentView) {
        this.apartmentView = apartmentView;
    } 
    
    public void showApartmentView()
    {
        apartmentView.setVisible(true);
    }
}
