package DBAccess;

import Database.DBConnection;
import model.Customer;
import model.UserAttempt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAttemptQuery {


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
