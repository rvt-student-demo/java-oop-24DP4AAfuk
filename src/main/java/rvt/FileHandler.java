package rvt;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// file handler for student registration tool
public class FileHandler {
    private static final String FILE_PATH = "data/students.csv";

    public static List<Student> loadStudents() {
        List<Student> students = new ArrayList<>();
        File file = new File(FILE_PATH);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) parent.mkdirs();
        if (!file.exists()) {
            try { file.createNewFile(); } catch (IOException e) {
                System.err.println("Failed to create file: " + e.getMessage());
                return students;
            }
        }
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    Student student = new Student(parts[0], parts[1], parts[2], parts[3], parts[4]);
                    students.add(student);
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to load students: " + e.getMessage());
        }
        return students;
    }

    public static void saveStudents(List<Student> students) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Student student : students) {
                String line = String.join(",",
                        student.name,
                        student.surname,
                        student.email,
                        student.IDnum,
                        student.registrationDate);
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Failed to save students: " + e.getMessage());
        }
    }
}
