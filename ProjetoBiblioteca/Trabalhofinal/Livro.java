

    // Livro.java
public class Livro {
    private int id;
    private String titulo;
    private String autor;
    private String isbn;
    private boolean disponivel;
    
    // Encapsulamento
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
    
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    
    public boolean isDisponivel() { return disponivel; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }
    
    public void emprestar() {
        if (disponivel) {
            disponivel = false;
            System.out.println("Livro emprestado com sucesso!");
        } else {
            System.out.println("Livro indisponível!");
        }
    }
    
    public void devolver() {
        disponivel = true;
        System.out.println("Livro devolvido com sucesso!");
    }
    
    public void consultar() {
        System.out.println("Título: " + titulo + " | Autor: " + autor + 
                          " | Disponível: " + (disponivel ? "Sim" : "Não"));
    }
}

