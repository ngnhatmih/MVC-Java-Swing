/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.swing_ams.ams;

import com.swing_ams.ams.controller.LoginController;
import com.swing_ams.ams.view.LoginView;
import java.awt.EventQueue;

/**
 *
 * @author ngnhatmih
 */
public class App {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            LoginView view = new LoginView();
            LoginController controller = new LoginController(view);
            // hiển thị màn hình login
            controller.showLoginView();
        });
    }
}
