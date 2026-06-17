package hospitalmanagementsystem; // defining the package name

import javax.swing.*; // importing swing components for gui
import java.awt.*; // importing awt for layout and colors
import java.awt.event.*; // importing event for handling interactions

public class DepartmentPage implements ActionListener { // implementing the action listener interface

    // --- COMPONENTS --- // section for declaring gui elements
    JFrame frame = new JFrame("HMS - Department Management"); // creating the main frame for department management
    JButton backButton = new JButton(); // initializing the back button for navigation
    JButton capacityButton = new JButton("CHECK CAPACITY"); // creating the button to check department capacity
    JButton doctorStaffButton = new JButton("CHECK DOCTORS"); // creating the button to check doctor staffing
    JButton nurseStaffButton = new JButton("CHECK NURSES"); // creating the button to check nurse staffing
    JButton displayButton = new JButton("DISPLAY INFO"); // creating the button to display department information

    JTextField nameField = new JTextField(); // creating the text field for entering department name
    JTextField headField = new JTextField(); // creating the text field for head of department name
    JTextField locField = new JTextField(); // creating the text field for department location
    JTextField docField = new JTextField(); // creating the text field for number of doctors
    JTextField nurseField = new JTextField(); // creating the text field for number of nurses
    JTextField patientField = new JTextField(); // creating the text field for number of patients

    JTextArea console = new JTextArea(); // creating the console text area for action logs
    JScrollPane scrollPane; // declaring a scroll pane for the console area

    Department depLogic = new Department(); // linking the backend logic class for departments

