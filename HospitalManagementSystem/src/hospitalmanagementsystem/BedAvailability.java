package hospitalmanagementsystem;

import java.io.*;

public class BedAvailability implements Displayable {

    public int totalBeds;
    public int availableBeds;
    public int icuBeds;
    public int regularBeds;

    public BedAvailability() {
        File file = new File("beds.txt");
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                String lastLine = null;
                while ((line = br.readLine()) != null) {
                    if (!line.trim().isEmpty()) {
                        lastLine = line;
                    }
                }
                
                if (lastLine != null) {
                    String[] tokens = lastLine.split(" ");
                    if (tokens[0].equals("ASSIGN") || tokens[0].equals("RELEASE") || tokens[0].equals("STATUS")) {
                        if (tokens.length >= 4) {
                            this.icuBeds = Integer.parseInt(tokens[1]);
                            this.regularBeds = Integer.parseInt(tokens[2]);
                            this.availableBeds = Integer.parseInt(tokens[3]);
                            this.totalBeds = this.icuBeds + this.regularBeds;
                        }
                    } else if (tokens.length >= 4) {
                        this.totalBeds = Integer.parseInt(tokens[0]);
                        this.icuBeds = Integer.parseInt(tokens[1]);
                        this.regularBeds = Integer.parseInt(tokens[2]);
                        this.availableBeds = Integer.parseInt(tokens[3]);
                    }
                }
            } catch (Exception e) {
                System.out.println("Error reading beds state file. Resetting parameters to 0.");
                this.totalBeds = 0; this.availableBeds = 0; this.icuBeds = 0; this.regularBeds = 0;
            }
        }
    }

    public BedAvailability(int totalBeds, int icuBeds, int regularBeds) {
        this.totalBeds = totalBeds;
        this.icuBeds = icuBeds;
        this.regularBeds = regularBeds;
        this.availableBeds = totalBeds;
    }

    public void increaseCapacity(int additionalBeds, boolean icu) {
        if (additionalBeds <= 0) return;

        totalBeds += additionalBeds;
        availableBeds += additionalBeds;

        if (icu) {
            icuBeds += additionalBeds;
        } else {
            regularBeds += additionalBeds;
        }
        saveState("CAPACITY");
    }

    public void assignBed(boolean icuRequired) {
        if (icuRequired && icuBeds > 0) {
            icuBeds--;
            availableBeds--;
        } else if (!icuRequired && regularBeds > 0) {
            regularBeds--;
            availableBeds--;
        }
        saveState("ASSIGN");
    }

    public void releaseBed(boolean isIcuBed) {
        if (isIcuBed) {
            icuBeds++;
        } else {
            regularBeds++;
        }
        availableBeds++;
        saveState("RELEASE");
    }

    private void saveState(String prefix) {
        try (FileWriter fw = new FileWriter("beds.txt", true)) {
            fw.write(prefix + " " + icuBeds + " " + regularBeds + " " + availableBeds + "\n");
        } catch (IOException e) {
            System.out.println("IO error saving system bed state: " + e.getMessage());
        }
    }

    public void remainingCapacity() {
        System.out.println("Regular beds: " + regularBeds);
        System.out.println("ICU beds: " + icuBeds);
        System.out.println("Available beds: " + availableBeds);
    }

    public void displayStatus() {
        System.out.println("\n--- Bed Availability Summary ---");
        System.out.println("Total: " + totalBeds);
        System.out.println("Regular: " + regularBeds);
        System.out.println("ICU: " + icuBeds);
        System.out.println("Available: " + availableBeds);
        saveState("STATUS");
    }

    @Override
    public void displayInfo() {
        displayStatus();
    }
}