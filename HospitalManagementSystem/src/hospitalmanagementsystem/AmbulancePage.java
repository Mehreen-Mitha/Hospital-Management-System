package hospitalmanagementsystem; // defining the package name

import javax.swing.*; // importing swing components for gui
import java.awt.*; // importing awt for layout and colors
import java.awt.event.*; // importing event for handling interactions

public class AmbulancePage implements ActionListener { // implementing the action listener interface

    // --- COMPONENTS --- // section for declaring gui elements
    JFrame frame = new JFrame("HMS - Ambulance Service"); // creating the main frame for ambulance service management
    JButton backButton = new JButton(); // initializing the back button for navigation
    JButton availableButton = new JButton("MARK AVAILABLE"); // creating the button to mark ambulance as available
    JButton dispatchButton = new JButton("DISPATCH AMBULANCE"); // creating the button to dispatch ambulance
    JButton displayButton = new JButton("VIEW LOGS"); // creating the button to view service logs in console

    JTextField idField = new JTextField(); // creating the text field for entering ambulance id
    JTextField driverField = new JTextField(); // creating the text field for driver name
    JTextField locField = new JTextField(); // creating the text field for destination location
    JTextField levelField = new JTextField(); // creating the text field for emergency level

    JTextArea console = new JTextArea(); // creating the console text area for action logs
    JScrollPane scrollPane; // declaring a scroll pane for the console area

    AmbulanceService ambLogic = new AmbulanceService(); // linking the backend logic class for ambulance service

