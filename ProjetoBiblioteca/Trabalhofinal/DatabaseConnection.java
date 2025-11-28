import java.sql.*;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:biblioteca.db";
    
    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(URL);
            criarTabelas(conn);
            System.out.println("✅ Conectado ao SQLite!");
            return conn;
        } catch (SQLException e) {
            System.out.println("❌ Erro SQLite: " + e.getMessage());
            return null;
        }
    }
    
    private static void criarTabelas(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            
            // Tabela livros
            stmt.execute(
                "CREATE TABLE IF NOT EXISTS livros (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "titulo TEXT NOT NULL, " +
                "autor TEXT NOT NULL, " +
                "isbn TEXT UNIQUE NOT NULL, " +
                "disponivel BOOLEAN DEFAULT 1)"
            );
            
            // Tabela pessoas
            stmt.execute(
                "CREATE TABLE IF NOT EXISTS pessoas (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT NOT NULL, " +
                "email TEXT UNIQUE NOT NULL)"
            );
            
            // Tabela usuarios
            stmt.execute(
                "CREATE TABLE IF NOT EXISTS usuarios (" +
                "id INTEGER PRIMARY KEY, " +
                "matricula TEXT UNIQUE NOT NULL, " +
                "telefone TEXT, " +
                "FOREIGN KEY(id) REFERENCES pessoas(id))"
            );
            
            System.out.println("✅ Tabelas criadas/verificadas!");
            
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabelas: " + e.getMessage());
        }
    }
    
    public static void testarConexao() {
        System.out.println("🧪 Testando SQLite...");
        Connection conn = getConnection();
        if (conn != null) {
            try {
                conn.close();
                System.out.println("✅ SQLite funcionando perfeitamente!");
                System.out.println("📁 Banco criado: biblioteca.db");
            } catch (SQLException e) {
                System.out.println("Erro ao fechar: " + e.getMessage());
            }
        }
    }
}