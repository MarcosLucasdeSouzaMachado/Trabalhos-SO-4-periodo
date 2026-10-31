public class Referencia {
    public int pagina;
    public long tempo;
    public char operacao; // 'R' ou 'W'

    public Referencia(int pagina, long tempo, char operacao) {
        this.pagina = pagina;
        this.tempo = tempo;
        this.operacao = operacao;
    }
}
