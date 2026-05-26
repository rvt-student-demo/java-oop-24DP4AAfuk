package rvt;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:todo.db");
            Statement statement = connection.createStatement();

            statement.execute("CREATE TABLE IF NOT EXISTS todo (id INTEGER PRIMARY KEY, task STRING NOT NULL)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
