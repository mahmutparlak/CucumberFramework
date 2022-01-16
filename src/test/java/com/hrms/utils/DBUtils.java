package com.hrms.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBUtils {

    static Connection connection;
    static ResultSet resultSet;

    /**
     * This method creates a connection to a database
     * @return
     */
    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(ConfigsReader.getPropertyValue("DBUrl"),
                                                                ConfigsReader.getPropertyValue("DBUserName"),
                                                                ConfigsReader.getPropertyValue("DBPassword"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * This method gets result object from specified sqlQuery
     * @param sqlQuery
     * @return
     */
    public static ResultSet getResultSet(String sqlQuery) {
        try {
            resultSet = getConnection().createStatement().executeQuery(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    /**
     * This method returns data from ResultSet Data objects
     * @param sqlQuery
     * @return List<Map<K, V>>
     */
    public List<Map<String, String>> getDataBaseData(String sqlQuery) {

        List<Map<String, String>> databaseData = new ArrayList<>();

        Map<String, String> dataBaseSingleData;

        try {
            resultSet= getResultSet(sqlQuery);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            while (resultSet.next()) {

                dataBaseSingleData = new LinkedHashMap<>();

                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    dataBaseSingleData.put(resultSetMetaData.getColumnName(i), resultSet.getString(resultSetMetaData.getColumnName(i)));
                }
                databaseData.add(dataBaseSingleData);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return databaseData;
    }
}
