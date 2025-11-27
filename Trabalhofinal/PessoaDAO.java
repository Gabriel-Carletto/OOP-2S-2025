package Trabalhofinal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {
    
    public void cadastrarUsuario(Usuario usuario) {
        String sqlPessoa = "INSERT INTO pessoas (nome, email) VALUES (?, ?)";
        String sqlUsuario = "INSERT INTO usuarios (id, matricula, telefone) VALUES (?, ?, ?)";
        
        Connection conn = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);
            
            // Inserir na tabela pessoas
            try (PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa, Statement.RETURN_GENERATED_KEYS)) {
                stmtPessoa.setString(1, usuario.getNome());
                stmtPessoa.setString(2, usuario.getEmail());
                stmtPessoa.executeUpdate();
                
                // Obter o ID gerado
                ResultSet rs = stmtPessoa.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    usuario.setId(id); // Atualiza o ID no objeto
                    
                    // Inserir na tabela usuarios
                    try (PreparedStatement stmtUsuario = conn.prepareStatement(sqlUsuario)) {
                        stmtUsuario.setInt(1, id);
                        stmtUsuario.setString(2, usuario.getMatricula());
                        stmtUsuario.setString(3, usuario.getTelefone());
                        stmtUsuario.executeUpdate();
                    }
                }
            }
            
            conn.commit();
            System.out.println("Usuário cadastrado com sucesso!");
            
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    System.out.println("Erro no rollback: " + ex.getMessage());
                }
            }
            System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar conexão: " + e.getMessage());
                }
            }
        }
    }
    
    public void cadastrarFuncionario(Funcionario funcionario) {
        String sqlPessoa = "INSERT INTO pessoas (nome, email) VALUES (?, ?)";
        String sqlFuncionario = "INSERT INTO funcionarios (id, cargo, salario) VALUES (?, ?, ?)";
        
        Connection conn = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);
            
            // Inserir na tabela pessoas
            try (PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa, Statement.RETURN_GENERATED_KEYS)) {
                stmtPessoa.setString(1, funcionario.getNome());
                stmtPessoa.setString(2, funcionario.getEmail());
                stmtPessoa.executeUpdate();
                
                // Obter o ID gerado
                ResultSet rs = stmtPessoa.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    funcionario.setId(id); // Atualiza o ID no objeto
                    
                    // Inserir na tabela funcionarios
                    try (PreparedStatement stmtFuncionario = conn.prepareStatement(sqlFuncionario)) {
                        stmtFuncionario.setInt(1, id);
                        stmtFuncionario.setString(2, funcionario.getCargo());
                        stmtFuncionario.setDouble(3, funcionario.getSalario());
                        stmtFuncionario.executeUpdate();
                    }
                }
            }
            
            conn.commit();
            System.out.println("Funcionário cadastrado com sucesso!");
            
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    System.out.println("Erro no rollback: " + ex.getMessage());
                }
            }
            System.out.println("Erro ao cadastrar funcionário: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar conexão: " + e.getMessage());
                }
            }
        }
    }
    
    public List<Usuario> consultarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT p.*, u.matricula, u.telefone FROM pessoas p " +
                    "JOIN usuarios u ON p.id = u.id";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setMatricula(rs.getString("matricula"));
                usuario.setTelefone(rs.getString("telefone"));
                usuarios.add(usuario);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao consultar usuários: " + e.getMessage());
        }
        
        return usuarios;
    }
    
    public List<Funcionario> consultarFuncionarios() {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT p.*, f.cargo, f.salario FROM pessoas p " +
                    "JOIN funcionarios f ON p.id = f.id";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(rs.getInt("id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setCargo(rs.getString("cargo"));
                funcionario.setSalario(rs.getDouble("salario"));
                funcionarios.add(funcionario);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao consultar funcionários: " + e.getMessage());
        }
        
        return funcionarios;
    }
    
    public List<Pessoa> consultarTodasPessoas() {
        List<Pessoa> pessoas = new ArrayList<>();
        
        // Consulta para usuários
        String sqlUsuarios = "SELECT p.*, u.matricula, u.telefone FROM pessoas p " +
                           "JOIN usuarios u ON p.id = u.id";
        
        // Consulta para funcionários
        String sqlFuncionarios = "SELECT p.*, f.cargo, f.salario FROM pessoas p " +
                               "JOIN funcionarios f ON p.id = f.id";
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            
            // Consultar usuários
            try (PreparedStatement stmt = conn.prepareStatement(sqlUsuarios);
                 ResultSet rs = stmt.executeQuery()) {
                
                while (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getInt("id"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setMatricula(rs.getString("matricula"));
                    usuario.setTelefone(rs.getString("telefone"));
                    pessoas.add(usuario);
                }
            }
            
            // Consultar funcionários
            try (PreparedStatement stmt = conn.prepareStatement(sqlFuncionarios);
                 ResultSet rs = stmt.executeQuery()) {
                
                while (rs.next()) {
                    Funcionario funcionario = new Funcionario();
                    funcionario.setId(rs.getInt("id"));
                    funcionario.setNome(rs.getString("nome"));
                    funcionario.setEmail(rs.getString("email"));
                    funcionario.setCargo(rs.getString("cargo"));
                    funcionario.setSalario(rs.getDouble("salario"));
                    pessoas.add(funcionario);
                }
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao consultar pessoas: " + e.getMessage());
        }
        
        return pessoas;
    }
    
    public Usuario consultarUsuarioPorId(int id) {
        String sql = "SELECT p.*, u.matricula, u.telefone FROM pessoas p " +
                    "JOIN usuarios u ON p.id = u.id WHERE p.id = ?";
        Usuario usuario = null;
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setMatricula(rs.getString("matricula"));
                usuario.setTelefone(rs.getString("telefone"));
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao consultar usuário: " + e.getMessage());
        }
        
        return usuario;
    }
}