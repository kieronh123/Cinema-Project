import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
            String sql = "CREATE TABLE test(col1 int, col2 int, col3 int)";
            Statement statement = connection.createStatement();
            int count = statement.executeUpdate(sql);


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