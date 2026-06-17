package hospitalmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FrontAccessPage implements ActionListener {

    JFrame frame = new JFrame("HMS"); // creating a frame
    JButton a = new JButton("ACCESS");

    public FrontAccessPage() {
        // --- FRAME ---
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // frame is completely exited
        frame.getContentPane().setBackground(new Color(255, 255, 255)); // frame background colour
        frame.setSize(800, 600); // setting frame size
        frame.setResizable(false); // doesnot allow user to manually change frame size
        frame.setLocationRelativeTo(null); // opens frame in the center of screen
        ImageIcon im = new ImageIcon("logo.jpg"); // initializing the logo image
        frame.setIconImage(im.getImage()); // setting logo next to frame title

        /*
         * borderLayout divides the screen into North, South, East, West, and Center
         * we are adding a 20 pixel vertical gap between these areas
         */
        frame.setLayout(new BorderLayout(0, 20));

        // --- TITLE ---
        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false); // panel is transparent so that background colour is consistent
        titlePanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0)); // create invisible padding; 40 pixels top,
                                                                            // 0 left, 0 bottom, 0 right
        JLabel titleLabel = new JLabel("HOSPITAL MANAGEMENT SYSTEM"); // adding a label containing title
        titleLabel.setForeground(new Color(91, 14, 14)); // setting letter colour
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 36)); // customizing the title
        titlePanel.add(titleLabel);

        // --- LOGO ---
        Image scaledImage = im.getImage().getScaledInstance(350, 350, Image.SCALE_SMOOTH); // scaling logo image to a
                                                                                           // specific size
        ImageIcon finalLogo = new ImageIcon(scaledImage);
        JLabel logoLabel = new JLabel(finalLogo);
        logoLabel.setHorizontalAlignment(JLabel.CENTER);

        // --- ACCESS BUTTON ---
        // using FlowLayout.CENTER to keep the button in the middle and prevent it from
        // stretching
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 0)); // bottom padding
        a.setPreferredSize(new Dimension(200, 50)); // width and height specified
        a.setFocusable(false); // getting rid of focus borders around text
        a.setFont(new Font("Arial", Font.BOLD, 16));
        a.setForeground(Color.WHITE);
        a.setBackground(new Color(91, 14, 14));
        a.setCursor(new Cursor(Cursor.HAND_CURSOR)); // change mouse into a hand icon when hovering over the button
        a.setBorder(BorderFactory.createRaisedBevelBorder()); // removes defualt borders on the botton

        a.addActionListener(this); // listen for clicks on button
        buttonPanel.add(a);

        // --- COMPONENTS ---
        // adding components to specific regions
        frame.add(titlePanel, BorderLayout.NORTH); // title placed on the top
        frame.add(logoLabel, BorderLayout.CENTER); // logo placed in the center
        frame.add(buttonPanel, BorderLayout.SOUTH); // button placed on the bottom

        frame.setVisible(true); // makes the window appear
    }

    // --- NEW WINDOW ---
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == a) {
            // opening new window when button is clicked
            frame.dispose(); // closes first frame when second is opened
            LoginPage LP = new LoginPage(); // new window
        }
    }
}