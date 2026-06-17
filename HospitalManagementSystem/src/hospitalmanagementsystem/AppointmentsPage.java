package hospitalmanagementsystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class AppointmentsPage implements ActionListener {

    JFrame frame = new JFrame("HMS - Appointments Management");
    JButton backButton = new JButton("← BACK");
    ImageIcon im = new ImageIcon("logo.jpg"); // initializing the logo image
    
    JButton scheduleButton = new JButton("SCHEDULE");
    JButton rescheduleButton = new JButton("RESCHEDULE");
    JButton cancelButton = new JButton("CANCEL");
    JButton admitButton = new JButton("ADMIT");
    JButton viewAppointmentsButton = new JButton("VIEW APPOINTMENTS");
    JButton clearAllButton = new JButton("CLEAR ALL LOGS");

    JTextField appIdField = new JTextField();
    JTextField patientNameField = new JTextField();
    JTextField doctorNameField = new JTextField();
    JTextField dateTimeField = new JTextField();
    JTextField departmentField = new JTextField();
    JTextField feeField = new JTextField();

    public AppointmentsPage() {
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

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 15, 20));
        formPanel.setOpaque(false);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 150, 20, 150));

        addFormRow(formPanel, "Appointment ID:", appIdField);
        addFormRow(formPanel, "Patient Name:", patientNameField);
        addFormRow(formPanel, "Doctor Name:", doctorNameField);
        addFormRow(formPanel, "Date & Time:", dateTimeField);
        addFormRow(formPanel, "Department Name:", departmentField);
        addFormRow(formPanel, "Appointment Fee ($):", feeField);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        JPanel actionPanel = new JPanel(new GridLayout(2, 3, 15, 15));
        actionPanel.setOpaque(false);

        styleActionButton(scheduleButton, new Color(51, 0, 25));
        styleActionButton(rescheduleButton, new Color(51, 0, 25));
        styleActionButton(cancelButton, new Color(51, 0, 25));
        styleActionButton(admitButton, new Color(51, 0, 25));
        styleActionButton(viewAppointmentsButton, new Color(51, 0, 25));
        styleActionButton(clearAllButton, new Color(120, 10, 20));

        actionPanel.add(scheduleButton);
        actionPanel.add(rescheduleButton);
        actionPanel.add(cancelButton);
        actionPanel.add(admitButton);
        actionPanel.add(viewAppointmentsButton);
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

    private void styleActionButton(JButton button, Color bg) {
        button.setFocusable(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setForeground(Color.WHITE);
        button.setBackground(bg);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(12, 0, 12, 0));
        button.addActionListener(this);
    }

    private Appointments buildAppointmentObject() throws NumberFormatException {
        String id = appIdField.getText().trim();
        String pName = patientNameField.getText().trim();
        String dName = doctorNameField.getText().trim();
        String dt = dateTimeField.getText().trim();
        String deptName = departmentField.getText().trim();
        double fee = feeField.getText().trim().isEmpty() ? 0.0 : Double.parseDouble(feeField.getText().trim());

        Patient mockPatient = new Patient();
        mockPatient.name = pName; 
        
        Doctor mockDoctor = new Doctor();
        mockDoctor.name = dName;

        Department mockDept = new Department();
        mockDept.setDepName(deptName);
        mockDept.setLocation("Main Block");

        return new Appointments(id, mockPatient, mockDoctor, dt, mockDept, fee, "Pending", 0);
    }

    private void showAppointmentsTableWindow() {
        JFrame tableFrame = new JFrame("HMS - All Registered Appointments (Robust View)");
        tableFrame.setSize(950, 450);
        tableFrame.setLocationRelativeTo(frame);
        tableFrame.setLayout(new BorderLayout(10, 10));

        String[] columns = {"Log Record Entries / Structured Parameters"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(tableModel);
        
        table.setFont(new Font("Consolas", Font.PLAIN, 12)); 
        table.setRowHeight(26);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        table.getTableHeader().setBackground(new Color(51, 0, 25));
        table.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
        tableFrame.add(scrollPane, BorderLayout.CENTER);

        File file = new File("appointments.txt");
        if (!file.exists()) {
            JOptionPane.showMessageDialog(frame, "No appointments logged yet.", "Empty Database", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String safeLine = line.replaceAll("hospitalmanagementsystem\\.[a-zA-Z0-9]+@", "@");
                    tableModel.addRow(new Object[]{ "  " + safeLine.trim() });
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(tableFrame, "Error parsing file lines: " + ex.getMessage(), "IO Error", JOptionPane.ERROR_MESSAGE);
        }

        JLabel tipLabel = new JLabel("  Note: Data is streaming directly out of your data file block logs to prevent variable drops.");
        tipLabel.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        tableFrame.add(tipLabel, BorderLayout.SOUTH);

        tableFrame.setVisible(true);
    }

    private void clearTextFields() {
        appIdField.setText("");
        patientNameField.setText("");
        doctorNameField.setText("");
        dateTimeField.setText("");
        departmentField.setText("");
        feeField.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            frame.dispose();
            new PatientPage();
            return;
        }

        if (e.getSource() == viewAppointmentsButton) {
            showAppointmentsTableWindow();
            return;
        }

        if (e.getSource() == clearAllButton) {
            int choice = JOptionPane.showConfirmDialog(frame, "Clear ALL appointments permanently?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (choice == JOptionPane.YES_OPTION) {
                try (FileWriter fw = new FileWriter("appointments.txt", false)) {
                    fw.write(""); 
                    clearTextFields(); 
                    JOptionPane.showMessageDialog(frame, "Appointments records cleared.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "IO Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            return;
        }

        try {
            if (e.getSource() == scheduleButton) {
                if (appIdField.getText().isEmpty() || patientNameField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Appointment ID and Patient Name are required!", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Appointments app = buildAppointmentObject();
                app.scheduleAppointment();
                JOptionPane.showMessageDialog(frame, "Appointment Scheduled successfully!", "Confirmed", JOptionPane.INFORMATION_MESSAGE);
            } 
            
            else if (e.getSource() == rescheduleButton) {
                if (appIdField.getText().isEmpty() || dateTimeField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Appointment ID and New Date/Time are required!", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Appointments app = buildAppointmentObject();
                app.reschedule(dateTimeField.getText().trim());
                JOptionPane.showMessageDialog(frame, "Appointment rescheduled successfully!", "Updated", JOptionPane.INFORMATION_MESSAGE);
            } 
            
            else if (e.getSource() == cancelButton) {
                if (appIdField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Appointment ID is required to cancel!", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Appointments app = buildAppointmentObject();
                app.cancelAppointment();
                JOptionPane.showMessageDialog(frame, "Appointment Cancelled.", "Cancelled", JOptionPane.WARNING_MESSAGE);
            } 
            
            else if (e.getSource() == admitButton) {
                if (appIdField.getText().isEmpty() || patientNameField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Appointment ID and Patient Name are required for admission!", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                BedAvailability beds = new BedAvailability();
                if (beds.regularBeds <= 0) {
                    JOptionPane.showMessageDialog(frame, "Admission Halted: No regular beds available!", "Capacity Out", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Appointments app = buildAppointmentObject();
                app.admitPatient();
                beds.assignBed(false);
                JOptionPane.showMessageDialog(frame, "Patient admitted! Bed automatically linked and deducted.", "Admitted", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Please make sure 'Fee' is a numeric format.", "Format Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}