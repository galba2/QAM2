package DBAccess;

import Database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.AppointmentType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AppointmentTypeQuery {

    public static ObservableList getAllCustomerReportType() throws SQLException {

        ObservableList<AppointmentType> cRptTyp = FXCollections.observableArrayList();

        DBConnection.makePreparedStatement("SELECT Type, Count(Type) FROM appointments GROUP BY Type",DBConnection.getConnection());
        PreparedStatement ps = DBConnection.getPreparedStatement();
        ResultSet rs = ps.executeQuery();

        while(rs.next()){

            AppointmentType at = new AppointmentType(rs.getString("Type"), rs.getInt("Count(Type)"));
            cRptTyp.add(at);
        }


        return cRptTyp;
    }
}
