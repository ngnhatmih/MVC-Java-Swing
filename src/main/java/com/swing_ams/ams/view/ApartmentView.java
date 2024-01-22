/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swing_ams.ams.view;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

public class ApartmentView extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private Map<String, JButton> buttons;
    private JTextField searchField;
    private JLabel idLabel;
    private JLabel floorLabel;
    private JLabel blockLabel;
    private JLabel title;
    private JLabel description;
    private JTextField idField;
    private JTextField floorField;
    private JTextField blockField;
    
    public ApartmentView() {
        initComponents();
    }
    
    private void initComponents()
    {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        title = new JLabel("AMS");
        title.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold +15");
        
        description = new JLabel("Enter apartment info here");
        description.putClientProperty(FlatClientProperties.STYLE, "" +
                "[light]foreground:lighten(@foreground,30%);" +
                "[dark]foreground:darken(@foreground,30%)");
        
        idLabel = new JLabel("Apartment ID: ");
        floorLabel = new JLabel("Floor: ");
        blockLabel = new JLabel("Block: ");
        
        idField = new JTextField();
        idField.setEditable(false);
        idField.putClientProperty(FlatClientProperties.STYLE, "focusWidth:0;");
        idField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Default ID");
        
        floorField = new JTextField();
        floorField.putClientProperty(FlatClientProperties.STYLE, "focusWidth:0;");
        floorField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter foor");
        
        blockField = new JTextField();
        blockField.putClientProperty(FlatClientProperties.STYLE, "focusWidth:0;");
        blockField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter block");
        
        JPanel inputPanel = new JPanel(new MigLayout("wrap, fillx, insets 20 35 20 35", "fill"));
        inputPanel.putClientProperty(FlatClientProperties.STYLE, "" +
                "[dark]background:darken(@background, 5%);" +
                "[light]background:darken(@background,10%);" +
                "[dark]foreground:darken(@foreground, 1%)");
        
        inputPanel.add(title);
        inputPanel.add(description);
        inputPanel.add(new JSeparator());
        inputPanel.add(idLabel, "gapy 8");
        inputPanel.add(idField);
        inputPanel.add(floorLabel, "gapy 8");
        inputPanel.add(floorField);
        inputPanel.add(blockLabel, "gapy 8");
        inputPanel.add(blockField);
        
        searchField = new JTextField();
        searchField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search");
        
        buttons = new HashMap<>();
        buttons.put("Add", new JButton());
        buttons.put("Update", new JButton());
        buttons.put("Delete", new JButton());   
        
        for (String buttonText : buttons.keySet())
        {
            var button = buttons.get(buttonText);
            button.setText(buttonText);
            button.putClientProperty(FlatClientProperties.STYLE, "" +
                "[light]background:darken(@background,10%);" +
                "[dark]background:lighten(@background,10%);" +
                "borderWidth:1;" +
                "focusWidth:0;");
        }
        
        JPanel panel = new JPanel(new MigLayout("fill, insets 10 10 10 10", "[fill]", "[grow 0][fill]"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" +
                "[light]background:darken(@background,10%);" +
                "[dark]background:darken(@background,1%);" +
                "[light]border:0,0,0,0,shade(@background,5%);" +
                "[dark]border:0,0,0,0,tint(@background,5%)");
        
        panel.add(inputPanel, "width 300, dock west");
        
        JPanel toolBar = new JPanel(new MigLayout("insets 10 10 10 10", "[]push[][][]"));
        toolBar.putClientProperty(FlatClientProperties.STYLE, "" +
                "borderWidth:1");
        
        searchField.putClientProperty(FlatClientProperties.STYLE, "" + 
                "focusWidth:0;" +
                "innerFocusWidth:0");
        
        searchField.putClientProperty(FlatClientProperties.TEXT_FIELD_TRAILING_ICON, new FlatSVGIcon("icons/search.svg"));
        
        toolBar.add(searchField, "width 200");
        for (JButton button : buttons.values())
            toolBar.add(button, "gap 2"); 
        
        panel.add(toolBar, "wrap, gapy 10");
        
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Floor");
        model.addColumn("Block");
        
        
        JTable table = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {                
                return false;               
            };
        };
        
        table.putClientProperty(FlatClientProperties.STYLE, "" +
                "rowHeight:25;" +
                "showHorizontalLines:true;" +
                "showVerticalLines:true;" +
                "[dark]selectionBackground:darken(@background, 3%);" + 
                "[light]selectionBackground:lighten(@background, 3%);" +
                "focusWidth:0;" +
                "innerFocusWidth:0;");
        
        table.getTableHeader().putClientProperty(FlatClientProperties.STYLE, "" +
                "height:25;" +
                "hoverBackground:null;" +
                "[dark]background:darken(@background,2%);" +
                "[light]background:lighten(@background,3%)");
        
        table.getTableHeader().setReorderingAllowed(false);
        
        
        JScrollPane scroll = new JScrollPane(table);
        scroll.putClientProperty(FlatClientProperties.STYLE, "" +
                "focusWidth:0;" +
                "innerFocusWidth:0");
        
        scroll.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, "" +
                "showButtons:true;" +
                "width:12;" +
                "thumbArc:999;" +
                "thumbInsets:2,2,2,2;" +
                "track:@background;" +
                "trackArc:999;");
        
        panel.add(scroll);
        
        this.add(panel);
        this.pack();
        
        this.setTitle("AMS");
        this.setSize(900,600);
        this.setLocationRelativeTo(null);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
