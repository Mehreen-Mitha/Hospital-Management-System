package hospitalmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPage implements ActionListener {

    // --- COMPONENTS ---
    JFrame frame = new JFrame("HMS - Login"); // creating a frame
    JButton loginButton = new JButton("LOGIN"); // initializing the login button
    
    // Added an empty string at index 0 so it starts completely blank
    String[] usernames = {"", "d.021", "f.030", "h.033", "m.051"};
    JComboBox<String> userDropdown = new JComboBox<>(usernames);
    
    JPasswordField passField = new JPasswordField(); // creating password field for security

    public LoginPage() {
        // --- FRAME ---
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // frame is completely exited
        frame.getContentPane().setBackground(new Color(255, 255, 255)); // frame background colour
        frame.setSize(800, 600); // setting frame size
        frame.setResizable(false); // doesnot allow user to manually change frame size
        frame.setLocationRelativeTo(null); // opens frame in the center of screen
        ImageIcon im = new ImageIcon("logo.jpg"); // initializing the logo image
        frame.setIconImage(im.getImage()); // setting logo next to frame title
        frame.setLayout(null); // using null layout for manual positioning

        // --- TITLE ---
        JLabel loginLabel = new JLabel("USER LOGIN", SwingConstants.CENTER); // centering text inside the label
        loginLabel.setBounds(200, 40, 400, 50); // repositioned slightly for vertical spacing
        loginLabel.setForeground(new Color(91, 14, 14)); // setting letter colour
        loginLabel.setFont(new Font("Times New Roman", Font.BOLD, 36)); // customizing the title font
        frame.add(loginLabel); // adding title to frame

        // --- WELCOME SIGN ---
        JLabel welcomeLabel = new JLabel("Welcome to HMS - Authorized Admin Terminal", SwingConstants.CENTER);
        welcomeLabel.setBounds(150, 95, 500, 30); // positioned right below the main title
        welcomeLabel.setForeground(new Color(120, 30, 30)); // slightly softer maroon shade
        welcomeLabel.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 16)); // stylish bold-italic mix
        frame.add(welcomeLabel); // adding welcome sign to frame

        // --- USERNAME ---
        JLabel uLabel = new JLabel("Username:"); // label for username input
        uLabel.setBounds(170, 190, 200, 30); // pushed down slightly to balance layout
        uLabel.setFont(new Font("Arial", Font.BOLD, 18)); // setting label font
        uLabel.setForeground(new Color(91, 14, 14)); // maroon colour
        frame.add(uLabel); // adding label to frame

        // Styling and positioning the drop-down menu component
        userDropdown.setBounds(385, 190, 250, 35); 
        userDropdown.setFont(new Font("Arial", Font.PLAIN, 16)); // setting text font inside drop-down
        userDropdown.setBackground(Color.WHITE); // white clean drop-down base background
        
        // Explicitly forces the drop-down selection to point at index 0 (the blank string) on load
        userDropdown.setSelectedIndex(0); 
        frame.add(userDropdown); // adding drop-down field to frame

        // --- PASSWORD ---
        JLabel pLabel = new JLabel("Password:"); // label for password input
        pLabel.setBounds(170, 260, 200, 30); 
        pLabel.setFont(new Font("Arial", Font.BOLD, 18)); // setting label font
        pLabel.setForeground(new Color(91, 14, 14)); // maroon colour
        frame.add(pLabel); // adding label to frame

        passField.setBounds(385, 260, 250, 35); 
        frame.add(passField); // adding password field to frame

        // --- LOGIN BUTTON ---
        loginButton.setBounds(300, 360, 200, 50); // centered
        loginButton.setFocusable(false); // getting rid of focus borders around text
        loginButton.setFont(new Font("Arial", Font.BOLD, 16)); // customizing the button font
        loginButton.setForeground(Color.WHITE); // setting button text colour
        loginButton.setBackground(new Color(91, 14, 14)); // maroon colour
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // change mouse into a hand icon
        loginButton.setBorder(BorderFactory.createRaisedBevelBorder()); // matching border style
        loginButton.addActionListener(this); // listen for clicks on button
        frame.add(loginButton); // adding button to frame

        frame.setVisible(true); // makes the window appear
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = (String) userDropdown.getSelectedItem(); 
            String password = new String(passField.getPassword()); // getting text from password field

            // 1. Validation check to make sure they didn't leave the dropdown blank
            if (username == null || username.equals("")) {
                JOptionPane.showMessageDialog(frame, "Please select a valid username choice!", "Selection Missing",
                        JOptionPane.WARNING_MESSAGE);
                return; // stops execution early
            }

            // 2. Main operational login credentials check
            if (password.equals("1234")) { 
                frame.dispose(); // closes login frame when second is opened
                new SecondPage(); // opening new window when login is successful
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid Username or Password", "Login Error",
                        JOptionPane.ERROR_MESSAGE); // error message pop up
            }
        }
    }
}