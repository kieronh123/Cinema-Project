import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class JDBC {

    public static void addMovies() {
        String driver = "org.sqlite.JDBC";
        String url = "jdbc:sqlite:sql/test.db";

        try {
            // Establish connection
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Connection connection = DriverManager.getConnection(url);
            System.out.println("Connection established.");

            // TODO: Insert data into Book table
            String sql = "INSERT INTO testing(col1, col2, col3) VALUES(?,?,?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, 2);
            statement.setInt(2, 3);
            statement.setInt(3, 4);
            statement.executeUpdate();

            // Close connection
            connection.close();
            System.out.println("Connection closed.");
        } catch (SQLException error) {
            System.err.println("JDBC error!");
        }
    }

    public static void main(String[] args) {
        addMovies();
    }
}