package com.score.garrys.Admin.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.score.garrys.Admin.model.entidade.Administrador;
import com.score.garrys.Conexao;

public class Admin_repository {

    public List<Administrador> listar() {
        List<Administrador> lista = new ArrayList<>();

        String sql = "SELECT * FROM administrador";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Administrador admin = mapear(rs);
                lista.add(admin);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Administrador buscarPorId(int id) {
        String sql = "SELECT * FROM administrador WHERE id_admin = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapear(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Administrador buscarPorUsername(String username) {
        String sql = "SELECT * FROM administrador WHERE username = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapear(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void inserir(Administrador admin) {
        String sql = """
            INSERT INTO administrador 
            (username, password, email, nivel_acesso, status, criado_em)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, admin.getUsername());
            stmt.setString(2, admin.getPassword());
            stmt.setString(3, admin.getEmail());
            stmt.setInt(4, admin.getNivelAcesso());
            stmt.setString(5, admin.getStatus());
            stmt.setTimestamp(6, Timestamp.valueOf(admin.getCriadoEm()));

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Administrador admin) {
        String sql = """
            UPDATE administrador SET
            username = ?,
            password = ?,
            email = ?,
            nivel_acesso = ?,
            status = ?,
            ultimo_login = ?
            WHERE id_admin = ?
        """;

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, admin.getUsername());
            stmt.setString(2, admin.getPassword());
            stmt.setString(3, admin.getEmail());
            stmt.setInt(4, admin.getNivelAcesso());
            stmt.setString(5, admin.getStatus());

            if (admin.getUltimoLogin() != null)
                stmt.setTimestamp(6, Timestamp.valueOf(admin.getUltimoLogin()));
            else
                stmt.setNull(6, Types.TIMESTAMP);

            stmt.setInt(7, admin.getIdAdmin());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM administrador WHERE id_admin = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Administrador mapear(ResultSet rs) throws SQLException {
        Administrador admin = new Administrador();

        admin.setIdAdmin(rs.getInt("id_admin"));
        admin.setUsername(rs.getString("username"));
        admin.setPassword(rs.getString("password"));
        admin.setEmail(rs.getString("email"));
        admin.setNivelAcesso(rs.getInt("nivel_acesso"));
        admin.setStatus(rs.getString("status"));

        Timestamp criado = rs.getTimestamp("criado_em");
        if (criado != null)
            admin.setCriadoEm(criado.toLocalDateTime());

        Timestamp login = rs.getTimestamp("ultimo_login");
        if (login != null)
            admin.setUltimoLogin(login.toLocalDateTime());

        return admin;
    }
}
