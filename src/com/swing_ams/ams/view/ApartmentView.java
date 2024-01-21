/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swing_ams.ams.view;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

public class ApartmentView extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private Map<String, JButton> buttons;
    private JTextField searchField;
    
    
    public ApartmentView() {
        initComponents();
    }
    
    private void initComponents()
    {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
                "focusWidth:0;" +
                "innerFocusWidth:0");
        }
        
        JPanel panel = new JPanel(new MigLayout("wrap, fill, insets 10 10 10 10", "[fill]", "[grow 0][fill]"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" +
                "arc:20;" +
                "[light]background:darken(@background,10%);" +
                "[dark]background:darken(@background,1%);" +
                "[light]border:0,0,0,0,shade(@background,5%);" +
                "[dark]border:0,0,0,0,tint(@background,5%)");
        
        JPanel toolBar = new JPanel(new MigLayout("insets 10 10 10 10", "[]push[][][]"));
        toolBar.putClientProperty(FlatClientProperties.STYLE, "" +
                "borderWidth:1");
        
        searchField.putClientProperty(FlatClientProperties.STYLE, "" + 
                "focusWidth:0;" +
                "innerFocusWidth:0");
        
        toolBar.add(searchField, "width 400");
        for (JButton button : buttons.values())
            toolBar.add(button, "gap 2"); 
        
        panel.add(toolBar, "gapy 10");
        
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Floor");
        model.addColumn("Block");
        
        
        JTable table = new JTable(model) {
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
