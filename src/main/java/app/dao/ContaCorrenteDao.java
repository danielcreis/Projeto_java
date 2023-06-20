package main.java.app.dao;

import main.java.app.model.ContaCorrente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContaCorrenteDao {
    private Connection conexao;

    public ContaCorrenteDao(Connection conexao) {
        this.conexao = conexao;
    }

    public void criarContaCorrente(ContaCorrente contaCorrente) throws SQLException {
        String sql = "INSERT INTO contas_correntes (numero, titular, saldo) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, contaCorrente.getNumero());
            stmt.setString(2, contaCorrente.getTitular());
            stmt.setDouble(3, contaCorrente.getSaldo());

            stmt.executeUpdate();
        }
    }

    public ContaCorrente buscarContaCorrentePorNumero(int numero) throws SQLException {
        String sql = "SELECT * FROM contas_correntes WHERE numero = ?";
        ContaCorrente contaCorrente = null;

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, numero);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int numeroConta = rs.getInt("numero");
                    String titular = rs.getString("titular");
                    double saldo = rs.getDouble("saldo");

                    contaCorrente = new ContaCorrente(numeroConta, titular, saldo);
                }
            }
        }

        return contaCorrente;
    }
}
