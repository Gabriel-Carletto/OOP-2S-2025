

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {
    
    public void cadastrar(Livro livro) {
        String sql = "INSERT INTO livros (titulo, autor, isbn, disponivel) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getIsbn());
            stmt.setBoolean(4, livro.isDisponivel());
            
            stmt.executeUpdate();
            System.out.println("✅ Livro cadastrado com sucesso!");
            
        } catch (SQLException e) {
            System.out.println("❌ Erro ao cadastrar livro: " + e.getMessage());
        }
    }
    
    // ADICIONE ESTE MÉTODO ↓
    public List<Livro> consultarTodos() {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livros";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Livro livro = new Livro();
                livro.setId(rs.getInt("id"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setIsbn(rs.getString("isbn"));
                livro.setDisponivel(rs.getBoolean("disponivel"));
                livros.add(livro);
            }
            
        } catch (SQLException e) {
            System.out.println("❌ Erro ao consultar livros: " + e.getMessage());
        }
        
        return livros;
    }
    
    // MÉTODO OPCIONAL: Consultar por ID
    public Livro consultarPorId(int id) {
        String sql = "SELECT * FROM livros WHERE id = ?";
        Livro livro = null;
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                livro = new Livro();
                livro.setId(rs.getInt("id"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setIsbn(rs.getString("isbn"));
                livro.setDisponivel(rs.getBoolean("disponivel"));
            }
            
        } catch (SQLException e) {
            System.out.println("❌ Erro ao consultar livro: " + e.getMessage());
        }
        
        return livro;
    }
}