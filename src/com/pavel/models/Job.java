package com.pavel.models;

import com.pavel.enums.WorkType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import static com.pavel.utils.Utils.*;


public class Job {

    public String masterName;
    public WorkType workType;
    public Date date;

    public Job(String masterName, WorkType workType, Date date) {
        this.masterName = masterName;
        this.workType = workType;
        this.date = date;
    }

    public Job(String masterName, WorkType workType) {
        this.masterName = masterName;
        this.workType = workType;
        this.date = getToday();
    }

    public static Job fromSqlString(ResultSet rs) throws SQLException, ParseException {
        return new Job(
                rs.getString("masterName"),
                WorkType.valueOf(rs.getString("workType")),
                parse(rs.getString("date"))
        );
    }

    public String toSqlString() {
        return "('" + masterName + "','" + workType.toString() + "','" + format(date) + "')";
    }

    @Override
    public String toString() {
        return '('
                + "masterName='" + masterName + '\'' +
                ", workType=" + workType +
                ", date=" + format(date) +
                ')';
    }
}
