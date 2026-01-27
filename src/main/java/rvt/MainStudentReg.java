package rvt;

import java.util.Scanner;

public class MainStudentReg {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Registration registration = new Registration();

        while (true) {
            System.out.println("\nStudentu Registracijas Sistema");
            System.out.println("register | show | remove | edit | exit");
            System.out.print("> ");
            String command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "register":
                    registration.registerStudent();
                    break;
                case "show":
                    registration.showStudents();
                    break;
                case "remove":
                    System.out.print("Ievadiet personas kodu, lai izdzestu studentu: ");
                    String input = scanner.nextLine().trim();
                    registration.removeStudentByID(input);
                    break;
                case "edit":
                    System.out.print("Ievadiet personas kodu, lai redigetu studentu: ");
                    input = scanner.nextLine().trim();
                    registration.editStudentByID(input, scanner);
                    break;
                case "exit":
                    System.out.println("Iziet no programmas.");
                    return;
                default:
                    System.out.println("Nedertga komanda. Meginiet velreiz.");
            }
    }
}
}