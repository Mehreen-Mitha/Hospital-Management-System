package hospitalmanagementsystem;

import java.io.FileWriter;
import java.io.IOException;

public class Equipment {

    private String equipmentName;
    private int quantity;
    private String status;
    private String lastCheckedDate;
    private String roomNumber;
    private String modelNumber;
    private boolean isHighPriority;

    public Equipment() {
    }

    public Equipment(String equipmentName, int quantity, String status, String lastCheckedDate,
            String roomNumber, String modelNumber, boolean isHighPriority) {
        this.equipmentName = equipmentName;
        this.quantity = quantity;
        this.status = status;
        this.lastCheckedDate = lastCheckedDate;
        this.roomNumber = roomNumber;
        this.modelNumber = modelNumber;
        this.isHighPriority = isHighPriority;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }

    public String getLastCheckedDate() {
        return lastCheckedDate;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public boolean getIsHighPriority() {
        return isHighPriority;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setLastCheckedDate(String lastCheckedDate) {
        this.lastCheckedDate = lastCheckedDate;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public void setIsHighPriority(boolean isHighPriority) {
        this.isHighPriority = isHighPriority;
    }

    public void checkEquipment() {
        System.out.println("\n\t\t=== Equipment Maintenance Check ===");
        System.out.println("Inspecting: " + equipmentName + " (Model: " + modelNumber + ")");
        System.out.println("Current Room: " + roomNumber);

        if (isHighPriority && status.equalsIgnoreCase("Functional")) {
            System.out.println("Check complete: High-priority item is in good standing.");
        } else {
            System.out.println("Check complete: Routine inspection logged for " + lastCheckedDate);
        }

        try {
            FileWriter fw = new FileWriter("equipment.txt", true);
            fw.write("CHECK | " + equipmentName
                    + " | Model: " + modelNumber
                    + " | Status: " + status
                    + " | Room: " + roomNumber
                    + " | Priority: " + isHighPriority
                    + " | Date: " + lastCheckedDate + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("File Error (Equipment Check): " + e.getMessage());
        }
    }

    public void repairEquipment() {
        System.out.println("Repair request initiated for " + equipmentName);

        if (status.equalsIgnoreCase("Broken") || status.equalsIgnoreCase("Maintenance Required")) {
            this.status = "Functional";
            System.out.println("Repair Successful! Status updated to: " + status);
        } else {
            System.out.println("No repairs needed. Equipment is already " + status);
        }

        try {
            FileWriter fw = new FileWriter("equipment.txt", true);
            fw.write("REPAIR | " + equipmentName
                    + " | New Status: " + status + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("File Error (Equipment Repair): " + e.getMessage());
        }

        System.out.println();
    }

    public void updateQuantity(int amount) {
        if (amount > 0) {
            this.quantity += amount;
            System.out.println("Added " + amount + " units. New total quantity: " + quantity);
        } else if (amount < 0 && (quantity + amount) >= 0) {
            this.quantity += amount;
            System.out.println("Removed " + Math.abs(amount) + " units. New total quantity: " + quantity);
        } else {
            System.out.println("Error: Invalid quantity adjustment.");
            return;
        }

        try {
            FileWriter fw = new FileWriter("equipment.txt", true);
            fw.write("QUANTITY UPDATE | " + equipmentName
                    + " | New Quantity: " + quantity + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("File Error (Equipment Quantity): " + e.getMessage());
        }
    }

    public void displayStatusReport() {
        System.out.println("\n-------- Equipment Status Report -------- ");
        System.out.println("Name: " + equipmentName);
        System.out.println("Model: " + modelNumber);
        System.out.println("Quantity: " + quantity);
        System.out.println("Location: Room " + roomNumber);
        System.out.println("Priority: " + (isHighPriority ? "HIGH" : "Normal"));
        System.out.println("Current Status: " + status);
        System.out.println("Last Inspection: " + lastCheckedDate);
        System.out.println("-------------------------------------------\n");

        try {
            FileWriter fw = new FileWriter("equipment.txt", true);
            fw.write("DISPLAY | " + equipmentName
                    + " | Model: " + modelNumber
                    + " | Qty: " + quantity
                    + " | Status: " + status + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("File Error (Equipment Display): " + e.getMessage());
        }
    }
}
