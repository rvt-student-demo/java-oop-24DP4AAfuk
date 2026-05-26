package rvt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TodoDB extends TodoList {
    private static final String DB_URL = "jdbc:sqlite:todo.db";

    public TodoDB() {
        initSchema();
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    private void initSchema() {
        String sql = "CREATE TABLE IF NOT EXISTS todo ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "task TEXT NOT NULL"
                + ")";

        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Schema init failed", e);
        }
    }

    @Override
    public void add(String task) {
        addTask(task);
    }

    public void addTask(String task) {
        String sql = "INSERT INTO todo(task) VALUES (?)";

        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, task);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to add task", e);
        }
    }

    public List<String> findAllTasks() {
        List<String> tasks = new ArrayList<>();
        String sql = "SELECT task FROM todo ORDER BY id";

        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                tasks.add(rs.getString("task"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to load tasks", e);
        }

        return tasks;
    }

    @Override
    public int size() {
        String sql = "SELECT COUNT(*) FROM todo";

        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            return rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to count tasks", e);
        }
    }

    @Override
    public String get(int index) {
        return findAllTasks().get(index);
    }

    @Override
    public void print() {
        List<String> tasks = findAllTasks();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    @Override
    public void remove(int i) {
        List<TaskRow> tasks = loadTaskRows();
        if (i >= 0 && i < tasks.size()) {
            removeById(tasks.get(i).id());
        }
    }

    public void removeById(int id) {
        String sql = "DELETE FROM todo WHERE id = ?";

        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to remove task", e);
        }
    }

    private List<TaskRow> loadTaskRows() {
        List<TaskRow> tasks = new ArrayList<>();
        String sql = "SELECT id, task FROM todo ORDER BY id";

        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                tasks.add(new TaskRow(rs.getInt("id"), rs.getString("task")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to load tasks", e);
        }

        return tasks;
    }

    public static void main(String[] args) {
        TodoDB list = new TodoDB();
        UserInterface ui = new UserInterface(list, new java.util.Scanner(System.in));
        ui.start();
    }

    private record TaskRow(int id, String task) {
    }
}