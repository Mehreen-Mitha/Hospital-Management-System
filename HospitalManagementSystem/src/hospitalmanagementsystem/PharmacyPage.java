package hospitalmanagementsystem; // defining the package name

import javax.swing.*; // importing swing for gui components
import java.awt.*; // importing awt for layout and colors
import java.awt.event.*; // importing event for button clicks

public class PharmacyPage implements ActionListener { // implementing the action listener interface

    // --- COMPONENTS --- // section for declaring components
    JFrame frame = new JFrame("HMS - Pharmacy Management"); // creating a frame for the pharmacy page
    JButton backButton = new JButton(); // initializing the back button for navigation
    JButton updateButton = new JButton("UPDATE STOCK"); // creating a button to update medicine stock
    JButton sellButton = new JButton("SELL MEDICINE"); // creating a button to sell medicine from stock
    JTextField nameField = new JTextField(); // creating a text field for the medicine name
    JTextField qtyField = new JTextField(); // creating a text field for the quantity
    JTextField priceField = new JTextField(); // creating a text field for the price
    JTextField supplierField = new JTextField(); // creating a text field for the supplier name
    JTextArea console = new JTextArea(); // creating a console text area for logs
    JScrollPane scrollPane; // creating a scroll pane to hold the console

    Pharmacy pharLogic = new Pharmacy(); // linking the backend pharmacy logic class

