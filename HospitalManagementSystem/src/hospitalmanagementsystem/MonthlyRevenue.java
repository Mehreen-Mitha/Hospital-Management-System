package hospitalmanagementsystem;

import java.io.FileWriter;
import java.io.IOException;

public class MonthlyRevenue implements Displayable {

    public String month;
    public double totalRevenue;

    public MonthlyRevenue() {
    }

    public MonthlyRevenue(String month) {
        this.month = month;
        this.totalRevenue = 0.0;
    }

    public void calculateRevenue(double patientBills, double pharmacySales, double otherCharges, String month) {
        this.month = month;
        totalRevenue = patientBills + pharmacySales + otherCharges;

        System.out.println("Total revenue for " + month + " is: " + totalRevenue + "$");

        try {
            FileWriter fw = new FileWriter("revenue.txt", true);
            fw.write("Month: " + month
                    + " | PatientBills: " + patientBills
                    + " | PharmacySales: " + pharmacySales
                    + " | OtherCharges: " + otherCharges
                    + " | TotalRevenue: " + totalRevenue + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("File Error (Revenue): " + e.getMessage());
        }
    }

    public void generateReport(String month) {
        System.out.println("\n--- Monthly Revenue Report ---");
        System.out.println("Month: " + month);
        System.out.println("Total Revenue: " + totalRevenue + "$");
        System.out.println("----------------------------------");

        try {
            FileWriter fw = new FileWriter("revenue.txt", true);
            fw.write("REPORT GENERATED | Month: " + month
                    + " | TotalRevenue: " + totalRevenue + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("File Error (Revenue Report): " + e.getMessage());
        }
    }

    @Override
    public void displayInfo() {
        generateReport(month);
    }
}
