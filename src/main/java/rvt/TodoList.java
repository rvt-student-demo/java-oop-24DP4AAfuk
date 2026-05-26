package rvt;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.io.File;
import java.nio.file.*;
import java.util.stream.Stream;

public class TodoList {
    private ArrayList<String> tasks;
    private final String filePath = "data/todo.csv";

    public TodoList() {
        this.tasks = new ArrayList<>();
        loadFromFile();
    }

    private void loadFromFile() {
        File file = new File(filePath);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) parent.mkdirs();
        if (!file.exists()) {
            try { file.createNewFile(); } catch (IOException e) {
                System.err.println("Failed to create file: " + e.getMessage());
                return;
            }
        }
        try (Stream<String> linesStream = Files.lines(file.toPath())) {
            linesStream.map(String::trim)
                       .filter(line -> !line.isEmpty())
                       .filter(line -> {
                           String lower = line.toLowerCase();
                           return !(lower.startsWith("id,") || lower.startsWith("task,") || lower.equals("id,task") || lower.contains(",task"));
                       })
                       .forEach(line -> {
                           int commaIndex = line.indexOf(',');
                           String parsedTask = (commaIndex >= 0) ? line.substring(commaIndex + 1).trim() : line;
                           if (!parsedTask.isEmpty()) tasks.add(parsedTask);
                       });
        } catch (IOException e) {
            System.err.println("Failed to load tasks: " + e.getMessage());
        }
    }

    public void add(String task) {
        this.tasks.add(task);
        saveToFile();
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

    public void remove(int i) {
        if (i >= 0 && i < tasks.size()) {
            tasks.remove(i);
            saveToFile();
        }
    }

    private void saveToFile() {
        try {
            Path path = Paths.get(filePath);
            Path parent = path.getParent();
            if (parent != null && !Files.exists(parent)) Files.createDirectories(parent);
            List<String> out = new ArrayList<>();
            out.add("id,task");
            for (int i = 0; i < tasks.size(); i++) {
                String raw = tasks.get(i);
                String escaped = raw.replace("\"", "\"\"");
                if (escaped.contains(",") || escaped.contains("\"")) escaped = "\"" + escaped + "\"";
                out.add((i + 1) + "," + escaped);
            }
            Files.write(path, out, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("Failed to save tasks: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        TodoList list = new TodoList();
        UserInterface ui = new UserInterface(list, new java.util.Scanner(System.in));
        ui.start();
    }
}
