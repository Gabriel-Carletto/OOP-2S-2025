package atividade1;

public class Main {
    public static void main(String[] args) {
        // Criando um computador
        Computador pc = new Computador(16, 500, 4, 2.5f); // 16GB RAM, 500GB SSD, 4 núcleos, 2.5 ops/s

        // Criando Sistema Operacional
        SistemaOperacional so = new SistemaOperacional(pc);

        // Programas
        Programa prog1 = new Programa(8, 100, 2, 10000);  // Deve rodar com sucesso
        Programa prog2 = new Programa(4, 600, 2, 5000);   // SSD insuficiente
        Programa prog3 = new Programa(32, 100, 2, 20000); // RAM insuficiente

        System.out.println("---- Teste 1 ----");
        so.executarPrograma(prog1);

        System.out.println("---- Teste 2 ----");
        so.executarPrograma(prog2);

        System.out.println("---- Teste 3 ----");
        so.executarPrograma(prog3);
    }
}