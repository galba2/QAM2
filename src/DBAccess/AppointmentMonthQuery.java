package DBAccess;

import Database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.AppointmentMonth;
import model.AreaCount;
import model.DivisionIDCount;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * This class connects with the database by appointmentmonth type.
 */
public class AppointmentMonthQuery {

    /** This method gets all appointment month types.
     * @return Returns list of all appointment month types.
     * @throws SQLException Throws SQLException.
     */
    public static ObservableList getAllCustomerReportMonth() throws SQLException {

        ObservableList<AppointmentMonth> cRptMon = FXCollections.observableArrayList();

        DBConnection.makePreparedStatement("SELECT MONTHNAME(Start), COUNT(MONTHNAME(Start)) FROM appointments GROUP BY MONTHNAME(Start)",DBConnection.getConnection());
        PreparedStatement ps = DBConnection.getPreparedStatement();
        ResultSet rs = ps.executeQuery();

        while(rs.next()){

            AppointmentMonth am = new AppointmentMonth(rs.getString("MONTHNAME(Start)"),rs.getInt("COUNT(MONTHNAME(Start))"));
            cRptMon.add(am);
        }


        return cRptMon;
    }
}
