package rvt;

import java.util.List;
import java.util.Scanner;

public class Registration {
    private List<Student> students;

    public Registration() {
        students = FileHandler.loadStudents();
    }

    public void registerStudent() {
        Scanner scanner = new Scanner(System.in);
        String name;
        do {
            System.out.print("Ievadiet vardu: ");
            name = scanner.nextLine().trim();
            if (!Validator.validName(name)) {
                System.out.println("Nederīgs vārds. Vārdam jābūt vismaz 3 simboli un saturēt tikai burtus.");
            }
        } while (!Validator.validName(name));

        String surname;
        do {
            System.out.print("Ievadiet uzvardu: ");
            surname = scanner.nextLine().trim();
            if (!Validator.validName(surname)) {
                System.out.println("Nederīgs uzvārds. Uzvārdam jābūt vismaz 3 simboli un saturēt tikai burtus.");
            }
        } while (!Validator.validName(surname));

        String email;
        do {
            System.out.print("Ievadiet epastu: ");
            email = scanner.nextLine().trim();
            if (!Validator.validEmail(email)) {
                System.out.println("Nederīgs epasts. Ievadiet derīgu e-pasta adresi.");
            }
        } while (!Validator.validEmail(email));

        String IDnum;
        do {
            System.out.print("Ievadiet ID numuru: ");
            IDnum = scanner.nextLine().trim();
            if (!Validator.validPersonalCode(IDnum)) {
                System.out.println("Nederīgs personas kods. Tam jābūt 11 cipariem.");
            }
        } while (!Validator.validPersonalCode(IDnum));

        String registrationDate;
        do {
            System.out.print("Ievadiet reģistrācijas datumu (YYYY-MM-DD): ");
            registrationDate = scanner.nextLine().trim();
            if (!Validator.validDate(registrationDate)) {
                System.out.println("Nederīgs datums. Izmantojiet formātu YYYY-MM-DD.");
            }
        } while (!Validator.validDate(registrationDate));

        Student newStudent = new Student(name, surname, email, IDnum, registrationDate);
        students.add(newStudent);
        FileHandler.saveStudents(students);

        System.out.println("Students veiksmigi reģistrēts!");
    }

    public void removeStudentByID(String IDnum) {
        students.removeIf(student -> student.getIDnum().equals(IDnum));
        FileHandler.saveStudents(students);
        System.out.println("Students ar ID numuru " + IDnum + " ir izdzēsts.");
    }

    public void editStudentByID(String IDnum, Scanner scanner) {
        for (Student student : students) {
            if (student.getIDnum().equals(IDnum)) {
                System.out.print("Ievadiet jaunu vardu (atstājiet tukšu, lai nemainītu): ");
                String newName = scanner.nextLine().trim();
                if (!newName.isEmpty()) {
                    if (Validator.validName(newName)) {
                        student.setName(newName);
                    } else {
                        System.out.println("Nederīgs vārds. Neizmainīts.");
                    }
                }

                System.out.print("Ievadiet jaunu uzvardu (atstājiet tukšu, lai nemainītu): ");
                String newSurname = scanner.nextLine().trim();
                if (!newSurname.isEmpty()) {
                    if (Validator.validName(newSurname)) {
                        student.setSurname(newSurname);
                    } else {
                        System.out.println("Nederīgs uzvārds. Neizmainīts.");
                    }
                }

                System.out.print("Ievadiet jaunu epastu (atstājiet tukšu, lai nemainītu): ");
                String newEmail = scanner.nextLine().trim();
                if (!newEmail.isEmpty()) {
                    if (Validator.validEmail(newEmail)) {
                        student.setEmail(newEmail);
                    } else {
                        System.out.println("Nederīgs epasts. Neizmainīts.");
                    }
                }

                FileHandler.saveStudents(students);
                System.out.println("Students ar ID numuru " + IDnum + " ir rediģēts.");
                return;
            }
        }
        System.out.println("Students ar ID numuru " + IDnum + " netika atrasts.");
    }

    public List<Student> showStudents() {
        return students;
    }
}
