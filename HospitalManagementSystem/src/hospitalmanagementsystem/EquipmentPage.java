package hospitalmanagementsystem; // defining the package name

import javax.swing.*; // importing swing components for gui
import java.awt.*; // importing awt for layout and colors
import java.awt.event.*; // importing event for handling interactions

public class EquipmentPage implements ActionListener { // implementing the action listener interface

    // --- COMPONENTS --- // section for declaring gui elements
    JFrame frame = new JFrame("HMS - Equipment Management"); // creating the main frame for equipment management
    JButton backButton = new JButton(); // initializing the back button for navigation
    JButton checkButton = new JButton("CHECK EQUIPMENT"); // creating the button to perform an equipment check
    JButton repairButton = new JButton("REPAIR EQUIPMENT"); // creating the button to initiate equipment repair
    JButton updateQtyButton = new JButton("UPDATE QUANTITY"); // creating the button to adjust equipment quantity
    JButton reportButton = new JButton("STATUS REPORT"); // creating the button to display a status report

    JTextField nameField = new JTextField(); // creating the text field for entering equipment name
    JTextField qtyField = new JTextField(); // creating the text field for quantity input
    JTextField statusField = new JTextField(); // creating the text field for status description
    JTextField dateField = new JTextField(); // creating the text field for last check date
    JTextField roomField = new JTextField(); // creating the text field for room number
    JTextField modelField = new JTextField(); // creating the text field for model number
    JCheckBox priorityBox = new JCheckBox("High Priority"); // creating the checkbox for high priority flag

    JTextArea console = new JTextArea(); // creating the console text area for action logs
    JScrollPane scrollPane; // declaring a scroll pane for the console area

    Equipment equipLogic = new Equipment(); // linking the backend logic class for equipment

