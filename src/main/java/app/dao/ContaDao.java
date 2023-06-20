package main.java.app.dao;

import main.java.app.model.Conta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContaDao {
    private Connection conexao;

    public ContaDao(Connection conexao) {
        this.conexao = conexao;
    }

    public void criarConta(Conta conta) throws SQLException {
        String sql = "INSERT INTO contas (numero, titular, saldo) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, conta.getNumero());
            stmt.setString(2, conta.getTitular());
            stmt.setDouble(3, conta.getSaldo());

            stmt.executeUpdate();
        }
    }

    public Conta buscarContaPorNumero(int numero) throws SQLException {
        String sql = "SELECT * FROM contas WHERE numero = ?";
        Conta conta = null;

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, numero);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int numeroConta = rs.getInt("numero");
                    String titular = rs.getString("titular");
                    double saldo = rs.getDouble("saldo");

                    conta = new Conta(numeroConta, titular, saldo);
                }
            }
        }

        return conta;
    }

    public void atualizarConta(Conta conta) throws SQLException {
        String sql = "UPDATE contas SET saldo = ? WHERE numero = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setDouble(1, conta.getSaldo());
            stmt.setInt(2, conta.getNumero());

            stmt.executeUpdate();
        }
    }

    public double consultarSaldo(int numero) throws SQLException {
        String sql = "SELECT saldo FROM contas WHERE numero = ?";
        double saldo = 0;

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, numero);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    saldo = rs.getDouble("saldo");
                }
            }
        }

        return saldo;
    }
}
