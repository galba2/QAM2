package DBAccess;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import model.CustomerReport;

import java.sql.SQLException;

/**
 * This class connects with the database by customer report type.
 */
public class CustomerReportQuery {

    /** This method gets a list of all customer report type.
     * @return Returns a list of all customer report type.
     * @throws SQLException Throws SQLException.
     */
    public static ObservableList getAllCustomerReport() throws SQLException {

        int count = 0;
        ObservableList<CustomerReport> cr = FXCollections.observableArrayList();

        //get all appointments
        ObservableList<Appointment> allAppts = FXCollections.observableArrayList();
        allAppts.addAll(AppointmentQuery.getAllAppointments());

        //get distinct types from all appointments
        ObservableList<String> types = FXCollections.observableArrayList();
        ObservableList<String> copyTypes = FXCollections.observableArrayList();
        for(Appointment a: allAppts){
            copyTypes.add(a.getType());
        }
        types.addAll(copyTypes.stream().distinct().toList());

        //get different months from all appointments
        ObservableList<String> months = FXCollections.observableArrayList();
        ObservableList<String> copyMonths = FXCollections.observableArrayList();
        for(Appointment a: allAppts){
            copyMonths.add(a.getStart().getMonth().toString());
        }
        months.addAll(copyMonths.stream().distinct().toList());

        //get number of matches with month and types then add to customer report
        for(String m: months){

            for(String t: types){
                count = 0;
                for(Appointment a: allAppts){
                    if(m.compareTo(a.getStart().getMonth().toString()) == 0   &&   t.compareTo(a.getType())== 0)//check if month and type match
                        count++;
                }
                if(count != 0){//check if count is not zero before adding to list
                    CustomerReport ncr = new CustomerReport(m,t,count);
                    cr.add(ncr);
                }
            }
        }
        return cr;
    }
}
