package ru.vetukov.java.core.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLiteConnection {

    private static Connection con;

    public static Connection getConnection() {
        try {
            Class.forName("org.sqlite.JDBC").newInstance();

            // Создание подключения к базе данных по пути, указанному в урле
            String url = "jdbc:sqlite:d:\\data\\AndroidFinance\\money.db";

            if (con == null)
                con = DriverManager.getConnection(url);

            return con;
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            Logger.getLogger(SQLiteConnection.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

}
