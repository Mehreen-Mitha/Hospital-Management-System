package hospitalmanagementsystem;

import java.io.*;

public class AmbulanceService {

    private int ID;
    private String driverName;
    private String status;
    private String emergencyLevel;
    private String location;

    public AmbulanceService() {
        this.ID = 0;
        this.driverName = "NULL";
        this.status = "NULL";
        this.emergencyLevel = "NULL";
        this.location = "NULL";
    }

    public AmbulanceService(int i, String n) {
        this.ID = i;
        this.driverName = n;
    }

    public int getID() {
        return ID;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getStatus() {
        return status;
    }

    public String getEmergencyLevel() {
        return emergencyLevel;
    }

    public String getLocation() {
        return location;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setEmergencyLevel(String emergencyLevel) {
        this.emergencyLevel = emergencyLevel;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void markAvailable() {
        this.status = "Available";
        this.location = "Base";

        try {
            FileWriter fw = new FileWriter("ambulance_log.txt", true);
            fw.write(ID + "," + driverName + ",Available,Base\n");
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Ambulance " + ID + " is available.");
    }

    public void dispatchAmbulance(String newLocation, String providedLevelOfEmergency) {
        this.status = "Dispatched";
        this.location = newLocation;
        this.emergencyLevel = providedLevelOfEmergency;

        try {
            FileWriter fw = new FileWriter("ambulance_log.txt", true);
            fw.write(ID + "," + driverName + ",Dispatched," + newLocation + "," + providedLevelOfEmergency + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Ambulance " + ID + " has been dispatched.");
        System.out.println("Location updated to: " + this.location);
    }

    public void display() {

        System.out.println("\n--- Ambulance Service Details ---");
        System.out.println("ID: " + ID);
        System.out.println("Driver: " + driverName);
        System.out.println("Status: " + status);
        System.out.println("Emergency Level: " + emergencyLevel);
        System.out.println("Current Location: " + location);

        try {
            FileReader fr = new FileReader("ambulance_log.txt");
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

            br.close();
            fr.close();

        } catch (FileNotFoundException e) {
            System.out.println("Log file not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("---------------------------------");
    }
}
