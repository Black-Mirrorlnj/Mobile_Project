package com.score.garrys.Admin.Conexão;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexão {

    private static final String URL = "jdbc:mysql://localhost:3306/seubanco";
    private static final String USER = "root";
    private static final String PASSWORD = "senha";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
