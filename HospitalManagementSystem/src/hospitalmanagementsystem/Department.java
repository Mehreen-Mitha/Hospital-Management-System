package hospitalmanagementsystem;

import java.io.*;

public class Department {

    private String depName;
    private String headOfDep;
    private String location;
    private int numberOfDoctors;
    private int minimumDoctorsRequired;
    private int numberOfNurses;
    private int minimumNursesRequired;
    private int numberOfPatients;
    private int maxPatients;

    public Department() {
        this.minimumDoctorsRequired = 5;
        this.minimumNursesRequired = 10;
        this.maxPatients = 30;
    }

    public Department(String depName, String headOfDep, String location, int numberOfDoctors, int numberOfNurses,
            int numberOfPatients) {
        this();
        this.depName = depName;
        this.headOfDep = headOfDep;
        this.location = location;
        this.numberOfDoctors = numberOfDoctors;
        this.numberOfNurses = numberOfNurses;
        this.numberOfPatients = numberOfPatients;
    }

    public String getDepName() {
        return depName;
    }

    public String getHeadOfDep() {
        return headOfDep;
    }

    public String getLocation() {
        return location;
    }

    public int getNumberOfDoctors() {
        return numberOfDoctors;
    }

    public int getMinimumDoctorsRequired() {
        return minimumDoctorsRequired;
    }

    public int getNumberOfNurses() {
        return numberOfNurses;
    }

    public int getMinimumNursesRequired() {
        return minimumNursesRequired;
    }

    public int getNumberOfPatients() {
        return numberOfPatients;
    }

    public int getMaxPatients() {
        return maxPatients;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public void setHeadOfDep(String headOfDep) {
        this.headOfDep = headOfDep;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setNumberOfDoctors(int numberOfDoctors) {
        this.numberOfDoctors = numberOfDoctors;
    }

    public void setMinimumDoctorsRequired(int minimumDoctorsRequired) {
        this.minimumDoctorsRequired = minimumDoctorsRequired;
    }

    public void setNumberOfNurses(int numberOfNurses) {
        this.numberOfNurses = numberOfNurses;
    }

    public void setMinimumNursesRequired(int minimumNursesRequired) {
        this.minimumNursesRequired = minimumNursesRequired;
    }

    public void setNumberOfPatients(int numberOfPatients) {
        this.numberOfPatients = numberOfPatients;
    }

    public void setMaxPatients(int maxPatients) {
        this.maxPatients = maxPatients;
    }

    public void checkDepCapacity() {
        System.out.println("Checking capacity for " + depName + "...");
        if (numberOfPatients >= maxPatients) {
            System.out.println("ALERT: Department is at full capacity (" + numberOfPatients + "/" + maxPatients + ")");
        } else {
            System.out.println("STATUS: Capacity available. Current patients: " + numberOfPatients);
        }

        try {
            FileWriter fw = new FileWriter("departments.txt", true);
            fw.write(depName + " " + headOfDep + " " + location + " "
                    + numberOfDoctors + " " + numberOfNurses + " "
                    + numberOfPatients + "\n");
            fw.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }

    public void checkUnderstaffedDoctors() {
        if (numberOfDoctors < minimumDoctorsRequired) {
            System.out.println("WARNING: " + depName + " is understaffed on Doctors. Current: " + numberOfDoctors
                    + " (Min: " + minimumDoctorsRequired + ")");
        } else {
            System.out.println("Current number of Doctors is adequate.");
        }
    }

    public void checkUnderstaffedNurses() {
        if (numberOfNurses < minimumNursesRequired) {
            System.out.println("WARNING: " + depName + " is understaffed on Nurses. Current: " + numberOfNurses
                    + " (Min: " + minimumNursesRequired + ")");
        } else {
            System.out.println("Current number of Nurses is adequate.");
        }
    }

    public void displayInfo() {
        System.out.println("\n\t\t=== Department Information ===");
        System.out.println("Department: " + depName);
        System.out.println("Head of Dept: " + headOfDep);
        System.out.println("Location: " + location);
        System.out.println("Staff: " + numberOfDoctors + " Doctors, " + numberOfNurses + " Nurses");
        System.out.println("Patients: " + numberOfPatients + " / " + maxPatients);
        System.out.println("-----------------------------------------------\n");

        try {
            FileWriter fw = new FileWriter("departments.txt", true);
            fw.write(depName + " " + headOfDep + " " + location + " "
                    + numberOfDoctors + " " + numberOfNurses + " "
                    + numberOfPatients + "\n");
            fw.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }
}
