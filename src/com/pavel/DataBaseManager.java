package com.pavel;

import com.pavel.models.Job;
import com.pavel.utils.Utils;

import java.sql.*;
import java.text.ParseException;

import static com.pavel.utils.Utils.format;

public class DataBaseManager {

    private static final String DATA_BASE_URL = "jdbc:sqlite:workshop.db";
    private static final String JOB_TABLE_NAME = "repair";
    private Connection connection;
    private Statement statement;

    public DataBaseManager() {
        init();
    }

    private void init() {
        try {
            connection = DriverManager.getConnection(DATA_BASE_URL);
            statement = connection.createStatement();
            statement.setQueryTimeout(10);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void printDatabase() {
        try {
            ResultSet rs = statement.executeQuery("select * from " + JOB_TABLE_NAME);
            while (rs.next()) {
                System.out.println(Job.fromSqlString(rs).toString());
            }
        } catch (SQLException | ParseException e) {
            System.err.println(e.getMessage());
        }
    }

    public void addJob(Job job) {
        try {
            createTable();
            statement.executeUpdate("insert into " + JOB_TABLE_NAME + " values " + job.toSqlString());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void closeConnections() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void getWorkBetweenDates(java.util.Date fromDate, java.util.Date toDate) {
        try {
            ResultSet rs = statement.executeQuery("SELECT * from  " + JOB_TABLE_NAME +
                    " Where date BETWEEN '" + format(fromDate) + "' AND '" + format(toDate) + "'");
            while (rs.next()) {
                System.out.println(Job.fromSqlString(rs).toString());
            }
        } catch (SQLException | ParseException e) {
            System.err.println(e.getMessage());
        }
    }

    private void createTable() {
        try {
            statement.executeUpdate("create table if not exists " + JOB_TABLE_NAME +
                    " (masterName string, workType string, date DATETIME)");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void clearAll() {
        try {
            statement.executeUpdate("delete from " + JOB_TABLE_NAME);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

}