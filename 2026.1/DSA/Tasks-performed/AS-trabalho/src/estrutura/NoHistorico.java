package estrutura;

import model.HistoricoRodada;

public class NoHistorico {
    private HistoricoRodada historico;
    private NoHistorico proximo;

    public NoHistorico(HistoricoRodada historico) {
        this.historico = historico;
    }

    public HistoricoRodada getHistorico() {
        return historico;
    }

    public NoHistorico getProximo() {
        return proximo;
    }

    public void setProximo(NoHistorico proximo) {
        this.proximo = proximo;
    }
}