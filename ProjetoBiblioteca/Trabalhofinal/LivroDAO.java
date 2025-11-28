public void cadastrar(Livro livro) {
    String sql = "INSERT INTO livros (titulo, autor, isbn, disponivel) VALUES (?, ?, ?, ?)";
    
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        
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