package com.swing_ams.ams.view;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.swing_ams.ams.model.User;
import net.miginfocom.swing.MigLayout;

public class LoginView extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JLabel title;
    private JLabel description;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JTextField userNameField;
    private JButton loginBtn;

    public LoginView() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        title = new JLabel("Welcome!");
        description = new JLabel("Please log in to access your account");
        usernameLabel = new JLabel("Username");
        passwordLabel = new JLabel("Password");
        userNameField = new JTextField();
        passwordField = new JPasswordField();
        loginBtn = new JButton();

        loginBtn.setText("LOGIN");
        loginBtn.addActionListener(this);
        
        JPanel panel = new JPanel(new MigLayout("wrap, fillx,insets 15 45 30 45", "fill,300:280"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" +
                "arc:20;" +
                "[light]background:darken(@background,10%);" +
                "[dark]background:lighten(@background,1%)");
        passwordField.putClientProperty(FlatClientProperties.STYLE, "" +
                "showRevealButton:true");
        loginBtn.putClientProperty(FlatClientProperties.STYLE, "" +
                "[light]background:darken(@background,10%);" +
                "[dark]background:lighten(@background,10%);" +
                "borderWidth:0;" +
                "focusWidth:0;" +
                "innerFocusWidth:0");
        description.putClientProperty(FlatClientProperties.STYLE, "" +
                "[light]foreground:lighten(@foreground,30%);" +
                "[dark]foreground:darken(@foreground,30%)");
        title.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold +15");
        
        userNameField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter your username");
        passwordField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter your password");
        // tạo đối tượng panel để chứa các thành phần của màn hình login
        panel.add(title);
        panel.add(description);
        panel.add(usernameLabel, "gapy 8");
        panel.add(userNameField);
        panel.add(passwordLabel, "gapy 8");
        panel.add(passwordField);
        panel.add(loginBtn, "gapy 10");
        
        // add panel tới JFrame
        this.add(panel);
        this.pack();
        // cài đặt các thuộc tính cho JFrame
        this.setTitle("Login");
        this.setSize(400, 300);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public User getUser() {
        return new User(userNameField.getText(), 
                String.copyValueOf(passwordField.getPassword()));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
    
    public void addLoginListener(ActionListener listener) {
        loginBtn.addActionListener(listener);
    }
}