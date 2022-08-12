package DBAccess;

import Database.DBConnection;
import model.Customer;
import model.UserAttempt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class connects with the database by user attempt type.
 */
public class UserAttemptQuery {


    /** This method gets a user id from a user name.
     * @param userName This is the name to match.
     * @return Returns a user id.
     * @throws SQLException Throws SQLException.
     */
    public static int getUserID(String userName) throws SQLException {

        int userID = 0;

        DBConnection.makePreparedStatement("SELECT User_ID FROM users WHERE User_Name = ?",DBConnection.getConnection());
        PreparedStatement ps = DBConnection.getPreparedStatement();
        ps.setString(1,userName);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){

            userID = rs.getInt("User_ID");
        }

        return userID;
    }

    /** This method gets a user password from a user name.
     * @param userName This is the name to match.
     * @return Returns a user name.
     * @throws SQLException Throws SQLException.
     */
    public static String getUserPassword(String userName) throws SQLException {

        String userPW = "";

        DBConnection.makePreparedStatement("SELECT Password FROM users WHERE User_Name = ?",DBConnection.getConnection());
        PreparedStatement ps = DBConnection.getPreparedStatement();
        ps.setString(1,userName);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){

            userPW = rs.getString("Password");
        }

        return userPW;
    }


    /** This method checks if the password matches the user.
     * @param user This is the user to match.
     * @param passwordToTest This is the password to match.
     * @return Returns true if password matches user.
     * @throws SQLException Throw SQLException.
     */
    public static boolean isPasswordCorrect(UserAttempt user, String passwordToTest) throws SQLException {

        boolean isCorrect = false;//password not correct for security

        DBConnection.makePreparedStatement("SELECT Password FROM users WHERE User_Name = ?",DBConnection.getConnection());
        PreparedStatement ps = DBConnection.getPreparedStatement();
        ps.setString(1,user.getUserName());
        ResultSet rs = ps.executeQuery();

        while(rs.next()){

            isCorrect = rs.getString("Password").equals(passwordToTest);
        }

        if(isCorrect){
            user.setIsUserAttemptSuccessful(true);
        }else{
            user.setIsUserAttemptSuccessful(false);
        }

        return isCorrect;
    }

}
