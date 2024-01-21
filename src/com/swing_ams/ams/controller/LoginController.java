package com.swing_ams.ams.controller;

import com.swing_ams.ams.dao.UserDao;
import com.swing_ams.ams.model.User;
import com.swing_ams.ams.view.ApartmentView;
import com.swing_ams.ams.view.LoginView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginController {
    private final UserDao userDao;
    private final LoginView loginView;

    public LoginController(LoginView view) {
        this.loginView = view;
        this.userDao = new UserDao();
        view.addLoginListener(new LoginListener());
    }
    
    public void showLoginView() {
        loginView.setVisible(true);
    }
    
    /**
     * Lớp LoginListener 
     * chứa cài đặt cho sự kiện click button "Login"
     */
    class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            User user = loginView.getUser();
            if (userDao.checkUser(user)) {
                ApartmentView apartmentView = new ApartmentView();
                ApartmentController apartmentController = new ApartmentController(apartmentView);
                apartmentController.showApartmentView();
                
                loginView.setVisible(false);
            } else {
                loginView.showMessage("Incorrect username or password.");
            }
        }
    }
}