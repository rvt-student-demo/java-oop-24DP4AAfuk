package rvt;

import java.util.ArrayList;
import java.util.List;

public class TodoList {
    private List<String> tasks = new ArrayList<>();

    public void add(String task) {
        this.tasks.add(task);
    }

    public int size() {
        return this.tasks.size();
    }

    public String get(int index) {
        return this.tasks.get(index);
    }

    public void print() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public void remove(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
    }

    public static void main(String[] args) {
        TodoList list = new TodoList();
        UserInterface ui = new UserInterface(list, new java.util.Scanner(System.in));
        ui.start();
    }
}
