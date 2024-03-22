/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swing_ams.ams.view;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.swing_ams.ams.model.Apartment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.miginfocom.swing.MigLayout;

public class ApartmentView extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private Map<String, JButton> buttons;
    private JTextField searchField;
    private JLabel idLabel;
    private JLabel floorLabel;
    private JLabel blockLabel;
    private JLabel managementLabel;
    private JLabel electricityLabel;
    private JLabel waterLabel;
    private JLabel elevatorLabel;
    private JLabel title;
    private JLabel description;
    private JTextField idField;
    private JComboBox blockComboBox;
    private JComboBox floorComboBox;
    private JTextField managementField;
    private JTextField electricityField;
    private JTextField waterField;
    private JTextField elevatorField;
    private JTable table;
    private String[] columns = {"Id", "Block", "Floor", "Management", "Electricity", "Water", "Elevator", "Total"};
    private DefaultTableModel model;
    private String[] blocks = {"A1", "A2", "B1", "B2", "B3", "C1", "C2", "C3"};
    private Integer[] floors = new Integer[20];
    private Locale locale = new Locale("vi", "VN");
    private NumberFormat numFormat = NumberFormat.getCurrencyInstance(locale);
    
    public ApartmentView() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        buttons = new HashMap<>();
        title = new JLabel("AMS");
        title.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:bold +15");

        description = new JLabel("Enter apartment info here");
        description.putClientProperty(FlatClientProperties.STYLE, ""
                + "[light]foreground:lighten(@foreground,30%);"
                + "[dark]foreground:darken(@foreground,30%)");

        idLabel = new JLabel("Apartment ID");
        blockLabel = new JLabel("Block");
        floorLabel = new JLabel("Floor");
        managementLabel = new JLabel("Management");
        electricityLabel = new JLabel("Electricity");
        waterLabel = new JLabel("Water");
        elevatorLabel = new JLabel("Elevator");
        
        idField = new JTextField();
        for (int i = 0; i < floors.length; i++) {
            floors[i] = i+1;
        }
        floorComboBox = new JComboBox(floors);
        blockComboBox = new JComboBox(blocks);
        managementField = new JTextField();
        electricityField = new JTextField();
        waterField = new JTextField();
        elevatorField = new JTextField();

        idField.setEditable(false);

        idField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Default ID");
        managementField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter management");
        electricityField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter electricity");
        waterField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter water");
        elevatorField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter elavator");

        // Place to enter infos of apartment
        JPanel inputPanel = new JPanel(new MigLayout("wrap, fillx, insets 20 35 20 35", "fill"));
        inputPanel.putClientProperty(FlatClientProperties.STYLE, ""
                + "[dark]background:darken(@background, 5%);"
                + "[light]background:darken(@background,10%);"
                + "[dark]foreground:darken(@foreground, 1%)");

        inputPanel.add(title);
        inputPanel.add(description);
        inputPanel.add(new JSeparator());
        inputPanel.add(idLabel, "gapy 6");
        inputPanel.add(idField);
        inputPanel.add(floorLabel, "gapy 6");
        inputPanel.add(floorComboBox);
        inputPanel.add(blockLabel, "gapy 6");
        inputPanel.add(blockComboBox);
        inputPanel.add(managementLabel, "gapy 6");
        inputPanel.add(managementField);
        inputPanel.add(electricityLabel, "gapy 6");
        inputPanel.add(electricityField);
        inputPanel.add(waterLabel, "gapy 6");
        inputPanel.add(waterField);
        inputPanel.add(elevatorLabel, "gapy 6");
        inputPanel.add(elevatorField);

        // Search bar
        searchField = new JTextField();
        searchField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search");
        searchField.putClientProperty(FlatClientProperties.TEXT_FIELD_TRAILING_ICON, new FlatSVGIcon("icons/search.svg"));

        // Other operations
        buttons.put("Add", new JButton());
        buttons.put("Update", new JButton());
        buttons.put("Delete", new JButton());

        for (String buttonText : buttons.keySet()) {
            var button = buttons.get(buttonText);
            button.setText(buttonText);
            button.putClientProperty(FlatClientProperties.STYLE, ""
                    + "[light]background:darken(@background,10%);"
                    + "[dark]background:lighten(@background,10%);");
        }

        // Tool bar: search, add, update, delete
        JPanel toolBar = new JPanel(new MigLayout("insets 10 10 10 10", "[]push[][][]"));

        toolBar.add(searchField, "width 200");
        toolBar.add(buttons.get("Add"), "gapx 2");
        toolBar.add(buttons.get("Update"), "gapx 2");
        toolBar.add(buttons.get("Delete"), "gapx 2");

        // Table view
        // Creates table
        model = new DefaultTableModel(new Object[][]{}, columns)
        {
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 1:
                        return String.class;
                    default:
                        return Integer.class;
                }
            }
        };
        
        table = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        DefaultTableCellRenderer right = new DefaultTableCellRenderer();
        right.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
        table.getColumn("Block").setCellRenderer(right);
        
        table.putClientProperty(FlatClientProperties.STYLE, ""
                + "rowHeight:25;"
                + "showHorizontalLines:true;"
                + "showVerticalLines:true;"
                + "[dark]selectionBackground:darken(@background, 3%);"
                + "[light]selectionBackground:lighten(@background, 3%);");

        table.getTableHeader().putClientProperty(FlatClientProperties.STYLE, ""
                + "height:25;"
                + "hoverBackground:null;"
                + "[dark]background:darken(@background,2%);"
                + "[light]background:lighten(@background,3%)");

        table.getTableHeader().setReorderingAllowed(false);

        // Searchs element in table
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter);
        searchField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = searchField.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = searchField.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        // Adds table to scroll panel
        JScrollPane scroll = new JScrollPane(table);

        scroll.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                + "showButtons:true;"
                + "width:12;"
                + "thumbArc:999;"
                + "thumbInsets:2,2,2,2;"
                + "track:@background;"
                + "trackArc:999;");

        // Main panel
        JPanel panel = new JPanel(new MigLayout("fill, insets 10 10 10 10", "[fill]", "[grow 0][fill]"));
        panel.putClientProperty(FlatClientProperties.STYLE, ""
                + "[light]background:darken(@background,10%);"
                + "[dark]background:darken(@background,1%);"
                + "[light]border:0,0,0,0,shade(@background,5%);"
                + "[dark]border:0,0,0,0,tint(@background,5%)");

        panel.add(inputPanel, "width 300, dock west");
        panel.add(toolBar, "wrap, gapy 10");
        panel.add(scroll);

        this.add(panel);
        this.pack();

        this.setTitle("AMS");
        this.setSize(900, 600);
        this.setLocationRelativeTo(null);
    }
    
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void showListApartments(List<Apartment> list) {
        model.setRowCount(0);
        
        for (Apartment ap : list)
            model.addRow(new Object[]{
                    ap.getId(), 
                    ap.getBlock(), 
                    ap.getFloor(), 
                    numFormat.format(ap.getService().getManagement()), 
                    numFormat.format(ap.getService().getElectricity()),
                    numFormat.format(ap.getService().getWater()),
                    numFormat.format(ap.getService().getElevator()),
                    numFormat.format(ap.getService().getTotal())
                    });

        table.setModel(model);
        table.repaint();
    }

    public void fillApartmentFromSelectedRow() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            idField.setText(table.getModel().getValueAt(row, 0).toString());
            for (int i = 0; i < floorComboBox.getItemCount(); i++) {
                if (floorComboBox.getItemAt(i).toString().equals(table.getModel().getValueAt(row, 2).toString())) {
                    floorComboBox.setSelectedIndex(i);
                    break;
                }
            }
            for (int i = 0; i < blockComboBox.getItemCount(); i++) {
                if (blockComboBox.getItemAt(i).toString().equals(table.getModel().getValueAt(row, 1).toString())) {
                    blockComboBox.setSelectedIndex(i);
                    break;
                }
            }
            managementField.setText(table.getModel().getValueAt(row, 3).toString().replaceAll("[^0-9]", ""));
            electricityField.setText(table.getModel().getValueAt(row, 4).toString().replaceAll("[^0-9]", ""));
            waterField.setText(table.getModel().getValueAt(row, 5).toString().replaceAll("[^0-9]", ""));
            elevatorField.setText(table.getModel().getValueAt(row, 6).toString().replaceAll("[^0-9]", ""));
        }
    }

    public void clearApartmentInfo() {
        idField.setText("");
        floorComboBox.setSelectedIndex(0);
        blockComboBox.setSelectedIndex(0);
        managementField.setText("");
        electricityField.setText("");
        waterField.setText("");
        elevatorField.setText("");
    }

    public void showApartment(Apartment apartment) {
        idField.setText(String.valueOf(apartment.getId()));
        floorComboBox.setSelectedItem(apartment.getFloor());
        blockComboBox.setSelectedItem(apartment.getBlock());
        managementField.setText(String.valueOf(apartment.getService().getManagement()));
        electricityField.setText(String.valueOf(apartment.getService().getElectricity()));
        waterField.setText(String.valueOf(apartment.getService().getWater()));
        elevatorField.setText(String.valueOf(apartment.getService().getElevator()));
    }

    public Apartment getApartmentInfo() {
        if (!validateManagement() || !validateElectricity() || !validateWater()) {
            return null;
        }
        try {
            Apartment apartment = new Apartment();
            if (idField.getText() != null && !"".equals(idField.getText())) {
                apartment.setId(Integer.parseInt(idField.getText()));
            }
            apartment.setFloor((Integer) floorComboBox.getSelectedItem());
            apartment.setBlock((String) blockComboBox.getSelectedItem());
            apartment.getService().setManagement(Integer.parseInt(managementField.getText().trim()));
            apartment.getService().setElectricity(Integer.parseInt(electricityField.getText().trim()));
            apartment.getService().setWater(Integer.parseInt(waterField.getText().trim()));
            apartment.getService().setElevator(Integer.parseInt(elevatorField.getText().trim()));
            return apartment;
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
        return null;
    }

    private boolean validateManagement() {
        return true;
    }

    private boolean validateElectricity() {
        return true;
    }

    private boolean validateWater() {
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public void addAddBtnListener(ActionListener listener) {
        buttons.get("Add").addActionListener(listener);
    }

    public void addUpdateBtnListener(ActionListener listener) {
        buttons.get("Update").addActionListener(listener);
    }

    public void addDeleteBtnListener(ActionListener listener) {
        buttons.get("Delete").addActionListener(listener);
    }
    
    public void addListApartmentSelectionListener(ListSelectionListener listener) {
        table.getSelectionModel().addListSelectionListener(listener);
    }
}
