package hospitalmanagementsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class PatientPage implements ActionListener {

    JFrame frame = new JFrame("HMS - Patient Management");
    
    // Initializing buttons for the 4 options
    JButton appointmentsButton = new JButton("APPOINTMENTS");
    JButton deathTollButton = new JButton("DEATH TOLL");
    JButton dischargeButton = new JButton("DISCHARGE");
    JButton patientInfoButton = new JButton("PATIENT INFO");
    
    // Back button to return to the selection panel
    JButton backButton = new JButton("← BACK");

    public PatientPage() {
        // --- FRAME SETUP ---
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(245, 245, 245));
        frame.setSize(900, 700);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        ImageIcon im = new ImageIcon("logo.jpg");
        frame.setIconImage(im.getImage());

        // Using a main panel with BorderLayout to hold the top bar and the central grid
        JPanel mainPanel = new JPanel(new BorderLayout(0, 20));
        mainPanel.setOpaque(false);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // --- TOP NAV BAR (For Back Button) ---
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setOpaque(false);
        
        backButton.setFocusable(false);
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backButton.setForeground(new Color(51, 0, 25));
        backButton.setContentAreaFilled(false); // Keeps it looking like a clean text link/button
        backButton.setBorderPainted(false);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.addActionListener(this);
        topPanel.add(backButton);
        
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // --- GRID PANEL (2x2 for the 4 options) ---
        JPanel gridPanel = new JPanel(new GridLayout(2, 2, 25, 25));
        gridPanel.setOpaque(false);

        // --- ADDING TILES ---
        gridPanel.add(createTile("APPOINTMENTS", "appointmentIm.png", appointmentsButton));
        gridPanel.add(createTile("DEATH TOLL", "tombstoneIm.png", deathTollButton));
        gridPanel.add(createTile("DISCHARGE", "dischargeIm.png", dischargeButton));
        gridPanel.add(createTile("PATIENT INFO", "patientInfoIm.png", patientInfoButton));

        mainPanel.add(gridPanel, BorderLayout.CENTER);
        frame.add(mainPanel);

        frame.setVisible(true);
    }

    // Reuse your exact tile creation logic for UI styling consistency
    private JPanel createTile(String title, String imgPath, JButton button) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));

        // --- IMAGE SCALING ---
        ImageIcon rawIcon = new ImageIcon(imgPath);
        Image scaledImage = rawIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
        imageLabel.setHorizontalAlignment(JLabel.CENTER);

        // --- BUTTON STYLING ---
        button.setFocusable(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(51, 0, 25)); // Deep maroon matching SecondPage
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(12, 0, 12, 0)); 
        button.addActionListener(this);

        panel.add(imageLabel, BorderLayout.CENTER);
        panel.add(button, BorderLayout.SOUTH);

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle Back Button navigation
        if (e.getSource() == backButton) {
            frame.dispose();
            new SecondPage(); // Take the user back to the main selection dashboard
            return;
        }
        if (e.getSource() == appointmentsButton) {
            frame.dispose();
            new AppointmentsPage();
        } 
        else if (e.getSource() == deathTollButton) {
            frame.dispose();
            new DeathTollPage();
        } 
        else if (e.getSource() == dischargeButton) {
            frame.dispose();
            new DischargePage();
        } 
        else if (e.getSource() == patientInfoButton) {
            frame.dispose();
            new PatientInfoPage();
        }
    }
}