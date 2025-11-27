package Trabalhofinal;

// Arquivo: SistemaBiblioteca.java
import java.util.List;
import java.util.Scanner;

public class SistemaBiblioteca {
    private static Scanner scanner = new Scanner(System.in);
    private static LivroDAO livroDAO = new LivroDAO();
    private static PessoaDAO pessoaDAO = new PessoaDAO();
    
    public static void main(String[] args) {
        exibirMenu();
    }
    
    public static void exibirMenu() {
        int opcao;
        
        do {
            System.out.println("\n=== SISTEMA DE BIBLIOTECA ===");
            System.out.println("1. Cadastrar Livro");
            System.out.println("2. Consultar Livros");
            System.out.println("3. Cadastrar Usuário");
            System.out.println("4. Consultar Usuários");
            System.out.println("5. Demonstrar Polimorfismo");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer
            
            switch (opcao) {
                case 1:
                    cadastrarLivro();
                    break;
                case 2:
                    consultarLivros();
                    break;
                case 3:
                    cadastrarUsuario();
                    break;
                case 4:
                    consultarUsuarios();
                    break;
                case 5:
                    demonstrarPolimorfismo();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
            
        } while (opcao != 0);
        
        scanner.close();
    }
    
    private static void cadastrarLivro() {
        System.out.println("\n--- CADASTRAR LIVRO ---");
        
        Livro livro = new Livro();
        
        System.out.print("Título: ");
        livro.setTitulo(scanner.nextLine());
        
        System.out.print("Autor: ");
        livro.setAutor(scanner.nextLine());
        
        System.out.print("ISBN: ");
        livro.setIsbn(scanner.nextLine());
        
        livro.setDisponivel(true);
        
        livroDAO.cadastrar(livro);
    }
    
    private static void consultarLivros() {
        System.out.println("\n--- CONSULTAR LIVROS ---");
        
        List<Livro> livros = livroDAO.consultarTodos();
        
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
        } else {
            System.out.println("Livros cadastrados:");
            for (Livro livro : livros) {
                System.out.println("ID: " + livro.getId() + 
                                 " | Título: " + livro.getTitulo() + 
                                 " | Autor: " + livro.getAutor() + 
                                 " | Disponível: " + (livro.isDisponivel() ? "Sim" : "Não"));
            }
        }
    }
    
    private static void cadastrarUsuario() {
        System.out.println("\n--- CADASTRAR USUÁRIO ---");
        
        Usuario usuario = new Usuario();
        
        System.out.print("Nome: ");
        usuario.setNome(scanner.nextLine());
        
        System.out.print("Email: ");
        usuario.setEmail(scanner.nextLine());
        
        System.out.print("Matrícula: ");
        usuario.setMatricula(scanner.nextLine());
        
        System.out.print("Telefone: ");
        usuario.setTelefone(scanner.nextLine());
        
        pessoaDAO.cadastrarUsuario(usuario);
    }
    
    private static void consultarUsuarios() {
        System.out.println("\n--- CONSULTAR USUÁRIOS ---");
        
        List<Usuario> usuarios = pessoaDAO.consultarUsuarios();
        
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {
            System.out.println("Usuários cadastrados:");
            for (Usuario usuario : usuarios) {
                System.out.println("ID: " + usuario.getId() + 
                                 " | Nome: " + usuario.getNome() + 
                                 " | Email: " + usuario.getEmail() + 
                                 " | Matrícula: " + usuario.getMatricula());
            }
        }
    }
    
    private static void demonstrarPolimorfismo() {
        System.out.println("\n--- DEMONSTRAÇÃO DE POLIMORFISMO ---");
        
        // Criando objetos das classes filhas
        Usuario usuario = new Usuario();
        usuario.setNome("João Silva");
        usuario.setEmail("joao@email.com");
        usuario.setMatricula("2023001");
        
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Maria Santos");
        funcionario.setEmail("maria@email.com");
        funcionario.setCargo("Bibliotecária");
        
        // Array do tipo da classe pai (Polimorfismo)
        Pessoa[] pessoas = {usuario, funcionario};
        
        // Chamada polimórfica do método exibirInformacoes()
        System.out.println("Chamada polimórfica:");
        for (Pessoa pessoa : pessoas) {
            pessoa.exibirInformacoes(); // Comportamento diferente para cada tipo
        }
    }
}