    public AmbulancePage() { // constructor to set up the ambulance page gui
        // --- FRAME --- // section for frame configuration
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ensuring the application closes on exit
        frame.getContentPane().setBackground(new Color(255, 255, 255)); // setting white background for frame
        frame.setSize(850, 750); // setting the size of the ambulance page window
        frame.setResizable(false); // making the frame window size non-adjustable
        frame.setLocationRelativeTo(null); // positioning the frame at the center of the screen
        ImageIcon im = new ImageIcon("logo.jpg"); // loading the hospital logo icon
        frame.setIconImage(im.getImage()); // applying the icon to the frame window
        frame.setLayout(null); // using absolute positioning for all components

        // --- BACK BUTTON --- // section for back button setup
        ImageIcon backIcon = new ImageIcon("BackButton.png"); // loading the back button image
        Image scaledBack = backIcon.getImage().getScaledInstance(30, 25, Image.SCALE_SMOOTH); // scaling the image
        backButton.setIcon(new ImageIcon(scaledBack)); // applying the icon to the button
        backButton.setBounds(10, 10, 60, 40); // setting the bounds for the back button
        backButton.setFocusable(false); // disabling the focus border on the button
        backButton.setBackground(new Color(255, 255, 255)); // setting the button background to white
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // changing cursor to hand on hover
        backButton.setBorderPainted(false); // hiding the default button border
        backButton.addActionListener(this); // registering the button for click events
        frame.add(backButton); // adding the back button to the frame

        // --- TITLE --- // section for the title label
        JLabel title = new JLabel("AMBULANCE SERVICE", SwingConstants.CENTER); // creating the main title label
        title.setBounds(125, 30, 600, 50); // positioning the title label on the window
        title.setForeground(new Color(91, 14, 14)); // setting the text color to maroon
        title.setFont(new Font("Times New Roman", Font.BOLD, 36)); // applying bold font to the title
        frame.add(title); // adding the title label to the window

        // --- INPUT FIELDS --- // section for data entry labels and fields
        createLabel("Ambulance ID:", 100); // creating the label for ambulance id input
        idField.setBounds(385, 100, 250, 35); // adjusted X boundary from 335 to 385 to fit layout positioning cleanly
        frame.add(idField); // adding the id field to the frame

        createLabel("Driver Name:", 150); // creating the label for driver name input
        driverField.setBounds(385, 150, 250, 35); // adjusted X boundary to fit layout positioning cleanly
        frame.add(driverField); // adding the driver name field to the frame

        createLabel("Destination:", 200); // creating the label for destination input
        locField.setBounds(385, 200, 250, 35); // adjusted X boundary to fit layout positioning cleanly
        frame.add(locField); // adding the destination field to the frame

        createLabel("Emergency Level:", 250); // creating the label for emergency level input
        levelField.setBounds(385, 250, 250, 35); // adjusted X boundary to fit layout positioning cleanly
        frame.add(levelField); // adding the emergency level field to the frame

        // --- BUTTONS --- // section for action button setup
        availableButton.setBounds(100, 330, 300, 45); // positioning the mark available button
        styleButton(availableButton); // applying maroon style to available button
        availableButton.addActionListener(this); // registering the button for clicks
        frame.add(availableButton); // adding the available button to the frame

        dispatchButton.setBounds(450, 330, 300, 45); // positioning the dispatch button
        styleButton(dispatchButton); // applying maroon style to dispatch button
        dispatchButton.addActionListener(this); // registering the button for clicks
        frame.add(dispatchButton); // adding the dispatch button to the frame

        displayButton.setBounds(275, 390, 300, 45); // positioning the log viewing button
        styleButton(displayButton); // applying maroon style to display button
        displayButton.addActionListener(this); // registering the button for clicks
        frame.add(displayButton); // adding the display button to the frame

        // --- CONSOLE --- // section for the console area
        console.setEditable(false); // making the console area non-editable for users
        console.setFont(new Font("Monospaced", Font.PLAIN, 14)); // setting monospace font for logs
        console.setBackground(new Color(240, 240, 240)); // setting light gray background for console
        scrollPane = new JScrollPane(console); // adding a scroll bar to the console area
        scrollPane.setBounds(100, 460, 650, 220); // positioning the console area on the frame
        scrollPane.setBorder(
                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(91, 14, 14)), "Service Log")); // adding titled border
        frame.add(scrollPane); // adding the console container to the frame

        frame.setVisible(true); // making the entire window visible
    } // ending the constructor method

    // helper method to create labels with consistent style
    private void createLabel(String text, int y) { // definition of createLabel helper method
        JLabel label = new JLabel(text); // creating a label with given text
        // WIDENED boundary width from 150 to 200 to prevent text from cutting off!
        label.setBounds(170, y, 200, 30); 
        label.setFont(new Font("Arial", Font.BOLD, 18)); // setting the standard font for labels
        label.setForeground(new Color(91, 14, 14)); // applying the maroon theme color
        frame.add(label); // adding the created label to the frame
    } // ending the helper method

    // helper method to style buttons
    private void styleButton(JButton btn) { // definition of styleButton helper method
        btn.setFocusable(false); // removing the focus indicator from the button
        btn.setFont(new Font("Arial", Font.BOLD, 16)); // setting the standard font for buttons
        btn.setForeground(Color.WHITE); // setting text color to white for contrast
        btn.setBackground(new Color(91, 14, 14)); // applying maroon color to button background
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR)); // changing cursor style on hover
        btn.setBorder(BorderFactory.createRaisedBevelBorder()); // applying a raised border style
    } // ending the helper method

    // method to synchronize logic state with gui fields
    private void syncLogic() { // definition of syncLogic method
        try { // beginning the data validation block
            ambLogic.setID(Integer.parseInt(idField.getText().trim())); // setting ambulance id in backend
            ambLogic.setDriverName(driverField.getText().trim()); // setting driver name in backend
        } catch (NumberFormatException ex) { // handling non-numeric input errors
            JOptionPane.showMessageDialog(frame, "Please enter a valid numeric ID", "Input Error",
                    JOptionPane.ERROR_MESSAGE); // showing error message
        } // ending catch block
    } // ending synchronization method

    @Override // overriding the actionPerformed method from interface
    public void actionPerformed(ActionEvent e) { // definition of the event handler method
        if (e.getSource() == backButton) { // checking if the back button was clicked
            frame.dispose(); // closing the current window
            new SecondPage(); // returning to the selection dashboard
        } // ending back button logic

        if (e.getSource() == availableButton) { // checking if mark available button was clicked
            syncLogic(); // syncing gui data to backend object
            ambLogic.markAvailable(); // invoking backend method to mark as available
            console.append("STATUS: Ambulance " + ambLogic.getID() + " marked Available.\n"); // logging action to gui console
            System.out.println("GUI log: Ambulance " + ambLogic.getID() + " is available."); // logging to ide console
        } // ending available status logic

        if (e.getSource() == dispatchButton) { // checking if dispatch button was clicked
            syncLogic(); // syncing gui data to backend object
            String destination = locField.getText().trim(); // reading destination from text field
            String level = levelField.getText().trim(); // reading emergency level from text field
            ambLogic.dispatchAmbulance(destination, level); // invoking backend dispatch method
            console.append("DISPATCH: Ambulance " + ambLogic.getID() + " sent to " + destination + " (Level: " + level + ")\n"); // logging dispatch to gui
            System.out.println("GUI log: Dispatched ambulance " + ambLogic.getID() + " to " + destination); // logging to ide console
        } // ending dispatch logic

        if (e.getSource() == displayButton) { // checking if view logs button was clicked
            ambLogic.display(); // invoking backend method to display service logs
            console.append("LOGS: Displayed logs in IDE console.\n"); // logging action to gui console
            System.out.println("GUI log: Viewing ambulance service logs."); // logging to ide console
        } // ending log viewing logic
    } // ending event handler method
} // ending the AmbulancePage class definition