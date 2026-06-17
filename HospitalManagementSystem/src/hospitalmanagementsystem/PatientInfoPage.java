package hospitalmanagementsystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class PatientInfoPage implements ActionListener {

    JFrame frame = new JFrame("HMS - Patient Info System");
    JButton backButton = new JButton("← BACK");
    JButton registerButton = new JButton("REGISTER PATIENT");
    JButton viewRegistrationsButton = new JButton("VIEW REGISTRATIONS");
    JButton clearAllButton = new JButton("CLEAR ALL LOGS"); // Initialized the clear logs button
    ImageIcon im = new ImageIcon("logo.jpg"); // initializing the logo image

    JTextField idField = new JTextField();
    JTextField nameField = new JTextField();
    JTextField ageField = new JTextField();
    JTextField genderField = new JTextField();
    JTextField phoneField = new JTextField();
    JTextField addressField = new JTextField();
    JTextField emailField = new JTextField();
    JTextField diseaseField = new JTextField();
    JTextField admissionDateField = new JTextField();
    JTextField roomField = new JTextField();
    JTextField historyField = new JTextField();

    public PatientInfoPage() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(245, 245, 245));
        frame.setSize(900, 700);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(im.getImage()); // setting logo next to frame title

        JPanel mainPanel = new JPanel(new BorderLayout(0, 10));
        mainPanel.setOpaque(false);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setOpaque(false);
        
        backButton.setFocusable(false);
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backButton.setForeground(new Color(51, 0, 25));
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setActionCommand("BACK");
        backButton.addActionListener(this);
        topPanel.add(backButton);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // --- EXTENDED FORM LAYOUT ---
        JPanel formPanel = new JPanel(new GridLayout(6, 4, 15, 15));
        formPanel.setOpaque(false);
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        addFormRow(formPanel, "Patient ID:", idField);
        addFormRow(formPanel, "Name:", nameField);
        addFormRow(formPanel, "Age:", ageField);
        addFormRow(formPanel, "Gender:", genderField);
        addFormRow(formPanel, "Phone Number:", phoneField);
        addFormRow(formPanel, "Address:", addressField);
        addFormRow(formPanel, "Email ID:", emailField);
        addFormRow(formPanel, "Diagnosed Disease:", diseaseField);
        addFormRow(formPanel, "Admission Date:", admissionDateField);
        addFormRow(formPanel, "Room Allocation:", roomField);
        
        JLabel historyLabel = new JLabel("Medical History:");
        historyLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        historyLabel.setForeground(new Color(51, 0, 25));
        formPanel.add(historyLabel);
        formPanel.add(historyField);
        
        formPanel.add(new JLabel(""));
        formPanel.add(new JLabel(""));

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // --- LOWER ACTION PANEL FOR BUTTONS (Expanded to 3 columns) ---
        JPanel actionPanel = new JPanel(new GridLayout(1, 3, 15, 0));
        actionPanel.setOpaque(false);
        actionPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        styleActionButton(registerButton, new Color(51, 0, 25), "REGISTER");
        styleActionButton(viewRegistrationsButton, new Color(51, 0, 25), "VIEW");
        styleActionButton(clearAllButton, new Color(120, 10, 20), "CLEAR"); // Deep red alert style

        actionPanel.add(registerButton);
        actionPanel.add(viewRegistrationsButton);
        actionPanel.add(clearAllButton);

        mainPanel.add(actionPanel, BorderLayout.SOUTH);

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

    private void styleActionButton(JButton button, Color backgroundColor, String command) {
        button.setFocusable(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Balanced for 3 button spacing
        button.setForeground(Color.WHITE);
        button.setBackground(backgroundColor);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(12, 0, 12, 0));
        button.setActionCommand(command);
        button.addActionListener(this);
    }

    private void showRegistrationsTableWindow() {
        JFrame tableFrame = new JFrame("HMS - Registered Patients List");
        tableFrame.setSize(1000, 450);
        tableFrame.setLocationRelativeTo(frame);
        tableFrame.setLayout(new BorderLayout(10, 10));

        String[] columns = {"Patient Records / Registered Log History Entries"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(tableModel);
        
        table.setFont(new Font("Consolas", Font.PLAIN, 12));
        table.setRowHeight(26);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        table.getTableHeader().setBackground(new Color(51, 0, 25));
        table.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
        tableFrame.add(scrollPane, BorderLayout.CENTER);

        File file = new File("patients.txt");
        if (!file.exists()) {
            JOptionPane.showMessageDialog(frame, "No patient records found in patients.txt yet.", "Database Empty", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String secureLine = line.replaceAll("hospitalmanagementsystem\\.[a-zA-Z0-9]+@", "@");
                    tableModel.addRow(new Object[]{"  " + secureLine.trim()});
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(tableFrame, "Error parsing file lines: " + ex.getMessage(), "IO Error", JOptionPane.ERROR_MESSAGE);
        }

        JLabel footerLabel = new JLabel("  Data pulled securely from streaming file logs database.");
        footerLabel.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        tableFrame.add(footerLabel, BorderLayout.SOUTH);

        tableFrame.setVisible(true);
    }

    private void clearFormFields() {
        idField.setText("");
        nameField.setText("");
        ageField.setText("");
        genderField.setText("");
        phoneField.setText("");
        addressField.setText("");
        emailField.setText("");
        diseaseField.setText("");
        admissionDateField.setText("");
        roomField.setText("");
        historyField.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ("BACK".equals(command)) {
            frame.dispose();
            new PatientPage();
            return;
        }

        if ("VIEW".equals(command)) {
            showRegistrationsTableWindow();
            return;
        }

        // --- CLEAR ALL LOGS EVENT ---
        if ("CLEAR".equals(command)) {
            int choice = JOptionPane.showConfirmDialog(frame, 
                    "Clear ALL patient records permanently?", 
                    "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            
            if (choice == JOptionPane.YES_OPTION) {
                try (FileWriter fw = new FileWriter("patients.txt", false)) {
                    fw.write(""); // Overwrites text database file with blank entry 
                    clearFormFields(); 
                    JOptionPane.showMessageDialog(frame, "Patient records cleared successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "IO Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            return;
        }

        if ("REGISTER".equals(command)) {
            try {
                if (idField.getText().trim().isEmpty() || nameField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Patient ID and Name are mandatory fields!", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int id = Integer.parseInt(idField.getText().trim());
                String name = nameField.getText().trim();
                int age = Integer.parseInt(ageField.getText().trim());
                String gen = genderField.getText().trim();
                String ph = phoneField.getText().trim();
                String addr = addressField.getText().trim();
                String email = emailField.getText().trim();
                String disease = diseaseField.getText().trim();
                String admDate = admissionDateField.getText().trim();
                int rm = Integer.parseInt(roomField.getText().trim());
                String history = historyField.getText().trim();

                Patient patientRecord = new Patient(id, name, age, gen, ph, addr, email, disease, admDate, rm, history);
                patientRecord.displayInfo(); 

                JOptionPane.showMessageDialog(frame, "Patient Profile Created & Recorded Successfully!", "Registration Confirmed", JOptionPane.INFORMATION_MESSAGE);
                clearFormFields();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please complete ID, Age, and Room fields with numeric values.", "Parsing Verification Exception", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}