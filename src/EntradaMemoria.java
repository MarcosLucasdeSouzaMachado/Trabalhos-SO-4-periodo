import java.util.List;

public class EntradaMemoria {
    public int paginasVirtuais;
    public int molduras;
    public int cicloResetR;
    public List<Referencia> referencias;

    public EntradaMemoria(int paginasVirtuais, int molduras, int cicloResetR, List<Referencia> referencias) {
        this.paginasVirtuais = paginasVirtuais;
        this.molduras = molduras;
        this.cicloResetR = cicloResetR;
        this.referencias = referencias;
    }
}