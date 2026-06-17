package hospitalmanagementsystem;

import java.io.*;

public class DeathToll {

    private int number_of_casualties;
    private String location;
    private String date;
    private String cause;
    private String reporterName;
    private String incidentNotes;

    public DeathToll() {
    }

    public DeathToll(int number_of_casualties, String location, String date, String cause,
            String reporterName, String incidentNotes) {

        this.number_of_casualties = number_of_casualties;
        this.location = location;
        this.date = date;
        this.cause = cause;
        this.reporterName = reporterName;
        this.incidentNotes = incidentNotes;
    }

    public int getNumber_of_casualties() {
        return number_of_casualties;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public String getCause() {
        return cause;
    }

    public String getReporterName() {
        return reporterName;
    }

    public String getIncidentNotes() {
        return incidentNotes;
    }

    public void setNumber_of_casualties(int number_of_casualties) {
        this.number_of_casualties = number_of_casualties;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }

    public void setIncidentNotes(String incidentNotes) {
        this.incidentNotes = incidentNotes;
    }

    public void update_casualties(int new_number) {

        try {
            FileWriter fw = new FileWriter("death_toll.txt", true);
            fw.write(location + "," + date + "," + number_of_casualties + "->" + new_number + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        int difference = new_number - this.number_of_casualties;
        this.number_of_casualties = new_number;
        System.out.println("Record updated. Increase of " + difference + " cases reported.\n");
    }

    public void displaySummary() {

        System.out.println("\n=== INCIDENT SUMMARY REPORT ===");
        System.out.println("Date: " + date + " | Location: " + location);
        System.out.println("Cause: " + cause);
        System.out.println("Total Casualties: " + number_of_casualties);

        try {
            FileReader fr = new FileReader("death_toll.txt");
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

            br.close();
            fr.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void alertAuthorities() {
        System.out.println("EMERGENCY ALERT: " + number_of_casualties + " casualties at " + location);
    }

    public void addNote(String newNote) {
        incidentNotes += " | UPDATE: " + newNote;

        try {
            FileWriter fw = new FileWriter("death_toll_notes.txt", true);
            fw.write(location + " : " + incidentNotes + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