    public EquipmentPage() { // constructor to set up the equipment page gui
        // --- FRAME --- // section for frame configuration
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ensuring the application closes on exit
        frame.getContentPane().setBackground(new Color(255, 255, 255)); // setting white background for frame
        frame.setSize(850, 850); // setting the size of the equipment page window
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
        JLabel title = new JLabel("EQUIPMENT MANAGEMENT", SwingConstants.CENTER); // creating the main title label
        title.setBounds(125, 30, 600, 50); // positioning the title label on the window
        title.setForeground(new Color(91, 14, 14)); // setting the text color to maroon
        title.setFont(new Font("Times New Roman", Font.BOLD, 36)); // applying bold font to the title
        frame.add(title); // adding the title label to the window

        // --- INPUT FIELDS --- // section for data entry labels and fields
        int y = 100; // defining starting vertical coordinate for inputs
        int gap = 45; // defining vertical gap between input rows

        createLabel("Equip Name:", y); // creating label for equipment name input
        nameField.setBounds(335, y, 250, 30); // positioning the name field
        frame.add(nameField); // adding name field to frame

        createLabel("Quantity:", y + gap); // creating label for quantity input
        qtyField.setBounds(335, y + gap, 250, 30); // positioning the quantity field
        frame.add(qtyField); // adding quantity field to frame

        createLabel("Status:", y + gap * 2); // creating label for status input
        statusField.setBounds(335, y + gap * 2, 250, 30); // positioning the status field
        frame.add(statusField); // adding status field to frame

        createLabel("Last Check:", y + gap * 3); // creating label for date input
        dateField.setBounds(335, y + gap * 3, 250, 30); // positioning the date field
        frame.add(dateField); // adding date field to frame

        createLabel("Room No:", y + gap * 4); // creating label for room number input
        roomField.setBounds(335, y + gap * 4, 250, 30); // positioning the room field
        frame.add(roomField); // adding room field to frame

        createLabel("Model No:", y + gap * 5); // creating label for model number input
        modelField.setBounds(335, y + gap * 5, 250, 30); // positioning the model field
        frame.add(modelField); // adding model field to frame

        priorityBox.setBounds(335, y + gap * 6, 150, 30); // positioning the high priority checkbox
        priorityBox.setBackground(Color.WHITE); // setting white background for checkbox
        priorityBox.setFont(new Font("Arial", Font.BOLD, 14)); // setting font for checkbox label
        priorityBox.setForeground(new Color(91, 14, 14)); // applying maroon color to checkbox label
        frame.add(priorityBox); // adding checkbox to frame

        // --- BUTTONS --- // section for action button setup
        int bY = 440; // defining vertical coordinate for action buttons
        checkButton.setBounds(100, bY, 300, 45); // positioning equipment check button
        styleButton(checkButton); // applying maroon style to check button
        checkButton.addActionListener(this); // registering button for click events
        frame.add(checkButton); // adding check button to frame

        repairButton.setBounds(450, bY, 300, 45); // positioning repair button
        styleButton(repairButton); // applying maroon style to repair button
        repairButton.addActionListener(this); // registering button for click events
        frame.add(repairButton); // adding repair button to frame

        updateQtyButton.setBounds(100, bY + 55, 300, 45); // positioning quantity update button
        styleButton(updateQtyButton); // applying maroon style to update button
        updateQtyButton.addActionListener(this); // registering button for click events
        frame.add(updateQtyButton); // adding update button to frame

        reportButton.setBounds(450, bY + 55, 300, 45); // positioning status report button
        styleButton(reportButton); // applying maroon style to report button
        reportButton.addActionListener(this); // registering button for click events
        frame.add(reportButton); // adding report button to frame

        // --- CONSOLE --- // section for the console area
        console.setEditable(false); // making console area non-editable for users
        console.setFont(new Font("Monospaced", Font.PLAIN, 14)); // setting monospace font for logs
        console.setBackground(new Color(240, 240, 240)); // setting light gray background for console
        scrollPane = new JScrollPane(console); // adding scroll bar to console area
        scrollPane.setBounds(100, 560, 650, 220); // positioning console area on the frame
        scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(91, 14, 14)),
                "Maintenance Log")); // adding titled border
        frame.add(scrollPane); // adding console container to frame

        frame.setVisible(true); // making entire window visible
    } // ending the constructor method

    // helper method to create labels with consistent style // description of helper
    private void createLabel(String text, int y) { // definition of createLabel helper method
        JLabel label = new JLabel(text); // creating a label with given text
        label.setBounds(185, y, 150, 30); // setting fixed horizontal alignment for labels
        label.setFont(new Font("Arial", Font.BOLD, 18)); // setting standard font for labels
        label.setForeground(new Color(91, 14, 14)); // applying maroon theme color
        frame.add(label); // adding created label to frame
    } // ending helper method

    // helper method to style buttons // description of helper
    private void styleButton(JButton btn) { // definition of styleButton helper method
        btn.setFocusable(false); // removing focus indicator from button
        btn.setFont(new Font("Arial", Font.BOLD, 16)); // setting standard font for buttons
        btn.setForeground(Color.WHITE); // setting text color to white for contrast
        btn.setBackground(new Color(91, 14, 14)); // applying maroon color to background
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR)); // changing cursor style on hover
        btn.setBorder(BorderFactory.createRaisedBevelBorder()); // applying bevel border style
    } // ending helper method

    // method to synchronize logic state with gui fields // description of helper
    private void syncLogic() { // definition of syncLogic method
        try { // beginning data validation block
            equipLogic.setEquipmentName(nameField.getText()); // setting equipment name in backend
            equipLogic.setQuantity(Integer.parseInt(qtyField.getText())); // setting quantity in backend
            equipLogic.setStatus(statusField.getText()); // setting status in backend
            equipLogic.setLastCheckedDate(dateField.getText()); // setting date in backend
            equipLogic.setRoomNumber(roomField.getText()); // setting room number in backend
            equipLogic.setModelNumber(modelField.getText()); // setting model number in backend
            equipLogic.setIsHighPriority(priorityBox.isSelected()); // setting priority in backend
        } catch (NumberFormatException ex) { // handling non-numeric quantity errors
            JOptionPane.showMessageDialog(frame, "Please enter valid numeric quantity", "Input Error",
                    JOptionPane.ERROR_MESSAGE); // showing error message
        } // ending catch block
    } // ending sync method

    @Override // overriding actionPerformed method from interface
    public void actionPerformed(ActionEvent e) { // definition of event handler method
        if (e.getSource() == backButton) { // checking if back button was clicked
            frame.dispose(); // closing current window
            new SecondPage(); // returning to selection dashboard
        } // ending back button logic

        if (e.getSource() == checkButton) { // checking if equipment check button was clicked
            syncLogic(); // syncing gui data to backend object
            equipLogic.checkEquipment(); // invoking backend check method
            console.append("CHECK: Maintenance check for " + equipLogic.getEquipmentName() + " logged.\n"); // logging
                                                                                                            // action to
                                                                                                            // gui
                                                                                                            // console
            System.out.println("GUI log: Checked equipment " + equipLogic.getEquipmentName()); // logging to ide console
        } // ending check logic

        if (e.getSource() == repairButton) { // checking if repair button was clicked
            syncLogic(); // syncing gui data to backend object
            equipLogic.repairEquipment(); // invoking backend repair method
            statusField.setText(equipLogic.getStatus()); // updating gui status field from backend
            console.append("REPAIR: Repair initiated for " + equipLogic.getEquipmentName() + ". New Status: "
                    + equipLogic.getStatus() + "\n"); // logging to gui console
            System.out.println("GUI log: Repaired equipment " + equipLogic.getEquipmentName()); // logging to ide
                                                                                                // console
        } // ending repair logic

        if (e.getSource() == updateQtyButton) { // checking if quantity update button was clicked
            try { // beginning validation block for adjustment count
                String input = JOptionPane.showInputDialog(frame, "Enter amount to adjust (negative to remove):"); // prompting
                                                                                                                   // for
                                                                                                                   // amount
                int adj = Integer.parseInt(input); // parsing adjustment amount to integer
                equipLogic.setEquipmentName(nameField.getText()); // setting name for context
                equipLogic.setQuantity(Integer.parseInt(qtyField.getText())); // setting current quantity
                equipLogic.updateQuantity(adj); // invoking backend quantity adjustment method
                qtyField.setText(String.valueOf(equipLogic.getQuantity())); // refreshing gui quantity field
                console.append("QUANTITY: Adjusted " + equipLogic.getEquipmentName() + " by " + adj + ". Total: "
                        + equipLogic.getQuantity() + "\n"); // logging to gui console
                System.out.println("GUI log: Updated quantity for " + equipLogic.getEquipmentName()); // logging to ide
                                                                                                      // console
            } catch (Exception ex) { // handling input errors
                JOptionPane.showMessageDialog(frame, "Invalid input for adjustment", "Error",
                        JOptionPane.ERROR_MESSAGE); // showing error message
            } // ending catch block
        } // ending quantity update logic

        if (e.getSource() == reportButton) { // checking if status report button was clicked
            syncLogic(); // syncing gui data to backend object
            equipLogic.displayStatusReport(); // invoking backend report method
            console.append("REPORT: Status report generated in console.\n"); // logging action to gui console
            System.out.println("GUI log: Viewing status report for " + equipLogic.getEquipmentName()); // logging action
                                                                                                       // to ide console
        } // ending report logic
    } // ending event handler method
} // ending the EquipmentPage class definition
