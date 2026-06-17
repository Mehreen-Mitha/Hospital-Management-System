package hospitalmanagementsystem; // defining the package name

import javax.swing.*; // importing swing components for gui
import java.awt.*; // importing awt for layout and colors
import java.awt.event.*; // importing event for handling interactions

public class RevenuePage implements ActionListener { // implementing the action listener interface

    // --- COMPONENTS --- // section for declaring gui elements
    JFrame frame = new JFrame("HMS - Monthly Revenue"); // creating the main frame for revenue management
    JButton backButton = new JButton(); // initializing the back button for navigation
    JButton calcButton = new JButton("CALCULATE & SAVE"); // creating the button to calculate revenue
    JButton reportButton = new JButton("GENERATE REPORT"); // creating the button to generate the report
    JTextField monthField = new JTextField(); // creating the text field for entering month name
    JTextField billField = new JTextField(); // creating the text field for patient bill amounts
    JTextField pharmField = new JTextField(); // creating the text field for pharmacy sales
    JTextField otherField = new JTextField(); // creating the text field for miscellaneous charges
    JLabel totalDisplay = new JLabel("Total: 0.0$", SwingConstants.CENTER); // creating the label for showing result
    JTextArea console = new JTextArea(); // creating the console text area for action logs
    JScrollPane scrollPane; // declaring a scroll pane for the console area

    MonthlyRevenue revLogic = new MonthlyRevenue(); // linking the backend logic class for revenue

    public RevenuePage() { // constructor to set up the revenue page gui
        // --- FRAME --- // section for frame configuration
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ensuring the application closes on exit
        frame.getContentPane().setBackground(new Color(255, 255, 255)); // setting white background for frame
        frame.setSize(850, 750); // setting the size of the revenue page window
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
        JLabel title = new JLabel("REVENUE MANAGEMENT", SwingConstants.CENTER); // creating the main title label
        title.setBounds(125, 30, 600, 50); // positioning the title label on the window
        title.setForeground(new Color(91, 14, 14)); // setting the text color to maroon
        title.setFont(new Font("Times New Roman", Font.BOLD, 36)); // applying bold font to the title
        frame.add(title); // adding the title label to the window

        // --- INPUT FIELDS --- // section for data entry labels and fields
        createLabel("Month Name:", 100); // creating the label for month input
        monthField.setBounds(335, 100, 250, 35); // positioning the month entry field
        monthField.setFont(new Font("Arial", Font.PLAIN, 16)); // setting font for the month field
        frame.add(monthField); // adding the month field to the frame

        createLabel("Patient Bills:", 150); // creating the label for patient bill input
        billField.setBounds(335, 150, 250, 35); // positioning the bill entry field
        billField.setFont(new Font("Arial", Font.PLAIN, 16)); // setting font for the bill field
        frame.add(billField); // adding the bill field to the frame

        createLabel("Pharmacy Sales:", 200); // creating the label for pharmacy sales input
        pharmField.setBounds(335, 200, 250, 35); // positioning the pharmacy sales field
        pharmField.setFont(new Font("Arial", Font.PLAIN, 16)); // setting font for the pharmacy field
        frame.add(pharmField); // adding the pharmacy field to the frame

        createLabel("Other Charges:", 250); // creating the label for other charges input
        otherField.setBounds(335, 250, 250, 35); // positioning the other charges field
        otherField.setFont(new Font("Arial", Font.PLAIN, 16)); // setting font for the other charges field
        frame.add(otherField); // adding the other charges field to the frame

        // --- RESULT DISPLAY --- // section for result output
        totalDisplay.setBounds(225, 300, 400, 40); // positioning the result display label
        totalDisplay.setFont(new Font("Arial", Font.BOLD, 22)); // setting font size for visibility
        totalDisplay.setForeground(new Color(91, 14, 14)); // setting maroon color for the total
        frame.add(totalDisplay); // adding the result label to the frame

        // --- BUTTONS --- // section for action button setup
        calcButton.setBounds(200, 360, 200, 45); // positioning the calculation button
        styleButton(calcButton); // applying maroon style to calculation button
        calcButton.addActionListener(this); // registering the button for clicks
        frame.add(calcButton); // adding the calculation button to the frame

        reportButton.setBounds(450, 360, 200, 45); // positioning the report generation button
        styleButton(reportButton); // applying maroon style to report button
        reportButton.addActionListener(this); // registering the button for clicks
        frame.add(reportButton); // adding the report button to the frame

        // --- CONSOLE --- // section for the console area
        console.setEditable(false); // making the console area non-editable for users
        console.setFont(new Font("Monospaced", Font.PLAIN, 14)); // setting monospace font for logs
        console.setBackground(new Color(240, 240, 240)); // setting light gray background for console
        scrollPane = new JScrollPane(console); // adding a scroll bar to the console area
        scrollPane.setBounds(100, 430, 650, 250); // positioning the console area on the frame
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
        btn.setBorder(BorderFactory.createRaisedBevelBorder()); // applying a bevel border style
    } // ending the helper method

    @Override // overriding the actionPerformed method from interface
    public void actionPerformed(ActionEvent e) { // definition of the event handler method
        if (e.getSource() == backButton) { // checking if the back button was clicked
            frame.dispose(); // closing the current window
            new SecondPage(); // returning to the selection dashboard
        } // ending back button logic

        if (e.getSource() == calcButton) { // checking if calculation button was clicked
            try { // beginning the data validation block
                  // grabbing values from input fields // description of data retrieval
                String m = monthField.getText(); // reading the month name from text field
                double bills = Double.parseDouble(billField.getText()); // parsing patient bills to double
                double pharm = Double.parseDouble(pharmField.getText()); // parsing pharmacy sales to double
                double other = Double.parseDouble(otherField.getText()); // parsing other charges to double

                // running logic from MonthlyRevenue class // description of backend sync
                revLogic.calculateRevenue(bills, pharm, other, m); // invoking backend calculation method

                // updating GUI // description of gui refresh
                double total = revLogic.totalRevenue; // accessing the public field from backend directly
                totalDisplay.setText("Total: " + total + "$"); // updating the total display label
                console.append("CALCULATED: Month: " + m + " | Total: $" + total + "\n"); // logging to gui console
                System.out.println("GUI log: Revenue calculated for " + m); // logging to ide console

                JOptionPane.showMessageDialog(frame, "Revenue calculated and saved successfully!", "Success",
                        JOptionPane.INFORMATION_MESSAGE); // success notification

            } catch (NumberFormatException ex) { // handling non-numeric input errors
                JOptionPane.showMessageDialog(frame, "Please enter valid numbers", "Input Error",
                        JOptionPane.ERROR_MESSAGE); // showing error message
            } // ending catch block
        } // ending calculation button logic

        if (e.getSource() == reportButton) { // checking if report button was clicked
            String m = monthField.getText(); // reading month name for report
            revLogic.month = m; // setting the public field in backend directly
            revLogic.generateReport(m); // invoking the report generation method
            console.append("REPORT: Monthly report generated for " + m + "\n"); // logging report action to gui
            System.out.println("GUI log: Report generated for " + m); // logging action to ide console
            JOptionPane.showMessageDialog(frame, "Report generated in console and log file!", "Info",
                    JOptionPane.INFORMATION_MESSAGE); // info popup
        } // ending report button logic
    } // ending event handler method
} // ending the RevenuePage class definition
