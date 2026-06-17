package hospitalmanagementsystem;

import java.io.FileWriter;
import java.io.IOException;

public class Appointments {

    private String appointmentID;
    private Patient patient;
    private Doctor doctor;
    private String dateTime;
    private Department department;
    private double fee;
    private String status;
    private int currentPatients;

    public Appointments() {
    }

    public Appointments(String appointmentID, Patient patient, Doctor doctor, String dateTime,
            Department department, double fee, String status, int currentPatients) {
        this.appointmentID = appointmentID;
        this.patient = patient;
        this.doctor = doctor;
        this.dateTime = dateTime;
        this.department = department;
        this.fee = fee;
        this.status = status;
        this.currentPatients = currentPatients;
    }

    public String getAppointmentID() {
        return appointmentID;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getDateTime() {
        return dateTime;
    }

    public Department getDepartment() {
        return department;
    }

    public double getFee() {
        return fee;
    }

    public String getStatus() {
        return status;
    }

    public int getCurrentPatients() {
        return currentPatients;
    }

    public void setAppointmentID(String appointmentID) {
        this.appointmentID = appointmentID;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCurrentPatients(int currentPatients) {
        this.currentPatients = currentPatients;
    }

    public void scheduleAppointment() {
        System.out.println("Scheduling appointment " + appointmentID + " for " + patient.getName());
        this.status = "Scheduled";
        this.currentPatients++;
        System.out.println("Appointment confirmed for " + dateTime + " with Dr. " + doctor.getName());
        System.out.println("Department: " + department.getDepName() + " | Location: " + department.getLocation());
        System.out.println("Current active patients in queue: " + currentPatients + "\n");

        try {
            FileWriter fw = new FileWriter("appointments.txt", true);
            fw.write("SCHEDULED | ID: " + appointmentID
                    + " | Patient: " + patient.getName()
                    + " | Doctor: " + doctor.getName()
                    + " | Dept: " + department.getDepName()
                    + " | Time: " + dateTime
                    + " | Status: " + status + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("File Error (Appointments): " + e.getMessage());
        }
    }

    public void reschedule(String new_date) {
        System.out.println("Requesting reschedule for ID: " + appointmentID);
        this.dateTime = new_date;
        this.status = "Rescheduled";
        System.out.println("Success: The new appointment time is set to " + dateTime + "\n");

        try {
            FileWriter fw = new FileWriter("appointments.txt", true);
            fw.write("RESCHEDULED | ID: " + appointmentID
                    + " | New Time: " + dateTime
                    + " | Patient: " + patient.getName() + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("File Error (Appointments): " + e.getMessage());
        }
    }

    public void cancelAppointment() {
        System.out.println("Cancelling appointment for patient: " + patient.getName());
        this.status = "Cancelled";
        if (currentPatients > 0) {
            currentPatients--;
        }
        System.out.println("Status updated. Refund of $" + (fee * 0.8) + " processed to patient.\n");

        try {
            FileWriter fw = new FileWriter("appointments.txt", true);
            fw.write("CANCELLED | ID: " + appointmentID + "| Patient: " + patient.getName() + " | Refund: "
                    + (fee * 0.8) + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("File Error (Appointments): " + e.getMessage());
        }
    }

    public void displayAppointmentCard() {
        System.out.println("\n------- APPOINTMENT CARD -------");
        System.out.println("ID: " + appointmentID + " | Status: " + status);
        System.out.println("Patient: " + patient.getName() + " | Doctor: " + doctor.getName());
        System.out.println("Dept: " + department.getDepName() + " | Fee: $" + fee);
        System.out.println("Time: " + dateTime);
        System.out.println("--------------------------------\n");
    }

    public void updatePatientCount(int newCount) {
        System.out.println("Manual update: changing patient queue from " + currentPatients + " to " + newCount);
        this.currentPatients = newCount;
    }

    public void admitPatient() {
        System.out.println("Admitting " + patient.getName() + " to " + department.getDepName() + " department.");
        this.status = "Admitted";
        System.out.println("Checking medical history: " + patient.getMedicalHistory());
        System.out.println("Admission process completed successfully.\n");

        try {
            FileWriter fw = new FileWriter("appointments.txt", true);
            fw.write("ADMITTED | ID: " + appointmentID
                    + " | Patient: " + patient.getName()
                    + " | Dept: " + department.getDepName() + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("File Error (Appointments): " + e.getMessage());
        }
    }
}
