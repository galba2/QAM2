package model;

import DBAccess.UserAttemptQuery;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserAttempt {

    private int userID;
    private String userName;
    private String password;
    private int userLoginAttempt;
    private LocalDateTime userLogInDate;
    private Timestamp userLoginAttemptTime;
    private Boolean isUserAttemptSuccessful;

    public UserAttempt(String userName) throws SQLException {

        this.userID = UserAttemptQuery.getUserID(userName);
        this.userName = userName;
        this.password = UserAttemptQuery.getUserPassword(userName);
        this.userLoginAttempt = 0;
        this.userLogInDate = LocalDateTime.now();
        this.userLoginAttemptTime = Timestamp.valueOf(LocalDateTime.now());
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

    public int getUserLoginAttempt() {
        return userLoginAttempt;
    }

    public LocalDateTime getUserLogInDate() {
        return userLogInDate;
    }

    public Timestamp getUserLoginAttemptTime() {
        return userLoginAttemptTime;
    }

    public Boolean getIsUserAttemptSuccessful() {
        return isUserAttemptSuccessful;
    }

    public void setIsUserAttemptSuccessful(Boolean isUserAttemptSuccessful) {
        this.isUserAttemptSuccessful = isUserAttemptSuccessful;
    }
}
