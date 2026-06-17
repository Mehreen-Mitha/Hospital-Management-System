package hospitalmanagementsystem;

public abstract class Person {

    public int id;
    public String name;
    public int age;
    public String gender;
    public String phone;
    public String address;
    public String email;

    // Empty constructor
    public Person() {
        this.id = 0;
        this.name = "NULL";
        this.age = 0;
        this.gender = "NULL";
        this.phone = "NULL";
        this.address = "NULL";
        this.email = "NULL";
    }

    // Parameterized constructor
    public Person(int id, String name, int age, String gender, String phone, String address, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }

    public abstract void displayInfo();
}
