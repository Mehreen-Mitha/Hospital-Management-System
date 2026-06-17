package hospitalmanagementsystem;

import java.io.*;

public class DischargePatients implements Displayable {

    public BedAvailability bedAvailability;
    public Appointments appointments;
    public int patientId;
    public String dischargeDate;
    public double totalBill;
    public Patient patient;

    public DischargePatients() {
    }

    public DischargePatients(Patient patient, Appointments appointments,
            BedAvailability bedAvailability) {
        this.patient = patient;
        this.patientId = patient.id;
        this.appointments = appointments;
        this.bedAvailability = bedAvailability;
    }

    public void setDischargeDate(String date) {
        dischargeDate = date;
    }

    public void processPatient(boolean icuBed, double billAmount) {

        if (appointments.getCurrentPatients() <= 0) {
            System.out.println("No patients to discharge.");
            return;
        }

        appointments.setCurrentPatients(appointments.getCurrentPatients() - 1);
        bedAvailability.releaseBed(icuBed);
        totalBill = billAmount;

        System.out.println("Patient " + patientId + " discharged successfully.");

        try {
            FileWriter fw = new FileWriter("discharge.txt", true);
            fw.write(patientId + " " + dischargeDate + " " + totalBill + " "
                    + appointments.getCurrentPatients() + "\n");
            fw.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }

    public void generateDischargeSummary() {
        System.out.println("\n------ Discharge Summary ------");
        System.out.println("Patient ID: " + patientId);

        if (patient != null) {
            System.out.println("Patient Name: " + patient.name);
        }

        System.out.println("Discharge Date: " + dischargeDate);
        System.out.println("Total Bill: " + totalBill);
        System.out.println("Current patients remaining: " + appointments.getCurrentPatients());
        System.out.println("--------------------------------");

        try {
            FileWriter fw = new FileWriter("discharge.txt", true);
            fw.write(patientId + " " + (patient != null ? patient.name : "NULL") + " "
                    + dischargeDate + " " + totalBill + "\n");
            fw.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }

    @Override
    public void displayInfo() {
        generateDischargeSummary();
    }
}
