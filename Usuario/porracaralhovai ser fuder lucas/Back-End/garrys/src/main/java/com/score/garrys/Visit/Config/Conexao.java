package com.score.garrys.Visit.Config;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/ ",
                "root",
                "senha"
            );
        } catch (Exception e) {
            throw new RuntimeException("Erro ao conectar com banco", e);
        }
    }
}
