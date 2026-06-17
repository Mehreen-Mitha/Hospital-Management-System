package hospitalmanagementsystem;

import java.io.*;

class Patient extends Person {

    private String disease;
    private String admissionDate;
    private int roomNo;
    private String medicalHistory;

    public Patient() {
    }

    public Patient(int id, String name, int age, String gender,
            String phone, String address, String email,
            String disease, String admissionDate,
            int roomNo, String medicalHistory) {

        super(id, name, age, gender, phone, address, email);

        this.disease = disease;
        this.admissionDate = admissionDate;
        this.roomNo = roomNo;
        this.medicalHistory = medicalHistory;
    }

    public String getName() {
        return name;
    }

    public String getDisease() {
        return disease;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    @Override
    public void displayInfo() {

        System.out.println("\n---Patient info---");
        System.out.println("Patient ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Gender: " + gender);
        System.out.println("Phone: " + phone);
        System.out.println("Address: " + address);
        System.out.println("Email: " + email);
        System.out.println("Disease: " + disease);
        System.out.println("Admission Date: " + admissionDate);
        System.out.println("Room No: " + roomNo);
        System.out.println("Medical History: " + medicalHistory);

        try {
            FileWriter fw = new FileWriter("patients.txt", true);
            fw.write(id + " " + name + " " + age + " " + gender + " "
                    + phone + " " + address + " " + email + " "
                    + disease + " " + admissionDate + " " + roomNo + " "
                    + medicalHistory + "\n");
            fw.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }
}
