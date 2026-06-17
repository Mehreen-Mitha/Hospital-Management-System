package hospitalmanagementsystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class DoctorPage implements ActionListener {

    JFrame frame = new JFrame("HMS - Doctor Information");
    JButton backButton = new JButton("← BACK");
    ImageIcon im = new ImageIcon("logo.jpg"); // Initializing the logo image
    
    JButton registerButton = new JButton("REGISTER DOCTOR");
    JButton viewRegistrationsButton = new JButton("VIEW DOCTORS");
    JButton clearAllButton = new JButton("CLEAR ALL LOGS");

    // Inherited fields
    JTextField idField = new JTextField();
    JTextField nameField = new JTextField();
    JTextField ageField = new JTextField();
    JTextField genderField = new JTextField();
    JTextField phoneField = new JTextField();
    JTextField addressField = new JTextField();
    JTextField emailField = new JTextField();
    
    // Doctor specific fields
    JTextField specializationField = new JTextField();
    JTextField salaryField = new JTextField();
    JTextField experienceField = new JTextField();

    public DoctorPage() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(245, 245, 245));
        frame.setSize(900, 700);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(im.getImage()); // Setting logo next to frame title

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
        backButton.addActionListener(this);
        topPanel.add(backButton);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // --- FORM LAYOUT ---
        JPanel formPanel = new JPanel(new GridLayout(5, 4, 15, 15));
        formPanel.setOpaque(false);
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        addFormRow(formPanel, "Doctor ID:", idField);
        addFormRow(formPanel, "Name:", nameField);
        addFormRow(formPanel, "Age:", ageField);
        addFormRow(formPanel, "Gender:", genderField);
        addFormRow(formPanel, "Phone Number:", phoneField);
        addFormRow(formPanel, "Address:", addressField);
        addFormRow(formPanel, "Email ID:", emailField);
        addFormRow(formPanel, "Specialization:", specializationField);
        addFormRow(formPanel, "Salary ($):", salaryField);
        addFormRow(formPanel, "Experience (Yrs):", experienceField);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // --- LOWER ACTION PANEL ---
        JPanel actionPanel = new JPanel(new GridLayout(1, 3, 15, 0));
        actionPanel.setOpaque(false);
        actionPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        styleActionButton(registerButton, new Color(51, 0, 25));
        styleActionButton(viewRegistrationsButton, new Color(51, 0, 25));
        styleActionButton(clearAllButton, new Color(120, 10, 20)); // Deep red alert style

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

    private void styleActionButton(JButton button, Color backgroundColor) {
        button.setFocusable(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setForeground(Color.WHITE);
        button.setBackground(backgroundColor);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(12, 0, 12, 0));
        button.addActionListener(this);
    }

    private void showDoctorsTableWindow() {
        JFrame tableFrame = new JFrame("HMS - Organized Doctor Directory");
        tableFrame.setSize(1100, 500);
        tableFrame.setLocationRelativeTo(frame);
        tableFrame.setLayout(new BorderLayout(10, 10));
        tableFrame.setIconImage(im.getImage()); // Setting logo next to frame title

        String[] columns = {
            "ID", "Name", "Age", "Gender", "Phone", "Address", "Email", "Specialization", "Salary", "Experience"
        };
        
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        
        JTable table = new JTable(tableModel);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setRowHeight(28);
        table.setGridColor(new Color(230, 230, 230));
        table.setShowGrid(true);
        
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        table.getTableHeader().setBackground(new Color(51, 0, 25));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(table);
        tableFrame.add(scrollPane, BorderLayout.CENTER);

        File file = new File("doctors.txt");
        if (!file.exists()) {
            JOptionPane.showMessageDialog(frame, "No doctor records found in doctors.txt yet.", "Database Empty", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] tokens = line.split("\\s+");
                    if (tokens.length >= 10) {
                        tableModel.addRow(tokens);
                    } else {
                        String[] paddedTokens = new String[10];
                        System.arraycopy(tokens, 0, paddedTokens, 0, Math.min(tokens.length, 10));
                        tableModel.addRow(paddedTokens);
                    }
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(tableFrame, "Error parsing file lines: " + ex.getMessage(), "IO Error", JOptionPane.ERROR_MESSAGE);
        }

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(new Color(240, 240, 240));
        JLabel footerLabel = new JLabel("Total Registered medical staff logs parsed successfully.   ");
        footerLabel.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        bottomPanel.add(footerLabel);
        tableFrame.add(bottomPanel, BorderLayout.SOUTH);

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
        specializationField.setText("");
        salaryField.setText("");
        experienceField.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            frame.dispose();
            new SecondPage();
            return;
        }

        if (e.getSource() == viewRegistrationsButton) {
            showDoctorsTableWindow();
            return;
        }

        // --- CLEAR ALL LOGS EVENT ---
        if (e.getSource() == clearAllButton) {
            int choice = JOptionPane.showConfirmDialog(frame, 
                    "Clear ALL doctor records permanently?", 
                    "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            
            if (choice == JOptionPane.YES_OPTION) {
                try (FileWriter fw = new FileWriter("doctors.txt", false)) {
                    fw.write(""); // Overwrites text database file with blank entry 
                    clearFormFields(); 
                    JOptionPane.showMessageDialog(frame, "Doctor records cleared successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "IO Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            return;
        }

        // --- REGISTER DOCTOR EVENT ---
        if (e.getSource() == registerButton) {
            try {
                if (idField.getText().trim().isEmpty() || nameField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Doctor ID and Name are mandatory fields!", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int id = Integer.parseInt(idField.getText().trim());
                String name = nameField.getText().trim();
                int age = Integer.parseInt(ageField.getText().trim());
                String gen = genderField.getText().trim();
                String ph = phoneField.getText().trim();
                String addr = addressField.getText().trim();
                String email = emailField.getText().trim();
                String spec = specializationField.getText().trim();
                double sal = Double.parseDouble(salaryField.getText().trim());
                int exp = Integer.parseInt(experienceField.getText().trim());

                Doctor docRecord = new Doctor(id, name, age, gen, ph, addr, email, spec, sal, exp);
                docRecord.displayInfo(); 

                JOptionPane.showMessageDialog(frame, "Doctor Profile Created & Recorded Successfully!", "Registration Confirmed", JOptionPane.INFORMATION_MESSAGE);
                clearFormFields();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please complete numeric fields with clean integer or decimal values.", "Parsing Verification Exception", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}