package com.swing_ams.ams;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.fonts.roboto_mono.FlatRobotoMonoFont;
import com.swing_ams.ams.controller.LoginController;
import com.swing_ams.ams.view.LoginView;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.UIManager;

public class App {
    public static void setup()
    {
        FlatRobotoFont.install();
        FlatRobotoMonoFont.install();
        FlatLaf.registerCustomDefaultsSource("themes");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        UIManager.put("Panel.font", FlatRobotoFont.FAMILY);
        FlatDarculaLaf.setup();
    }
    
    public static void run()
    {
        EventQueue.invokeLater(() -> {
            LoginView view = new LoginView();
            LoginController controller = new LoginController(view);
            // hiển thị màn hình login
            controller.showLoginView();
        });  
    }
    
    public static void main(String[] args) {
        App.setup();
        App.run();       
    }
}