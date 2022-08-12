package model;

import DBAccess.UserAttemptQuery;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * This class is the model for UserAttempt.
 */
public class UserAttempt {

    private int userID;
    private String userName;
    private String password;
    private LocalDateTime userLogInLocalDateTime;
    private Boolean isUserAttemptSuccessful;

    /** Constructor UserAttempt
     * @param userName userName
     * @throws SQLException throws  SQLException
     */
    public UserAttempt(String userName) throws SQLException {

        this.userID = UserAttemptQuery.getUserID(userName);
        this.userName = userName;
        this.password = UserAttemptQuery.getUserPassword(userName);
        this.userLogInLocalDateTime = LocalDateTime.now();
        this.isUserAttemptSuccessful = null;
    }

    /** This method gets userID
     * @return userID
     */
    public int getUserID() {
        return userID;
    }

    /** This method gets userName
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /** This method gets password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /** This method gets userLogInLocalDateTime
     * @return userLogInLocalDateTime
     */
    public LocalDateTime getUserLogInLocalDateTime() {
        return userLogInLocalDateTime;
    }

    /** This method gets isUserAttemptSuccessful
     * @return isUserAttemptSuccessful
     */
    public Boolean getIsUserAttemptSuccessful() {
        return isUserAttemptSuccessful;
    }

    /** This method sets isUserAttemptSuccessful
     * @param isUserAttemptSuccessful isUserAttemptSuccessful
     */
    public void setIsUserAttemptSuccessful(Boolean isUserAttemptSuccessful) {
        this.isUserAttemptSuccessful = isUserAttemptSuccessful;
    }
}
