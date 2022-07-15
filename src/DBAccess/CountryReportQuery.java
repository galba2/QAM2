package DBAccess;

import Database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.AreaCount;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class CountryReportQuery {

    public static ObservableList getCountryReport() throws SQLException {


        ArrayList<AreaCount> divisionIDCountList  = new ArrayList<AreaCount>();
        divisionIDCountList.addAll(getDivisionIDCount());

        ArrayList<AreaCount> countryIDList  = new ArrayList<AreaCount>();
        countryIDList.addAll(getCountryIDList());



        ObservableList<AreaCount> cRpt = FXCollections.observableArrayList();



        return cRpt;
    }



    public static ArrayList<AreaCount> getDivisionIDCount() throws SQLException {

        ArrayList<int, int> arLi = new ArrayList<AreaCount>();

        DBConnection.makePreparedStatement("SELECT COUNT(Customer_ID), Division_ID FROM customers GROUP BY Division_ID",DBConnection.getConnection());
        PreparedStatement ps = DBConnection.getPreparedStatement();
        ResultSet rs = ps.executeQuery();

        while(rs.next()){

            AreaCount ac = new AreaCount(rs.getString("Division_ID"), rs.getInt("COUNT(Customer_ID)"));
            arLi.add(ac);
        }

        return  arLi;

    }

    public static ArrayList<AreaCount> getCountryIDList() throws SQLException {

        ArrayList<AreaCount> arLi1 = new ArrayList<AreaCount>();

        DBConnection.makePreparedStatement("SELECT Country, Country_ID FROM countries ",DBConnection.getConnection());
        PreparedStatement ps = DBConnection.getPreparedStatement();
        ResultSet rs = ps.executeQuery();

        while(rs.next()){

            AreaCount ac = new AreaCount(rs.getString("Country"), rs.getInt("Country_ID"));
            arLi1.add(ac);

        }

        return arLi1;

    }
}
