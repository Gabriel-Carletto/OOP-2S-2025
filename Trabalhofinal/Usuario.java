package Trabalhofinal;

public class Usuario extends Pessoa {
    private String matricula;
    private String telefone;
    
    public String getMatricula() { 
        return matricula; 
    }
    
    public void setMatricula(String matricula) { 
        this.matricula = matricula; 
    }
    
    public String getTelefone() { 
        return telefone; 
    }
    
    public void setTelefone(String telefone) { 
        this.telefone = telefone; 
    }
    
    @Override
    public void exibirInformacoes() {
        System.out.println("Usuário: " + getNome() + " | Matrícula: " + matricula);
    }
}