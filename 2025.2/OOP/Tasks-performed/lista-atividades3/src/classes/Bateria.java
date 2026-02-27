package classes;

public class Bateria {
    private int capacidadeMah;
    private int porcentagemAtual;

    public Bateria(int capacidadeMah, int porcentagemAtual) {
        this.capacidadeMah = capacidadeMah;
        this.porcentagemAtual = porcentagemAtual;
    }
    
    public int getCapacidadeMah() {
        return capacidadeMah;
    }

    public void setCapacidadeMah(int capacidadeMah) {
        if(capacidadeMah < 1) {
            System.out.println("Capacidade deve ser maior que 0.");
        } else {
            this.capacidadeMah = capacidadeMah;
        }
    }

    public int getPorcentagemAtual() {
        return porcentagemAtual;
    }

    public void setPorcentagemAtual(int porcentagemAtual) {
        if (porcentagemAtual < 0) {
            this.porcentagemAtual = 0;
        } else if (porcentagemAtual > 100) {
            this.porcentagemAtual = 100;
        } else {
            this.porcentagemAtual = porcentagemAtual;
        }
    }
}
