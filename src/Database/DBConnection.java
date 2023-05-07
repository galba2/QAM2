package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This class connects with the database.
 */
public class DBConnection {

    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";//~~~client_schedule
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference //~~~com.mysql.cj.jdbc.Driver
    private static final String userName = "sqlUser"; // Username //~~~sqlUser
    private static String password = "Passw0rd!"; // Password //~~~Passw0rd!
    private static Connection connection = null;  // Connection Interface
    private static PreparedStatement preparedStatement;

    /**
     * This method makes a connection with the database.
     */
    public static void makeConnection() {

        try {
            Class.forName(driver); // Locate Driver
            //password = Details.getPassword(); // Assign password
            connection = DriverManager.getConnection(jdbcUrl, userName, password); // reference Connection object
            System.out.println("~~~Connection successful!!!");
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("~~~Connection closed!!!");
        } catch (SQLException e) {
            //Do Nothing
        }
    }

    /** This method makes a prepared statement.
     * @param sqlStatement This is the sql command.
     * @param conn This is the connection.
     * @throws SQLException Throws SQLException.
     */
    public static void makePreparedStatement(String sqlStatement, Connection conn) throws SQLException {
        if (conn != null)
            preparedStatement = conn.prepareStatement(sqlStatement);
        else
            System.out.println("Prepared Statement Creation Failed!");
    }

    /** This method gets the prepared statement.
     * @return Returns prepared statement.
     * @throws SQLException Throws SQLException.
     */
    public static PreparedStatement getPreparedStatement() throws SQLException {
        if (preparedStatement != null)
            return preparedStatement;
        else System.out.println("Null reference to Prepared Statement");
        return null;
    }
}