    public DepartmentPage() { // constructor to set up the department page gui
        // --- FRAME --- // section for frame configuration
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ensuring the application closes on exit
        frame.getContentPane().setBackground(new Color(255, 255, 255)); // setting white background for frame
        frame.setSize(850, 800); // setting the size of the department page window
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
        JLabel title = new JLabel("DEPARTMENT MANAGEMENT", SwingConstants.CENTER); // creating the main title label
        title.setBounds(125, 30, 600, 50); // positioning the title label on the window
        title.setForeground(new Color(91, 14, 14)); // setting the text color to maroon
        title.setFont(new Font("Times New Roman", Font.BOLD, 36)); // applying bold font to the title
        frame.add(title); // adding the title label to the window

        // --- INPUT FIELDS --- // section for data entry labels and fields
        int yStart = 100; // defining the starting vertical position for labels
        int yGap = 50; // defining the vertical gap between input rows

        createLabel("Dept Name:", yStart); // creating the label for department name input
        nameField.setBounds(335, yStart, 250, 35); // positioning the department name entry field
        frame.add(nameField); // adding the name field to the frame

        createLabel("Head of Dept:", yStart + yGap); // creating the label for head of department input
        headField.setBounds(335, yStart + yGap, 250, 35); // positioning the head of department field
        frame.add(headField); // adding the head field to the frame

        createLabel("Location:", yStart + yGap * 2); // creating the label for location input
        locField.setBounds(335, yStart + yGap * 2, 250, 35); // positioning the location entry field
        frame.add(locField); // adding the location field to the frame

        createLabel("Doctors Count:", yStart + yGap * 3); // creating the label for doctor count input
        docField.setBounds(335, yStart + yGap * 3, 250, 35); // positioning the doctor count field
        frame.add(docField); // adding the doctor field to the frame

        createLabel("Nurses Count:", yStart + yGap * 4); // creating the label for nurse count input
        nurseField.setBounds(335, yStart + yGap * 4, 250, 35); // positioning the nurse count field
        frame.add(nurseField); // adding the nurse field to the frame

        createLabel("Patients Count:", yStart + yGap * 5); // creating the label for patient count input
        patientField.setBounds(335, yStart + yGap * 5, 250, 35); // positioning the patient count field
        frame.add(patientField); // adding the patient field to the frame

        // --- BUTTONS --- // section for action button setup
        int bY = 420; // defining the starting vertical position for buttons
        capacityButton.setBounds(100, bY, 300, 45); // positioning the capacity check button
        styleButton(capacityButton); // applying maroon style to capacity button
        capacityButton.addActionListener(this); // registering the button for clicks
        frame.add(capacityButton); // adding the capacity button to the frame

        displayButton.setBounds(450, bY, 300, 45); // positioning the info display button
        styleButton(displayButton); // applying maroon style to display button
        displayButton.addActionListener(this); // registering the button for clicks
        frame.add(displayButton); // adding the display button to the frame

        doctorStaffButton.setBounds(100, bY + 55, 300, 45); // positioning the doctor staffing check button
        styleButton(doctorStaffButton); // applying maroon style to doctor staffing button
        doctorStaffButton.addActionListener(this); // registering the button for clicks
        frame.add(doctorStaffButton); // adding the doctor staffing button to the frame

        nurseStaffButton.setBounds(450, bY + 55, 300, 45); // positioning the nurse staffing check button
        styleButton(nurseStaffButton); // applying maroon style to nurse staffing button
        nurseStaffButton.addActionListener(this); // registering the button for clicks
        frame.add(nurseStaffButton); // adding the nurse staffing button to the frame

        // --- CONSOLE --- // section for the console area
        console.setEditable(false); // making the console area non-editable for users
        console.setFont(new Font("Monospaced", Font.PLAIN, 14)); // setting monospace font for logs
        console.setBackground(new Color(240, 240, 240)); // setting light gray background for console
        scrollPane = new JScrollPane(console); // adding a scroll bar to the console area
        scrollPane.setBounds(100, 530, 650, 200); // positioning the console area on the frame
        scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(91, 14, 14)),
                "Real-Time Log")); // adding titled border
        frame.add(scrollPane); // adding the console container to the frame

        frame.setVisible(true); // making the entire window visible
    } // ending the constructor method

    // helper method to create labels with consistent style // description of helper
    private void createLabel(String text, int y) { // definition of createLabel helper method
        JLabel label = new JLabel(text); // creating a label with given text
        label.setBounds(185, y, 150, 30); // setting fixed horizontal alignment for labels
        label.setFont(new Font("Arial", Font.BOLD, 18)); // setting the standard font for labels
        label.setForeground(new Color(91, 14, 14)); // applying the maroon theme color
        frame.add(label); // adding the created label to the frame
    } // ending the helper method

    // helper method to style buttons // description of helper
    private void styleButton(JButton btn) { // definition of styleButton helper method
        btn.setFocusable(false); // removing the focus indicator from the button
        btn.setFont(new Font("Arial", Font.BOLD, 16)); // setting the standard font for buttons
        btn.setForeground(Color.WHITE); // setting text color to white for contrast
        btn.setBackground(new Color(91, 14, 14)); // applying maroon color to button background
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR)); // changing cursor style on hover
        btn.setBorder(BorderFactory.createRaisedBevelBorder()); // applying a raised border style
    } // ending the helper method

    // method to synchronize logic state with gui fields // description of helper
    private void syncLogic() { // definition of syncLogic method
        try { // beginning the data validation block
            depLogic.setDepName(nameField.getText()); // setting department name in backend
            depLogic.setHeadOfDep(headField.getText()); // setting head of department in backend
            depLogic.setLocation(locField.getText()); // setting department location in backend
            depLogic.setNumberOfDoctors(Integer.parseInt(docField.getText())); // setting doctor count in backend
            depLogic.setNumberOfNurses(Integer.parseInt(nurseField.getText())); // setting nurse count in backend
            depLogic.setNumberOfPatients(Integer.parseInt(patientField.getText())); // setting patient count in backend
        } catch (NumberFormatException ex) { // handling non-numeric input errors
            JOptionPane.showMessageDialog(frame, "Please enter valid numeric values for staff and patients",
                    "Input Error", JOptionPane.ERROR_MESSAGE); // showing error message
        } // ending catch block
    } // ending synchronization method

    @Override // overriding the actionPerformed method from interface
    public void actionPerformed(ActionEvent e) { // definition of the event handler method
        if (e.getSource() == backButton) { // checking if the back button was clicked
            frame.dispose(); // closing the current window
            new SecondPage(); // returning to the selection dashboard
        } // ending back button logic

        if (e.getSource() == capacityButton) { // checking if capacity check button was clicked
            syncLogic(); // syncing gui data to backend object
            depLogic.checkDepCapacity(); // invoking backend capacity check method
            console.append("CHECK: Capacity checked for " + depLogic.getDepName() + "\n"); // logging action to gui
                                                                                           // console
            System.out.println("GUI log: Checked capacity for " + depLogic.getDepName()); // logging to ide console
        } // ending capacity check logic

        if (e.getSource() == doctorStaffButton) { // checking if doctor staffing button was clicked
            syncLogic(); // syncing gui data to backend object
            depLogic.checkUnderstaffedDoctors(); // invoking backend staffing check for doctors
            console.append("CHECK: Doctor staffing checked for " + depLogic.getDepName() + "\n"); // logging action to
                                                                                                  // gui console
            System.out.println("GUI log: Checked doctor staffing for " + depLogic.getDepName()); // logging to ide
                                                                                                 // console
        } // ending doctor staffing check logic

        if (e.getSource() == nurseStaffButton) { // checking if nurse staffing button was clicked
            syncLogic(); // syncing gui data to backend object
            depLogic.checkUnderstaffedNurses(); // invoking backend staffing check for nurses
            console.append("CHECK: Nurse staffing checked for " + depLogic.getDepName() + "\n"); // logging action to
                                                                                                 // gui console
            System.out.println("GUI log: Checked nurse staffing for " + depLogic.getDepName()); // logging to ide
                                                                                                // console
        } // ending nurse staffing check logic

        if (e.getSource() == displayButton) { // checking if info display button was clicked
            syncLogic(); // syncing gui data to backend object
            depLogic.displayInfo(); // invoking backend info display method
            console.append("DISPLAY: Information logged to file and console.\n"); // logging display action to gui
            System.out.println("GUI log: Displayed info for " + depLogic.getDepName()); // logging action to ide console
        } // ending info display logic
    } // ending event handler method
} // ending the DepartmentPage class definition
