public class Pagina {
    public int numero;
    public boolean bitR;
    public boolean bitM;
    public long ultimoAcesso;

    public Pagina(int numero, boolean bitR, boolean bitM, long ultimoAcesso) {
        this.numero = numero;
        this.bitR = bitR;
        this.bitM = bitM;
        this.ultimoAcesso = ultimoAcesso;
    }
}