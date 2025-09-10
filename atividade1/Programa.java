package atividade1;

public class Programa {
    private int memoriaRAMAlocada;
    private int SSDOcupado;
    private int nucleos;
    private int quantidadeOperacoes;

    public Programa(int memoriaRAMAlocada, int SSDOcupado, int nucleos, int quantidadeOperacoes) {
        this.memoriaRAMAlocada = memoriaRAMAlocada;
        this.SSDOcupado = SSDOcupado;
        this.nucleos = nucleos;
        this.quantidadeOperacoes = quantidadeOperacoes;
    }

    // Getters e Setters
    public int getMemoriaRAMAlocada() {
        return memoriaRAMAlocada;
    }

    public void setMemoriaRAMAlocada(int memoriaRAMAlocada) {
        this.memoriaRAMAlocada = memoriaRAMAlocada;
    }

    public int getSSDOcupado() {
        return SSDOcupado;
    }

    public void setSSDOcupado(int SSDOcupado) {
        this.SSDOcupado = SSDOcupado;
    }

    public int getNucleos() {
        return nucleos;
    }

    public void setNucleos(int nucleos) {
        this.nucleos = nucleos;
    }

    public int getQuantidadeOperacoes() {
        return quantidadeOperacoes;
    }

    public void setQuantidadeOperacoes(int quantidadeOperacoes) {
        this.quantidadeOperacoes = quantidadeOperacoes;
    }
}