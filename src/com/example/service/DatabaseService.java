package com.example.service;

import com.example.model.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseService {


    public static int addCity(Connection conn, City city) {
        PreparedStatement pstmt = null;
        int i = -1;
        try {
            String sql = "INSERT INTO City " + "VALUES (?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, city.getIdCity());
            pstmt.setString(2, city.getName());
            pstmt.setInt(3, city.getTouristNumber());
            pstmt.setString(4, city.getDescription());
            i= pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException se2) {
            }
        }
        return i;
    }


   public static City getCity(Connection conn,int idCity) {
        PreparedStatement pstmt = null;
        City city = new City();
        try {

            String sql = "SELECT * FROM City where idCity=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,idCity);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                city.setIdCity(rs.getInt("idCity"));
                city.setName(rs.getString("name"));
                city.setTouristNumber(rs.getInt("touristNumber"));
                city.setDescription(rs.getString("description"));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException se2) {
            }
        }
        return  city;
    }


    public static List<City> getCities(Connection conn) {
        Statement stmt = null;
        List<City> cities = new ArrayList<City>();

        try {

            String sql = "SELECT * FROM City";
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                City city = new City();
                city.setIdCity(rs.getInt("idCity"));
                city.setName(rs.getString("name"));
                city.setTouristNumber(rs.getInt("touristNumber"));
                city.setDescription(rs.getString("description"));
                cities.add(city);
            }
            rs.close();
            stmt.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }
        }
        return  cities;
    }
// New method

    public static City getCityByName(Connection conn,String name) {
        Statement stmt = null;
        City city = new City();

        try {

            String sql = "SELECT * FROM City where name='"+name+"'";
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                city.setIdCity(rs.getInt("idCity"));
                city.setName(rs.getString("name"));
                city.setTouristNumber(rs.getInt("touristNumber"));
                city.setDescription(rs.getString("description"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }
        }
        return city;
    }


}
