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

        return getCountryReport(divisionIDCountList, countryIDList);
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


        for(AreaCount cID : counIDList){//populate hashMap with country_ID only

            counIDCountHash.put(cID.getAreaCount(),0);
        }

        for(DivisionIDCount dID : divIDCountList){//add up customers for each country_ID

            DBConnection.makePreparedStatement("SELECT Country_ID FROM first_level_divisions WHERE Division_ID = ? ",DBConnection.getConnection());
            PreparedStatement ps = DBConnection.getPreparedStatement();
            ps.setInt(1, dID.getDivID());
            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                int cID = rs.getInt("Country_ID");
                int cCount = (Integer)counIDCountHash.get(cID) + (Integer)dID.getDivCount();
                counIDCountHash.replace(cID, cCount);
            }
        }

        for (int cID : counIDCountHash.keySet()){//change out country_id for country and populate observableList

            DBConnection.makePreparedStatement("SELECT Country FROM countries WHERE Country_ID = ?",DBConnection.getConnection());
            PreparedStatement ps = DBConnection.getPreparedStatement();
            ps.setInt(1, cID);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                AreaCount ac = new AreaCount(rs.getString("Country"),counIDCountHash.get(cID));
                cRpt.add(ac);
            }
        }

        return cRpt;
    }
}
