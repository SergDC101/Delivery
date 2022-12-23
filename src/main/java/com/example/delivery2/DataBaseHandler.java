package com.example.delivery2;

import java.sql.*;


public class DataBaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString,
                dbUser, dbPass);
        return dbConnection;
    }

    public ResultSet getUser(User user) {
        ResultSet resSet = null;
        String select = "SELECT * FROM " + Const.CLIENT_TABLE + " WHERE " +
                Const.CLIENT_LOG + "=? AND " + Const.CLIENT_PASS + "=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getLogin());
            prSt.setString(2, user.getPassword());
            resSet = prSt.executeQuery();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return resSet;
    }

    public void signUpUser(User user) {
        //SQL запрос
        String insert = "INSERT INTO " + Const.DB_NAME + "." + Const.CLIENT_TABLE + "(" +
                Const.CLIENT_FIO + "," + Const.CLIENT_LOG + "," +
                Const.CLIENT_PASS + "," + Const.CLIENT_TEL + "," +
                Const.CLIENT_EMAIL + ")" +
                "VALUES('" + user.getFio() + "','" + user.getLogin() + "','" + user.getPassword() + "','" + user.getTel() + "','" + user.getEmail() + "');";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.executeUpdate(insert);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public ResultSet getProduct(String select){
        ResultSet resSet = null;

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return resSet;

    }

    public ResultSet getCategory(){
        ResultSet resSet = null;
        String select = "SELECT name FROM ProductType";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);

            resSet = prSt.executeQuery();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return resSet;
    }


}
