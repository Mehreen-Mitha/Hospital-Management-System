package hospitalmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DischargePage implements ActionListener {

    JFrame frame = new JFrame("HMS - Discharge Management");
    JButton backButton = new JButton("← BACK");
    JButton dischargeButton = new JButton("PROCESS DISCHARGE");
    ImageIcon im = new ImageIcon("logo.jpg"); // initializing the logo image

    JTextField idField = new JTextField();
    JTextField nameField = new JTextField();
    JTextField dateField = new JTextField();
    JTextField billField = new JTextField();
    JCheckBox icuCheckbox = new JCheckBox("Requires ICU Bed Release Tracking");

    public DischargePage() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(245, 245, 245));
        frame.setSize(900, 700);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(im.getImage()); // setting logo next to frame title

        JPanel mainPanel = new JPanel(new BorderLayout(0, 20));
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

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 15, 25));
        formPanel.setOpaque(false);
        formPanel.setBorder(BorderFactory.createEmptyBorder(40, 150, 40, 150));

        addFormRow(formPanel, "Patient ID:", idField);
        addFormRow(formPanel, "Patient Name:", nameField);
        addFormRow(formPanel, "Discharge Date:", dateField);
        addFormRow(formPanel, "Total Bill Amount ($):", billField);

        JLabel icuLabel = new JLabel("ICU Check:");
        icuLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        icuLabel.setForeground(new Color(51, 0, 25));
        icuCheckbox.setOpaque(false);
        icuCheckbox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        formPanel.add(icuLabel);
        formPanel.add(icuCheckbox);

        dischargeButton.setFocusable(false);
        dischargeButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        dischargeButton.setForeground(Color.WHITE);
        dischargeButton.setBackground(new Color(51, 0, 25));
        dischargeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        dischargeButton.setBorder(BorderFactory.createEmptyBorder(12, 0, 12, 0));
        dischargeButton.addActionListener(this);

        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(dischargeButton, BorderLayout.SOUTH);

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            frame.dispose();
            new PatientPage();
            return;
        }

        if (e.getSource() == dischargeButton) {
            try {
                int pId = Integer.parseInt(idField.getText().trim());
                String name = nameField.getText().trim();
                String date = dateField.getText().trim();
                double bill = Double.parseDouble(billField.getText().trim());
                boolean isIcu = icuCheckbox.isSelected();

                // Mocking data objects safely to integrate smoothly into existing backend logic structure
                Patient mockPatient = new Patient();
                mockPatient.id = pId;
                mockPatient.name = name;

                // Safely initialized structural assumptions matching backend files
                Appointments appTrack = new Appointments(); 
                appTrack.setCurrentPatients(10); // Simple initialization reference
                BedAvailability bedTrack = new BedAvailability();

                DischargePatients process = new DischargePatients(mockPatient, appTrack, bedTrack);
                process.setDischargeDate(date);
                process.processPatient(isIcu, bill);
                process.generateDischargeSummary();

                JOptionPane.showMessageDialog(frame, "Patient Discharged Successfully! Records outputted to discharge.txt", "Discharge Finalized", JOptionPane.INFORMATION_MESSAGE);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid identification or billing entries numerical format.", "Input Validation Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "System structural component tracking configuration initialization exception.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}