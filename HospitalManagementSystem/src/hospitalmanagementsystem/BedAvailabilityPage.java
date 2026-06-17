package hospitalmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BedAvailabilityPage implements ActionListener {

    JFrame frame = new JFrame("HMS - Bed Availability");
    JButton backButton = new JButton();
    JButton increaseButton = new JButton("INCREASE CAPACITY");
    JButton assignRegularButton = new JButton("ASSIGN REGULAR");
    JButton assignIcuButton = new JButton("ASSIGN ICU");
    JButton releaseRegularButton = new JButton("RELEASE REGULAR");
    JButton releaseIcuButton = new JButton("RELEASE ICU");
    JButton statusButton = new JButton("VIEW STATUS");

    JTextField totalField = new JTextField("0");
    JTextField icuField = new JTextField("0");
    JTextField regularField = new JTextField("0");

    JTextArea console = new JTextArea();
    JScrollPane scrollPane;

    BedAvailability bedLogic = new BedAvailability();

    public BedAvailabilityPage() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(255, 255, 255));
        frame.setSize(850, 750);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        
        ImageIcon im = new ImageIcon("logo.jpg");
        frame.setIconImage(im.getImage());
        frame.setLayout(null);

        ImageIcon backIcon = new ImageIcon("BackButton.png");
        Image scaledBack = backIcon.getImage().getScaledInstance(30, 25, Image.SCALE_SMOOTH);
        backButton.setIcon(new ImageIcon(scaledBack));
        backButton.setBounds(10, 10, 60, 40);
        backButton.setFocusable(false);
        backButton.setBackground(new Color(255, 255, 255));
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setBorderPainted(false);
        backButton.addActionListener(this);
        frame.add(backButton);

        JLabel title = new JLabel("BED AVAILABILITY MANAGEMENT", SwingConstants.CENTER);
        title.setBounds(125, 30, 600, 50);
        title.setForeground(new Color(91, 14, 14));
        title.setFont(new Font("Times New Roman", Font.BOLD, 36));
        frame.add(title);

        createLabel("Total Beds:", 100);
        totalField.setBounds(335, 100, 250, 35);
        totalField.setEditable(false);
        frame.add(totalField);

        createLabel("ICU Beds:", 150);
        icuField.setBounds(335, 150, 250, 35);
        icuField.setEditable(false);
        frame.add(icuField);

        createLabel("Regular Beds:", 200);
        regularField.setBounds(335, 200, 250, 35);
        regularField.setEditable(false);
        frame.add(regularField);

        int bY = 280;
        increaseButton.setBounds(275, bY, 300, 45);
        styleButton(increaseButton);
        increaseButton.addActionListener(this);
        frame.add(increaseButton);

        assignRegularButton.setBounds(100, bY + 55, 300, 45);
        styleButton(assignRegularButton);
        assignRegularButton.addActionListener(this);
        frame.add(assignRegularButton);

        assignIcuButton.setBounds(450, bY + 55, 300, 45);
        styleButton(assignIcuButton);
        assignIcuButton.addActionListener(this);
        frame.add(assignIcuButton);

        releaseRegularButton.setBounds(100, bY + 110, 300, 45);
        styleButton(releaseRegularButton);
        releaseRegularButton.addActionListener(this);
        frame.add(releaseRegularButton);

        releaseIcuButton.setBounds(450, bY + 110, 300, 45);
        styleButton(releaseIcuButton);
        releaseIcuButton.addActionListener(this);
        frame.add(releaseIcuButton);

        statusButton.setBounds(275, bY + 165, 300, 45);
        styleButton(statusButton);
        statusButton.addActionListener(this);
        frame.add(statusButton);

        console.setEditable(false);
        console.setFont(new Font("Monospaced", Font.PLAIN, 14));
        console.setBackground(new Color(240, 240, 240));
        scrollPane = new JScrollPane(console);
        scrollPane.setBounds(100, 520, 650, 180);
        scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(91, 14, 14)), "Bed Status Log"));
        frame.add(scrollPane);

        updateUIFields();
        frame.setVisible(true);
    }

    private void createLabel(String text, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(185, y, 150, 30);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setForeground(new Color(91, 14, 14));
        frame.add(label);
    }

    private void styleButton(JButton btn) {
        btn.setFocusable(false);
        btn.setFont(new Font("Arial", Font.BOLD, 16));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(91, 14, 14));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createRaisedBevelBorder());
    }

    private void updateUIFields() {
        totalField.setText(String.valueOf(bedLogic.totalBeds));
        icuField.setText(String.valueOf(bedLogic.icuBeds));
        regularField.setText(String.valueOf(bedLogic.regularBeds));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            frame.dispose();
            new SecondPage();
            return;
        }

        if (e.getSource() == increaseButton) {
            try {
                String input = JOptionPane.showInputDialog(frame, "Enter additional beds count:");
                if (input == null) return;
                int count = Integer.parseInt(input.trim());
                int choice = JOptionPane.showConfirmDialog(frame, "Is it for ICU?", "Capacity Type", JOptionPane.YES_NO_OPTION);
                boolean isIcu = (choice == JOptionPane.YES_OPTION);

                bedLogic.increaseCapacity(count, isIcu);
                updateUIFields();
                console.append("CAPACITY: Increased beds by " + count + " (ICU: " + isIcu + ")\n");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input entry.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == assignRegularButton) {
            if (bedLogic.regularBeds <= 0) {
                JOptionPane.showMessageDialog(frame, "No Regular Beds available!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            bedLogic.assignBed(false);
            updateUIFields();
            console.append("ASSIGN: Regular bed assigned.\n");
        }

        if (e.getSource() == assignIcuButton) {
            if (bedLogic.icuBeds <= 0) {
                JOptionPane.showMessageDialog(frame, "No ICU Beds available!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            bedLogic.assignBed(true);
            updateUIFields();
            console.append("ASSIGN: ICU bed assigned.\n");
        }

        if (e.getSource() == releaseRegularButton) {
            bedLogic.releaseBed(false);
            updateUIFields();
            console.append("RELEASE: Regular bed released.\n");
        }

        if (e.getSource() == releaseIcuButton) {
            bedLogic.releaseBed(true);
            updateUIFields();
            console.append("RELEASE: ICU bed released.\n");
        }

        if (e.getSource() == statusButton) {
            bedLogic.displayStatus();
            console.append("STATUS: Total active remaining available: " + bedLogic.availableBeds + "\n");
        }
    }
}