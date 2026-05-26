package rvt;

import java.util.Scanner;

public class UserInterface {
    private final TodoStore list;
    private final Scanner scanner;

    public UserInterface(TodoStore list, Scanner scanner) {
        this.list = list;
        this.scanner = scanner;
    }

    public void start() {
        while (true) {
            System.out.print("Command: ");
            String command = scanner.nextLine();

            if (command.equals("stop")) {
                break;
            } else if (command.equals("add")) {
                System.out.print("To add: ");
                String task = scanner.nextLine();
                list.add(task);
            } else if (command.equals("list")) {
                list.print();
            } else if (command.equals("remove")) {
                System.out.print("Which one is removed? ");
                int index = Integer.parseInt(scanner.nextLine());
                list.remove(index - 1);
            }
        }
    }
}
