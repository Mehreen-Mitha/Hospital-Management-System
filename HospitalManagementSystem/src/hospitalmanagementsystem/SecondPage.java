package hospitalmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SecondPage implements ActionListener {

    JFrame frame = new JFrame("HMS - Selection Panel");
    
    // Initializing buttons
    JButton patientButton = new JButton("PATIENT");
    JButton nurseButton = new JButton("NURSE");
    JButton doctorButton = new JButton("DOCTOR");
    JButton departmentButton = new JButton("DEPARTMENTS");
    JButton ambulanceSerButton = new JButton("AMBULANCE SERVICE");
    JButton equipmentButton = new JButton("EQUIPMENT");
    JButton pharmacyButton = new JButton("PHARMACY");
    JButton bedAvaButton = new JButton("BED AVAILABILITY");
    JButton monthlyRevButton = new JButton("MONTHLY REVENUE");

    public SecondPage() {
    // --- FRAME SETUP ---
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(245, 245, 245));
        frame.setSize(900, 700);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        ImageIcon im = new ImageIcon("logo.jpg");
        frame.setIconImage(im.getImage());

        //grid layout with 20 pixel gaps
        frame.setLayout(new GridLayout(3, 3, 20, 20));
        
        //adding padding around the grid so that tiles dont touch the window edges
        ((JPanel)frame.getContentPane()).setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    // --- ADDING TILES ---
        frame.add(createTile("PATIENT", "patientIm.png", patientButton));
        frame.add(createTile("NURSE", "nurseIm.png", nurseButton));
        frame.add(createTile("DOCTOR", "doctorIm.png", doctorButton));
        frame.add(createTile("DEPARTMENTS", "depIm.png", departmentButton));
        frame.add(createTile("AMBULANCE SER", "ambulanceIm.png", ambulanceSerButton));
        frame.add(createTile("EQUIPMENT", "equipIm.png", equipmentButton));
        frame.add(createTile("PHARMACY", "medicineIm.png", pharmacyButton));
        frame.add(createTile("BED AVAILABILITY", "bedIm.png", bedAvaButton));
        frame.add(createTile("MONTHLY REVENUE", "revenueIm.png", monthlyRevButton));

        frame.setVisible(true);
    }

    //method to create tiles
    private JPanel createTile(String title, String imgPath, JButton button) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1)); //subtle borders

    // --- IMAGE SCALING ---
        ImageIcon rawIcon = new ImageIcon(imgPath);
        Image scaledImage = rawIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
        imageLabel.setHorizontalAlignment(JLabel.CENTER);

    // --- BUTTON STYLING ---
        button.setFocusable(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(51, 0, 25));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); //padding inside button
        button.addActionListener(this);

        panel.add(imageLabel, BorderLayout.CENTER);
        panel.add(button, BorderLayout.SOUTH);

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.dispose();
        //logic for button clicks
        if (e.getSource() == patientButton) {
            new PatientPage();  
        }
        else if (e.getSource() == nurseButton) {
            new NursePage();
        }
        else if (e.getSource() == doctorButton) {
            new DoctorPage();
        }
        else if (e.getSource() == departmentButton) {
            new DepartmentPage();
        }
        else if (e.getSource() == ambulanceSerButton) {
            new AmbulancePage();
        }
        else if (e.getSource() == equipmentButton) {
            new EquipmentPage();
        }
        else if (e.getSource() == pharmacyButton) {
            new PharmacyPage();
        }
        else if (e.getSource() == bedAvaButton) {
            new BedAvailabilityPage();
        }
        else if (e.getSource() == monthlyRevButton) {
            new RevenuePage();
        }
    }
}