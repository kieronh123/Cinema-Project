import java.sql.*;

public class JDBC {
    public static void addMovies() {
        String driver = "org.sqlite.JDBC";
        String url = "jdbc:sqlite:sql/test.db";

        try {
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            // Establish connection

            Connection connection = DriverManager.getConnection(url);
            System.out.println("Connection established.");

            //add data to table
            String sql = "INSERT INTO testing(col1, col2, col3) VALUES(?,?,?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, 45);
            statement.setInt(2, 56);
            statement.setInt(3, 75);
            statement.executeUpdate();

            // Close connection
            connection.close();
            System.out.println("Connection closed.");
        } catch (SQLException error) {
            System.err.println("JDBC error!");
        }
    }


    public static void getMovies() {
        String driver = "org.sqlite.JDBC";
        String url = "jdbc:sqlite:sql/test.db";

        try {
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            // Establish connection
            Connection connection = DriverManager.getConnection(url);
            System.out.println("Connection established.");

            //retrieve data
            String sql = "SELECT * FROM testing;";
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(sql);

            while(results.next()){
                System.out.println(results.getInt("col1") + ", " + results.getInt("col2") + ", " + results.getInt("col3"));
            }

            // Close connection
            connection.close();
            System.out.println("Connection closed.");
        } catch (SQLException error) {
            System.err.println("JDBC error!");
        }
    }


    public static void main(String[] args) {
        getMovies();
    }
}