    public PharmacyPage() { // constructor to initialize the gui
        // --- FRAME --- // section for frame setup
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ensuring the frame exits on close
        frame.getContentPane().setBackground(new Color(255, 255, 255)); // setting white background color
        frame.setSize(850, 700); // setting the window size of the frame
        frame.setResizable(false); // making the frame non-resizable by the user
        frame.setLocationRelativeTo(null); // centering the frame on the screen
        ImageIcon im = new ImageIcon("logo.jpg"); // loading the logo image icon
        frame.setIconImage(im.getImage()); // setting the frame icon to the loaded logo
        frame.setLayout(null); // using null layout for manual component positioning

        // --- BACK BUTTON --- // section for back button setup
        ImageIcon backIcon = new ImageIcon("BackButton.png"); // loading the back button image icon
        Image scaledBack = backIcon.getImage().getScaledInstance(30, 25, Image.SCALE_SMOOTH); // scaling the icon
        backButton.setIcon(new ImageIcon(scaledBack)); // setting the scaled icon onto the button
        backButton.setBounds(10, 10, 60, 40); // setting the position and size of back button
        backButton.setFocusable(false); // removing the focus border from the button
        backButton.setBackground(new Color(255, 255, 255)); // setting background color to white
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // changing cursor to hand on hover
        backButton.setBorderPainted(false); // hiding the border line of the button
        backButton.addActionListener(this); // adding an action listener for button clicks
        frame.add(backButton); // adding the back button to the frame

        // --- TITLE --- // section for the title label
        JLabel title = new JLabel("PHARMACY MANAGEMENT", SwingConstants.CENTER); // creating the title label
        title.setBounds(125, 30, 600, 50); // positioning the title label on the frame
        title.setForeground(new Color(91, 14, 14)); // setting the maroon text color
        title.setFont(new Font("Times New Roman", Font.BOLD, 36)); // setting a bold title font
        frame.add(title); // adding the title label to the frame

        // --- INPUT FIELDS --- // section for input labels and fields
        createLabel("Medicine Name:", 100); // creating label for medicine name
        nameField.setBounds(335, 100, 250, 35); // positioning the medicine name text field
        nameField.setFont(new Font("Arial", Font.PLAIN, 16)); // setting font for the field text
        frame.add(nameField); // adding the medicine name field to the frame

        createLabel("Stock Quantity:", 150); // creating label for stock quantity
        qtyField.setBounds(335, 150, 250, 35); // positioning the stock quantity text field
        qtyField.setFont(new Font("Arial", Font.PLAIN, 16)); // setting font for the field text
        frame.add(qtyField); // adding the quantity field to the frame

        createLabel("Price Per Unit:", 200); // creating label for unit price
        priceField.setBounds(335, 200, 250, 35); // positioning the unit price text field
        priceField.setFont(new Font("Arial", Font.PLAIN, 16)); // setting font for the field text
        frame.add(priceField); // adding the price field to the frame

        createLabel("Supplier Name:", 250); // creating label for supplier name
        supplierField.setBounds(335, 250, 250, 35); // positioning the supplier name text field
        supplierField.setFont(new Font("Arial", Font.PLAIN, 16)); // setting font for the field text
        frame.add(supplierField); // adding the supplier name field to the frame

        // --- BUTTONS --- // section for action buttons
        updateButton.setBounds(200, 310, 200, 45); // positioning the update stock button
        styleButton(updateButton); // applying custom maroon style to the button
        updateButton.addActionListener(this); // adding an action listener for the update button
        frame.add(updateButton); // adding the update button to the frame

        sellButton.setBounds(450, 310, 200, 45); // positioning the sell medicine button
        styleButton(sellButton); // applying custom maroon style to the button
        sellButton.addActionListener(this); // adding an action listener for the sell button
        frame.add(sellButton); // adding the sell button to the frame

        // --- CONSOLE --- // section for the output console
        console.setEditable(false); // disabling user editing in the console area
        console.setFont(new Font("Monospaced", Font.PLAIN, 14)); // setting a monospace console font
        console.setBackground(new Color(240, 240, 240)); // setting a light gray console background
        scrollPane = new JScrollPane(console); // wrapping the console area in a scroll pane
        scrollPane.setBounds(100, 380, 650, 250); // positioning the scroll pane on the frame
        scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(91, 14, 14)),
                "Real-Time Log")); // adding maroon border
        frame.add(scrollPane); // adding the scroll pane to the frame

        frame.setVisible(true); // making the frame visible to the user
    } // ending the constructor

    // helper method for consistent styling // method description
    private void createLabel(String text, int y) { // definition of the createLabel helper method
        JLabel label = new JLabel(text); // creating a new label with provided text
        label.setBounds(185, y, 150, 30); // setting the standard position for the label
        label.setFont(new Font("Arial", Font.BOLD, 18)); // setting the standard bold font
        label.setForeground(new Color(91, 14, 14)); // setting the standard maroon color
        frame.add(label); // adding the created label to the frame
    } // ending the helper method

    // helper method to style buttons // method description
    private void styleButton(JButton btn) { // definition of the styleButton helper method
        btn.setFocusable(false); // removing the focus rectangle from the button
        btn.setFont(new Font("Arial", Font.BOLD, 16)); // setting the standard bold button font
        btn.setForeground(Color.WHITE); // setting the text color of the button to white
        btn.setBackground(new Color(91, 14, 14)); // setting the background color to maroon
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR)); // changing cursor to hand icon on hover
        btn.setBorder(BorderFactory.createRaisedBevelBorder()); // applying a raised border style
    } // ending the helper method

    @Override // overriding the actionPerformed method
    public void actionPerformed(ActionEvent e) { // definition of the actionPerformed method
        if (e.getSource() == backButton) { // checking if the back button was clicked
            frame.dispose(); // closing the current pharmacy page frame
            new SecondPage(); // instantiating the second page selection panel
        } // ending back button logic

        if (e.getSource() == updateButton) { // checking if the update button was clicked
            try { // starting try block for numeric parsing
                  // grabbing values from input fields // comment for data retrieval
                String name = nameField.getText(); // getting medicine name from text field
                int qty = Integer.parseInt(qtyField.getText()); // parsing quantity as an integer
                double price = Double.parseDouble(priceField.getText()); // parsing price as a double
                String supplier = supplierField.getText(); // getting supplier name from text field

                // linking with Pharmacy class methods // comment for backend sync
                pharLogic.setMedName(name); // setting medicine name in backend object
                pharLogic.setMedPrice(price); // setting unit price in backend object
                pharLogic.setSupplierName(supplier); // setting supplier name in backend object
                pharLogic.setMedQuantity(0); // resetting quantity in backend object
                pharLogic.updateStock(qty); // calling backend method to update stock and save

                // updating console // comment for gui update
                console.append("UPDATED: " + name + " | Total Stock: " + qty + " | Price: $" + price + "\n"); // logging
                                                                                                              // update
                                                                                                              // to gui
                System.out.println("GUI log: Stock updated for " + name); // logging update to ide console

                JOptionPane.showMessageDialog(frame, "Medicine Stock Updated Successfully!", "Success",
                        JOptionPane.INFORMATION_MESSAGE); // showing success popup

            } catch (NumberFormatException ex) { // catching formatting errors
                JOptionPane.showMessageDialog(frame, "Please enter valid numeric values", "Input Error",
                        JOptionPane.ERROR_MESSAGE); // showing error popup
            } // ending catch block
        } // ending update button logic

        if (e.getSource() == sellButton) { // checking if the sell button was clicked
            try { // starting try block for numeric parsing
                String name = nameField.getText(); // getting medicine name from text field
                int qty = Integer.parseInt(qtyField.getText()); // parsing sell quantity as integer

                pharLogic.setMedName(name); // setting medicine name in backend object
                // Note: In a real app we'd load current stock, here we assume it's set //
                // comment for note
                pharLogic.sellMed(qty); // calling backend method to process sale

                console.append("SOLD: " + qty + " units of " + name + "\n"); // logging sale to gui
                System.out.println("GUI log: Sold " + qty + " of " + name); // logging sale to ide console

                JOptionPane.showMessageDialog(frame, "Sale Processed!", "Success", JOptionPane.INFORMATION_MESSAGE); // showing
                                                                                                                     // success
                                                                                                                     // popup
            } catch (NumberFormatException ex) { // catching formatting errors
                JOptionPane.showMessageDialog(frame, "Please enter valid quantity", "Input Error",
                        JOptionPane.ERROR_MESSAGE); // showing error popup
            } // ending catch block
        } // ending sell button logic
    } // ending actionPerformed method
} // ending the class definition
