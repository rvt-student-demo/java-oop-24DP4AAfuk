package rvt;

import java.nio.file.Paths;
import java.util.Scanner;

public class CSVRead {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(Paths.get("data/data.csv"))) {

            // we read the file until all lines have been read
            while (scanner.hasNextLine()) {
                // we read one line
                String row = scanner.nextLine();
                String[] parts = row.split(",");
                System.out.println("Name: " + parts[0] + ", Age: " + parts[1] + ", ID: " + parts[2] + ", Email: " + parts[3] + ", Major: " + parts[4] + ", GPA: " + parts[5]);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
