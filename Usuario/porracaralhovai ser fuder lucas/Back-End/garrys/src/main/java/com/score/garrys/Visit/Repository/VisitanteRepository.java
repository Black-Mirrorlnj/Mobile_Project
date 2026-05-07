package com.score.garrys.Visit.Repository;

import com.score.garrys.Visit.Model.Visitante;
import com.score.garrys.Visit.Config.Conexao;

import java.sql.*;
import java.util.*;

public class VisitanteRepository {

    public void registrarEntrada(String nome) {
        try (Connection conn = Conexao.getConnection()) {
            CallableStatement stmt = conn.prepareCall("{CALL registrar_entrada(?)}");
            stmt.setString(1, nome);
            stmt.execute();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao registrar entrada", e);
        }
    }

    public void registrarSaida(int id, int kills) {
        try (Connection conn = Conexao.getConnection()) {
            CallableStatement stmt = conn.prepareCall("{CALL registrar_saida(?, ?)}");
            stmt.setInt(1, id);
            stmt.setInt(2, kills);
            stmt.execute();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao registrar saída", e);
        }
    }

    public void remover(int id) {
        try (Connection conn = Conexao.getConnection()) {
            CallableStatement stmt = conn.prepareCall("{CALL remover_visitante(?)}");
            stmt.setInt(1, id);
            stmt.execute();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao remover visitante", e);
        }
    }

    public List<Visitante> listar() {
        List<Visitante> lista = new ArrayList<>();

        try (Connection conn = Conexao.getConnection()) {
            CallableStatement stmt = conn.prepareCall("{CALL listar_visitantes()}");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Visitante v = new Visitante();
                v.setIdVisitante(rs.getInt("id_visitante"));
                v.setNome(rs.getString("nome_usuario"));
                v.setKills(rs.getInt("kills"));

                Timestamp entrada = rs.getTimestamp("horario_entrada");
                if (entrada != null) v.setHorarioEntrada(entrada.toLocalDateTime());

                Timestamp saida = rs.getTimestamp("horario_saida");
                if (saida != null) v.setHorarioSaida(saida.toLocalDateTime());

                lista.add(v);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar visitantes", e);
        }

        return lista;
    }

    public List<Visitante> ranking() {
        List<Visitante> lista = new ArrayList<>();

        try (Connection conn = Conexao.getConnection()) {
            CallableStatement stmt = conn.prepareCall("{CALL ranking_kills()}");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Visitante v = new Visitante();
                v.setNome(rs.getString("nome_usuario"));
                v.setKills(rs.getInt("kills"));
                lista.add(v);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar ranking", e);
        }

        return lista;
    }
}
