package hospitalmanagementsystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class DeathTollPage implements ActionListener {

    JFrame frame = new JFrame("HMS - Death Toll Records");
    JButton backButton = new JButton("← BACK");
    JButton logButton = new JButton("LOG RECORD");
    JButton viewSummaryButton = new JButton("VIEW SUMMARY");
    JButton clearLogsButton = new JButton("CLEAR ALL LOGS");
    ImageIcon im = new ImageIcon("logo.jpg"); // initializing the logo image

    JTextField casualtiesField = new JTextField();
    JTextField locationField = new JTextField();
    JTextField dateField = new JTextField();
    JTextField causeField = new JTextField();
    JTextField reporterField = new JTextField();
    JTextField notesField = new JTextField();

    public DeathTollPage() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(245, 245, 245));
        frame.setSize(900, 700);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(im.getImage()); // setting logo next to frame title

        JPanel mainPanel = new JPanel(new BorderLayout(0, 15));
        mainPanel.setOpaque(false);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setOpaque(false);
        backButton.setFocusable(false);
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backButton.setForeground(new Color(51, 0, 25));
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.addActionListener(this);
        topPanel.add(backButton);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 15, 15));
        formPanel.setOpaque(false);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 120, 20, 120));

        addFormRow(formPanel, "Number of Casualties:", casualtiesField);
        addFormRow(formPanel, "Location:", locationField);
        addFormRow(formPanel, "Date (DD-MM-YYYY):", dateField);
        addFormRow(formPanel, "Cause:", causeField);
        addFormRow(formPanel, "Reporter Name:", reporterField);
        addFormRow(formPanel, "Incident Notes:", notesField);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new GridLayout(1, 3, 20, 0));
        bottomPanel.setOpaque(false);

        styleButton(logButton, new Color(51, 0, 25));
        styleButton(viewSummaryButton, new Color(51, 0, 25));
        styleButton(clearLogsButton, new Color(120, 10, 20)); 

        bottomPanel.add(logButton);
        bottomPanel.add(viewSummaryButton);
        bottomPanel.add(clearLogsButton);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void addFormRow(JPanel panel, String labelText, JTextField textField) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(new Color(51, 0, 25));
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(label);
        panel.add(textField);
    }

    private void styleButton(JButton button, Color backgroundColor) {
        button.setFocusable(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(backgroundColor);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(12, 0, 12, 0));
        button.addActionListener(this);
    }

    private void clearTextFields() {
        casualtiesField.setText("");
        locationField.setText("");
        dateField.setText("");
        causeField.setText("");
        reporterField.setText("");
        notesField.setText("");
    }

    // --- UPDATED TABULAR VIEW WINDOW WITH REQUESTED COLUMNS ---
    private void showSummaryTableWindow() {
        JFrame tableFrame = new JFrame("HMS - Casualties Summary Evaluation");
        tableFrame.setSize(750, 400);
        tableFrame.setLocationRelativeTo(frame);
        tableFrame.setLayout(new BorderLayout(10, 10));

        // Columns updated to match your strict layout structure: Casualties, Date, Location
        String[] columns = {"No. of Casualties", "Date", "Location"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(tableModel);
        
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setRowHeight(24);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        table.getTableHeader().setBackground(new Color(51, 0, 25));
        table.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
        tableFrame.add(scrollPane, BorderLayout.CENTER);

        File file = new File("death_toll.txt");
        if (!file.exists()) {
            JOptionPane.showMessageDialog(frame, "No documented mortality metrics exist yet.", "Empty Database", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] rawSplit = line.split(",");
                
                if (rawSplit.length >= 3) {
                    // 1. Extract location
                    String loc = rawSplit[0].trim();
                    
                    // 2. Extract date string
                    String dateStr = rawSplit[1].trim();
                    if (dateStr.isEmpty()) {
                        dateStr = "N/A";
                    }
                    
                    // 3. Extract final numeric casualties trend safely
                    String rawCasualtiesTrend = rawSplit[2].trim();
                    String finalCasualties = rawCasualtiesTrend.contains("->") 
                        ? rawCasualtiesTrend.substring(rawCasualtiesTrend.indexOf("->") + 2).trim() 
                        : rawCasualtiesTrend;

                    // Add elements to match the new columns layout structure precisely
                    tableModel.addRow(new Object[]{finalCasualties, dateStr, loc});
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(tableFrame, "Error rendering file records matrix: " + ex.getMessage(), "IO Error", JOptionPane.ERROR_MESSAGE);
        }

        tableFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            frame.dispose();
            new PatientPage();
            return;
        }

        if (e.getSource() == logButton) {
            if (casualtiesField.getText().trim().isEmpty() || locationField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please complete fields to process record entry.", "Data Validation Warning", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int casualties = Integer.parseInt(casualtiesField.getText().trim());
                String loc = locationField.getText().trim();
                String dt = dateField.getText().trim();
                String cause = causeField.getText().trim();
                String rep = reporterField.getText().trim();
                String notes = notesField.getText().trim();

                DeathToll record = new DeathToll(casualties, loc, dt, cause, rep, notes);
                record.update_casualties(casualties); 
                record.alertAuthorities();

                JOptionPane.showMessageDialog(frame, "Incident Report Logged Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearTextFields();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please check numeric entry metrics parsing rules.", "Input Mask Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == viewSummaryButton) {
            showSummaryTableWindow();
        }

        if (e.getSource() == clearLogsButton) {
            int verifyAction = JOptionPane.showConfirmDialog(
                frame, 
                "Are you absolutely certain you want to clear the entire log file history permanently?", 
                "Confirm Destructive Deletion", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.WARNING_MESSAGE
            );

            if (verifyAction == JOptionPane.YES_OPTION) {
                try (FileWriter fw = new FileWriter("death_toll.txt", false)) {
                    fw.write(""); 
                    clearTextFields();
                    JOptionPane.showMessageDialog(frame, "Log records cleared successfully.", "Clear Process Completed", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Error execution filesystem request: " + ex.getMessage(), "File Exception", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}