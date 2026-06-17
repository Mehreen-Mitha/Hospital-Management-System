package hospitalmanagementsystem;

import java.io.FileWriter;
import java.io.IOException;

public class Pharmacy {

    private String medName;
    private int medQuantity;
    private double medPrice;
    private String supplierName;

    public Pharmacy() {
        this.medName = "NULL";
        this.medQuantity = 0;
        this.medPrice = 0.0;
        this.supplierName = "NULL";
    }

    public Pharmacy(String medName, int medQuantity, double medPrice, String supplierName) {
        this.medName = medName;
        this.medQuantity = medQuantity;
        this.medPrice = medPrice;
        this.supplierName = supplierName;
    }

    public String getMedName() {
        return medName;
    }

    public int getMedQuantity() {
        return medQuantity;
    }

    public double getMedPrice() {
        return medPrice;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public void setMedQuantity(int medQuantity) {
        this.medQuantity = medQuantity;
    }

    public void setMedPrice(double medPrice) {
        this.medPrice = medPrice;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public void sellMed(int quantity) {
        if (quantity > 0 && quantity <= this.medQuantity) {
            this.medQuantity -= quantity;
            System.out.println(quantity + " units of " + medName + " sold.");

            try {
                FileWriter fw = new FileWriter("pharmacy.txt", true);
                fw.write("SOLD | Medicine: " + medName
                        + " | Quantity: " + quantity
                        + " | Remaining: " + medQuantity
                        + "\n");
                fw.close();
            } catch (IOException e) {
                System.out.println("File Error (Pharmacy Sell): " + e.getMessage());
            }

        } else {
            System.out.println("Error: Insufficient stock or invalid quantity.");
        }
    }

    public void updateStock(int quantity) {
        if (quantity > 0) {
            this.medQuantity += quantity;
            System.out.println("Stock updated - New quantity: " + this.medQuantity);

            try {
                FileWriter fw = new FileWriter("pharmacy.txt", true);
                fw.write("STOCK UPDATED | Medicine: " + medName
                        + " | Added: " + quantity
                        + " | Total: " + medQuantity
                        + "\n");
                fw.close();
            } catch (IOException e) {
                System.out.println("File Error (Pharmacy Stock): " + e.getMessage());
            }

        } else {
            System.out.println("Error: Quantity must be a positive amount");
        }
    }

    public void display() {
        System.out.println("\n--- Pharmacy Item Details ---");
        System.out.println("Medicine Name: " + medName);
        System.out.println("Quantity in Stock: " + medQuantity);
        System.out.println("Price per Unit: $" + medPrice);
        System.out.println("Supplier: " + supplierName);
        System.out.println("-----------------------------");

        try {
            FileWriter fw = new FileWriter("pharmacy.txt", true);
            fw.write("DISPLAY | Medicine: " + medName
                    + " | Price: " + medPrice
                    + " | Supplier: " + supplierName
                    + " | Quantity: " + medQuantity
                    + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("File Error (Pharmacy Display): " + e.getMessage());
        }
    }
}
