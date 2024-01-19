/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.swing_ams.ams;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.fonts.roboto_mono.FlatRobotoMonoFont;
import com.swing_ams.ams.controller.LoginController;
import com.swing_ams.ams.view.LoginView;
import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author ngnhatmih
 */
public class App {
    public static void main(String[] args) {
        FlatRobotoFont.install();
        FlatRobotoMonoFont.install();
        FlatDarculaLaf.setPreferredFontFamily( FlatRobotoFont.FAMILY );
        FlatDarculaLaf.setPreferredMonospacedFontFamily( FlatRobotoMonoFont.FAMILY);
        FlatLaf.registerCustomDefaultsSource("com.swing_ams.ams.resources.themes");
        FlatDarculaLaf.setup();

        EventQueue.invokeLater(() -> {
            LoginView view = new LoginView();
            LoginController controller = new LoginController(view);
            // hiển thị màn hình login
            controller.showLoginView();
        });
    }
}