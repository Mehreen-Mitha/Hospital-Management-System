package hospitalmanagementsystem;

import java.io.*;

public class Doctor extends Person {

    public String specialization;
    public double salary;
    public int experienceYears;

    public Doctor() {
        super();
    }

    Doctor(int id, String name, int age, String gender,
            String phone, String address, String email,
            String specialization, double salary, int experienceYears) {

        super(id, name, age, gender, phone, address, email);
        this.specialization = specialization;
        this.salary = salary;
        this.experienceYears = experienceYears;
    }

    public String getName() {
        return name;
    }

    public void diagnosePatient(Patient patient) {
        System.out.println("Dr. " + name + " is diagnosing " + patient.name
                + " for " + patient.getDisease());
    }

    public void prescribeMedicine(Patient patient, String medicine) {
        System.out.println("Dr. " + name + " prescribed " + medicine
                + " to " + patient.name);
    }

    @Override
    public void displayInfo() {
        System.out.println("\n----- Doctor Info -----");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Gender: " + gender);
        System.out.println("Phone: " + phone);
        System.out.println("Address: " + address);
        System.out.println("Email: " + email);
        System.out.println("Specialization: " + specialization);
        System.out.println("Salary: " + salary);
        System.out.println("Experience: " + experienceYears + " years\n");

        try {
            FileWriter fw = new FileWriter("doctors.txt", true);
            fw.write(id + " " + name + " " + age + " " + gender + " "
                    + phone + " " + address + " " + email + " "
                    + specialization + " " + salary + " " + experienceYears + "\n");
            fw.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }
}
