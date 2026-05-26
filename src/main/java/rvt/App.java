package rvt;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        TodoDB todoDB = new TodoDB();
        UserInterface ui = new UserInterface(todoDB, new Scanner(System.in));
        ui.start();
    }
}
