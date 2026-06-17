package hospitalmanagementsystem;

import java.io.*;

public class Nurse extends Person {

    public String wardAssigned;
    public String shift;
    public String salary;

    public Nurse() {
        super();
    }

    public Nurse(int id, String name, int age, String gender, String phone, String address, String email,
            String wardAssigned, String shift, String salary) {

        super(id, name, age, gender, phone, address, email);
        this.wardAssigned = wardAssigned;
        this.shift = shift;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void assistDoctor(Doctor doctor) {
        System.out.println("Nurse " + name + " is assisting Dr. " + doctor.name);
    }

    public void checkPatientVitals(Patient patient) {
        System.out.println("Nurse " + name + " is checking vitals of " + patient.name);
    }

    @Override
    public void displayInfo() {

        System.out.println("\n----- Nurse Info -----");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Gender: " + gender);
        System.out.println("Phone: " + phone);
        System.out.println("Address: " + address);
        System.out.println("Email: " + email);
        System.out.println("Ward Assigned: " + wardAssigned);
        System.out.println("Shift: " + shift);
        System.out.println("Salary: " + salary + "\n");

        try {
            FileWriter fw = new FileWriter("nurses.txt", true);
            fw.write(id + " " + name + " " + age + " " + gender + " "
                    + phone + " " + address + " " + email + " "
                    + wardAssigned + " " + shift + " " + salary + "\n");
            fw.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }
}
