package model;

import DBAccess.UserAttemptQuery;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class UserAttempt {

    private int userID;
    private String userName;
    private String password;
    private LocalDateTime userLogInLocalDateTime;
    private Boolean isUserAttemptSuccessful;

    public UserAttempt(String userName) throws SQLException {

        this.userID = UserAttemptQuery.getUserID(userName);
        this.userName = userName;
        this.password = UserAttemptQuery.getUserPassword(userName);
        this.userLogInLocalDateTime = LocalDateTime.now();
        this.isUserAttemptSuccessful = null;
    }

    public int getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getUserLogInLocalDateTime() {
        return userLogInLocalDateTime;
    }

    public Boolean getIsUserAttemptSuccessful() {
        return isUserAttemptSuccessful;
    }

    public void setIsUserAttemptSuccessful(Boolean isUserAttemptSuccessful) {
        this.isUserAttemptSuccessful = isUserAttemptSuccessful;
    }
}
