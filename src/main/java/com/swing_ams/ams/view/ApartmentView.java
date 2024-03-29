package com.swing_ams.ams.view;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.swing_ams.ams.model.Apartment;
import com.swing_ams.ams.model.ApartmentService.PaidDate;
import com.swing_ams.ams.model.ApartmentService.Quarter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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
    private JLabel ownerLabel;
    private JLabel paidLabel; // pls do this shit
    private JLabel dateLabel;
    private JLabel quarterLabel;
    private JLabel yearLabel;
    private JLabel floorLabel;
    private JLabel blockLabel;
    private JLabel managementLabel;
    private JLabel electricityLabel;
    private JLabel waterLabel;
    private JLabel elevatorLabel;
    private JLabel title;
    private JLabel description;

    private JTextField idField;
    private JTextField ownerField;
    private ButtonGroup radioGroup;
    private JRadioButton paidRadioButton;
    private JRadioButton unpaidRadioButton;
    private JComboBox quarterComboBox;
    private JComboBox yearComboBox;
    private JComboBox blockComboBox;
    private JComboBox floorComboBox;
    private JComboBox blockFilter;
    private JComboBox paidFilter;
    private JTextField managementField;
    private JTextField electricityField;
    private JTextField waterField;
    private JTextField elevatorField;

    private JTable table;
    private String[] columns = {"Id", "Owner", "Block", "Floor", "Paid", "Date", "Management", "Electricity", "Water", "Elevator", "Total"};
    private DefaultTableModel model;

    private String[] quarters = {"I", "II", "III", "IV"};
    private Integer[] years = new Integer[124];
    private String[] blocks = {"A1", "A2", "B1", "B2", "B3", "C1", "C2", "C3"};
    private Integer[] floors = new Integer[20];
    private String[] blockFilterValues = {"None", "A1", "A2", "B1", "B2", "B3", "C1", "C2", "C3"};
    private String[] paidFilterValues = {"None", "paid", "unpaid"};

    private final Locale locale = new Locale("vi", "VN");
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

        description = new JLabel("Apartment information");
        description.putClientProperty(FlatClientProperties.STYLE, ""
                + "[light]foreground:lighten(@foreground,30%);"
                + "[dark]foreground:darken(@foreground,30%)");

        idLabel = new JLabel("Apartment ID");
        ownerLabel = new JLabel("Owner");
        paidLabel = new JLabel("Is paid?");
        dateLabel = new JLabel("Date");
        quarterLabel = new JLabel("Quarter");
        yearLabel = new JLabel("Year");
        blockLabel = new JLabel("Block");
        floorLabel = new JLabel("Floor");
        managementLabel = new JLabel("Management");
        electricityLabel = new JLabel("Electricity");
        waterLabel = new JLabel("Water");
        elevatorLabel = new JLabel("Elevator");

        for (int i = 0; i < floors.length; i++) {
            floors[i] = i + 1;
        }

        for (int i = 0; i < years.length; i++) {
            years[i] = 2024 - i; // Start from 2024
        }

        idField = new JTextField();
        ownerField = new JTextField();
        radioGroup = new ButtonGroup();
        paidRadioButton = new JRadioButton("paid", true);
        unpaidRadioButton = new JRadioButton("unpaid", false);
        radioGroup.add(paidRadioButton);
        radioGroup.add(unpaidRadioButton);
        quarterComboBox = new JComboBox<>(quarters);
        yearComboBox = new JComboBox(years);
        floorComboBox = new JComboBox(floors);
        blockComboBox = new JComboBox(blocks);
        managementField = new JTextField();
        electricityField = new JTextField();
        waterField = new JTextField();
        elevatorField = new JTextField();

        idField.setEditable(false);

        idField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Default ID");
        ownerField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter apartment's owner");
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
        inputPanel.add(ownerLabel, "gapy 6");
        inputPanel.add(ownerField);
        inputPanel.add(floorLabel, "gapy 6");
        inputPanel.add(floorComboBox);
        inputPanel.add(blockLabel, "gapy 6");
        inputPanel.add(blockComboBox);
        inputPanel.add(paidLabel, "gapy 6");
        inputPanel.add(paidRadioButton, "split 2");
        inputPanel.add(unpaidRadioButton);
        inputPanel.add(dateLabel, "gapy 6");
        inputPanel.add(quarterLabel, "split 4");
        inputPanel.add(quarterComboBox);
        inputPanel.add(yearLabel);
        inputPanel.add(yearComboBox, "wrap");
        inputPanel.add(managementLabel, "gapy 6");
        inputPanel.add(managementField);
        inputPanel.add(electricityLabel, "gapy 6");
        inputPanel.add(electricityField);
        inputPanel.add(waterLabel, "gapy 6");
        inputPanel.add(waterField);
        inputPanel.add(elevatorLabel, "gapy 6");
        inputPanel.add(elevatorField);

        JScrollPane inputScrollPane = new JScrollPane(inputPanel);
        inputScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        inputScrollPane.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                + "showButtons:true;"
                + "width:12;"
                + "thumbArc:999;"
                + "thumbInsets:2,2,2,2;"
                + "track:@background;"
                + "trackArc:999;");

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

        blockFilter = new JComboBox(blockFilterValues);
        paidFilter = new JComboBox(paidFilterValues);

        ActionListener filterListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedBlock = (String) blockFilter.getSelectedItem();
                String selectedPaid = (String) paidFilter.getSelectedItem();

                RowFilter<Object, Object> blockRowFilter = RowFilter.regexFilter(selectedBlock.equals("None") ? ".*" : selectedBlock, 2);
                RowFilter<Object, Object> paidRowFilter = RowFilter.regexFilter(selectedPaid.equals("None") ? ".*" : "^" + selectedPaid + "$", 4);

                RowFilter<Object, Object> combinedFilter = RowFilter.andFilter(Arrays.asList(blockRowFilter, paidRowFilter));

                TableRowSorter<DefaultTableModel> sorter = (TableRowSorter<DefaultTableModel>) table.getRowSorter();
                sorter.setRowFilter(combinedFilter);
            }
        };

        blockFilter.addActionListener(filterListener);
        paidFilter.addActionListener(filterListener);

        // Tool bar: search, add, update, delete
        JPanel toolBar = new JPanel(new MigLayout("insets 10 10 10 10", "[]push[][][][][]"));

        toolBar.add(searchField, "width 200");
        toolBar.add(blockFilter, "gapx 2");
        toolBar.add(paidFilter, "gapx 2");
        toolBar.add(buttons.get("Add"), "gapx 2");
        toolBar.add(buttons.get("Update"), "gapx 2");
        toolBar.add(buttons.get("Delete"), "gapx 2");

        // Table view
        // Creates table
        model = new DefaultTableModel(new Object[][]{}, columns) {
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
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter);

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

        panel.add(inputScrollPane, "width 300, dock west");
        panel.add(toolBar, "wrap, gapy 10");
        panel.add(scroll);

        this.add(panel);
        this.pack();

        this.setTitle("AMS");
        this.setSize(1200, 600);
        this.setLocationRelativeTo(null);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void showListApartments(List<Apartment> list) {
        model.setRowCount(0);
        if (list != null) {
            for (Apartment ap : list) {
                model.addRow(new Object[]{
                    ap.getId(),
                    ap.getOwner(),
                    ap.getBlock(),
                    ap.getFloor(),
                    ap.getService().isPaid() ? "paid" : "unpaid",
                    ap.getService().getDate().toString(),
                    numFormat.format(ap.getService().getManagement()),
                    numFormat.format(ap.getService().getElectricity()),
                    numFormat.format(ap.getService().getWater()),
                    numFormat.format(ap.getService().getElevator()),
                    numFormat.format(ap.getService().getTotal())
                });
            }
        }

        table.setModel(model);
        table.repaint();
    }

    public void fillApartmentFromSelectedRow() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            int _row = table.convertRowIndexToModel(row);
            idField.setText(table.getModel().getValueAt(_row, 0).toString());
            ownerField.setText(table.getModel().getValueAt(_row, 1).toString());
            for (int i = 0; i < floorComboBox.getItemCount(); i++) {
                if (floorComboBox.getItemAt(i).toString().equals(table.getModel().getValueAt(_row, 3).toString())) {
                    floorComboBox.setSelectedIndex(i);
                    break;
                }
            }
            for (int i = 0; i < blockComboBox.getItemCount(); i++) {
                if (blockComboBox.getItemAt(i).toString().equals(table.getModel().getValueAt(_row, 2).toString())) {
                    blockComboBox.setSelectedIndex(i);
                    break;
                }
            }
            String isPaid = table.getModel().getValueAt(_row, 4).toString();
            if (isPaid.equalsIgnoreCase("paid")) {
                paidRadioButton.setSelected(true);
            } else {
                unpaidRadioButton.setSelected(true);
            }

            String[] tmp = table.getModel().getValueAt(_row, 5).toString().split("/");
            for (int i = 0; i < quarterComboBox.getItemCount(); i++) {
                if (quarterComboBox.getItemAt(i).toString().equals(tmp[0])) {
                    quarterComboBox.setSelectedIndex(i);
                    break;
                }
            }
            for (int i = 0; i < yearComboBox.getItemCount(); i++) {
                if (yearComboBox.getItemAt(i).toString().equals(tmp[1])) {
                    yearComboBox.setSelectedIndex(i);
                    break;
                }
            }
            managementField.setText(table.getModel().getValueAt(_row, 6).toString().replaceAll("[^0-9]", ""));
            electricityField.setText(table.getModel().getValueAt(_row, 7).toString().replaceAll("[^0-9]", ""));
            waterField.setText(table.getModel().getValueAt(_row, 8).toString().replaceAll("[^0-9]", ""));
            elevatorField.setText(table.getModel().getValueAt(_row, 9).toString().replaceAll("[^0-9]", ""));
        }
    }

    public void clearApartmentInfo() {
        idField.setText("");
        ownerField.setText("");
        floorComboBox.setSelectedIndex(0);
        blockComboBox.setSelectedIndex(0);
        quarterComboBox.setSelectedIndex(0);
        yearComboBox.setSelectedIndex(0);
        radioGroup.clearSelection();
        managementField.setText("");
        electricityField.setText("");
        waterField.setText("");
        elevatorField.setText("");
    }

    public void showApartment(Apartment apartment) {
        idField.setText(String.valueOf(apartment.getId()));
        ownerField.setText(apartment.getOwner());
        floorComboBox.setSelectedItem(apartment.getFloor());
        blockComboBox.setSelectedItem(apartment.getBlock());
        if (apartment.getService().isPaid()) {
            paidRadioButton.setSelected(true);
        } else {
            unpaidRadioButton.setSelected(true);
        }
        quarterComboBox.setSelectedItem(apartment.getService().getDate().getQuarter().value);
        yearComboBox.setSelectedItem(apartment.getService().getDate().getYear());
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
            apartment.setOwner(ownerField.getText());
            apartment.setFloor((Integer) floorComboBox.getSelectedItem());
            apartment.setBlock((String) blockComboBox.getSelectedItem());
            apartment.getService().setManagement(Integer.parseInt(managementField.getText().trim()));
            apartment.getService().setElectricity(Integer.parseInt(electricityField.getText().trim()));
            apartment.getService().setWater(Integer.parseInt(waterField.getText().trim()));
            apartment.getService().setElevator(Integer.parseInt(elevatorField.getText().trim()));
            apartment.getService().setPaid(paidRadioButton.isSelected());
            apartment.getService().setDate(new PaidDate(Quarter.valueOf((String) quarterComboBox.getSelectedItem()), (Integer) yearComboBox.getSelectedItem()));
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
