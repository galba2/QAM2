package DBAccess;

import Database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.AreaCount;
import model.DivisionIDCount;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class CountryReportQuery {

    public static ObservableList getCountryReport() throws SQLException {


        ArrayList<DivisionIDCount> divisionIDCountList  = new ArrayList<DivisionIDCount>();
        divisionIDCountList.addAll(getDivisionIDCount());

        ArrayList<AreaCount> countryIDList  = new ArrayList<AreaCount>();
        countryIDList.addAll(getCountryIDList());



        ObservableList<AreaCount> cRpt = FXCollections.observableArrayList();
        cRpt.add(getCountryReport(divisionIDCountList, countryIDList));



        return cRpt;
    }



    public static ArrayList<DivisionIDCount> getDivisionIDCount() throws SQLException {

        ArrayList<DivisionIDCount> arLi = new ArrayList<DivisionIDCount>();

        DBConnection.makePreparedStatement("SELECT COUNT(Customer_ID), Division_ID FROM customers GROUP BY Division_ID",DBConnection.getConnection());
        PreparedStatement ps = DBConnection.getPreparedStatement();
        ResultSet rs = ps.executeQuery();

        while(rs.next()){

            DivisionIDCount di = new DivisionIDCount(rs.getInt("Division_ID"), rs.getInt("COUNT(Customer_ID)"));
            arLi.add(di);
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

    public static ObservableList getCountryReport(ArrayList<DivisionIDCount> divIDCountList, ArrayList<AreaCount> counIDList) throws SQLException {

        ObservableList<AreaCount> cRpt = FXCollections.observableArrayList();
        HashMap<Integer, Integer> counIDCountHash = new HashMap<Integer, Integer>();
        HashMap<String, Integer> cRptHash = new HashMap<String, Integer>();

        for(DivisionIDCount dID : divIDCountList){


            DBConnection.makePreparedStatement("SELECT Country_ID FROM first_level_divisions WHERE Division_ID = ? ",DBConnection.getConnection());
            PreparedStatement ps = DBConnection.getPreparedStatement();
            ps.setInt(1, dID.getDivID());
            ResultSet rs = ps.executeQuery();

            while(rs.next()){



            }

        }








        return cRpt;

    }
}
