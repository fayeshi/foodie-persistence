package edu.cmu.team17.db;

import edu.cmu.team17.model.Chef;
import edu.cmu.team17.model.Dish;
import edu.cmu.team17.servlet.ServletUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 * Created by dev on 12/6/15.
 */
public class Util {

    private static Connection connection;

    static {

        try {


            Logger.getAnonymousLogger().info("Starting to connect to mysql");
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        }

//        try {
//            getConnection(true);
//        } catch (Exception e) {
//
//        }
    }



    public static ResultSet getResultSet( String sql) {

        try {

            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/foodiedb", "root", "123456");

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    public  static PreparedStatement getPreparedStatement(String sql) {
        try {

            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/foodiedb", "root", "123456");

            return connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;


    }

    public static ResultSet getResultSet( PreparedStatement sql) {

        try {

            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/foodiedb", "root", "123456");

            ResultSet resultSet = sql.executeQuery();
            return resultSet;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }



}
