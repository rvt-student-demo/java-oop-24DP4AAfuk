package rvt;

import java.util.ArrayList;
import java.util.List;

public class TodoList {
    private final List<String> tasks;

    public TodoList() {
        this.tasks = new ArrayList<>();
    }

    public void add(String task) {
        tasks.add(task);
    }

    public int size() {
        return tasks.size();
    }

    public String get(int index) {
        return tasks.get(index);
    }

    public void print() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public void remove(int i) {
        if (i >= 0 && i < tasks.size()) {
            tasks.remove(i);
        }
    }

    public static void main(String[] args) {
        TodoList list = new TodoList();
        UserInterface ui = new UserInterface(list, new java.util.Scanner(System.in));
        ui.start();
    }
}
