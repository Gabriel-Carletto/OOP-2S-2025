package atividade1;

public class SistemaOperacional {
    private Computador computador;

    public SistemaOperacional(Computador computador) {
        this.computador = computador;
    }

    public boolean executarPrograma(Programa p) {
        // Verificação de espaço no SSD
        if (p.getSSDOcupado() > computador.getSSD()) {
            System.out.println("Erro: Não há espaço suficiente no SSD para instalar o programa.");
            return false;
        }

        // Verificação de memória RAM
        if (p.getMemoriaRAMAlocada() > computador.getMemoriaRAM()) {
            System.out.println("Erro: Memória RAM insuficiente para executar o programa.");
            return false;
        }

        // Execução com sucesso
        float tempoExecucao = (float) p.getQuantidadeOperacoes() /
                (computador.getOperacoesPorSegundo() * computador.getNucleos());

        System.out.println("Programa executado com sucesso!");
        System.out.println("Tempo de execução: " + tempoExecucao + " segundos.");
        return true;
    }
}