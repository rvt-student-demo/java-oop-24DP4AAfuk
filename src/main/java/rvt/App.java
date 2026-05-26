package rvt;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        TodoDB todoList = new TodoDB();
        UserInterface ui = new UserInterface(todoList, new Scanner(System.in));
        ui.start();
    }
}
