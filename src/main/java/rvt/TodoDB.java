package rvt;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class TodoDB implements TodoStore {
    private final String dbUrl;

    public TodoDB(){
        this("todo.db");
    }

    public TodoDB(String dbPath) {
        if (dbPath.startsWith("jdbc:sqlite:")) {
            this.dbUrl = dbPath;
        } else {
            this.dbUrl = "jdbc:sqlite:" + dbPath;
        }
        initSchema();
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(dbUrl);
    }

    private void initSchema() {
        String sql = "CREATE TABLE IF NOT EXISTS todo ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "task TEXT NOT NULL"
                + ")";

        try (
            Connection conn = connect();
            Statement stmt = conn.createStatement()
        ) {
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

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, task);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to add task", e);
        }
    }

    public List<String> findAllTasks() {
        List<String> tasks = new ArrayList<>();
        for (TaskRow row : loadTasks()) {
            tasks.add(row.task());
        }
        return tasks;
    }

    @Override
    public int size() {
        String sql = "SELECT COUNT(*) FROM todo";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            return rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to count tasks", e);
        }
    }

    @Override
    public String get(int index) {
        List<TaskRow> tasks = loadTasks();
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + tasks.size());
        }
        return tasks.get(index).task();
    }

    @Override
    public void print() {
        List<TaskRow> tasks = loadTasks();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).task());
        }
    }

    @Override
    public void remove(int index) {
        List<TaskRow> tasks = loadTasks();
        if (index < 0 || index >= tasks.size()) {
            return;
        }
        removeById(tasks.get(index).id());
    }

    public void removeById(int id) {
        String sql = "DELETE FROM todo WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to remove task", e);
        }
    }

    private List<TaskRow> loadTasks() {
        List<TaskRow> tasks = new ArrayList<>();
        String sql = "SELECT id, task FROM todo ORDER BY id";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                tasks.add(new TaskRow(rs.getInt("id"), rs.getString("task")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to load tasks", e);
        }

        return tasks;
    }

    private record TaskRow(int id, String task) {
    }
}
