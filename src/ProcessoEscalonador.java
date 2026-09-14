public class ProcessoEscalonador {
    public int  chegada, burst, restante, conclusao, espera, turnaround;
    /*
    int chegada: momento que o processo chega
    int burst; tempo totalde CPU
    int restante: tempo restante (mlk isso aqui é só pro SRT mais por favor nn apaga)
    int conclusão: quando esse treco termina?
    int espera: tempo total em espera
    int turnaround: eu acho que é autoexplicativo
    */
    public ProcessoEscalonador(int chegada, int burst) {
        this.chegada=chegada;
        this.burst=burst;
        this.restante=burst;
        this.conclusao=0;
        this.espera=0;
        this.turnaround=0;
    }

    public void resetar(){
        this.restante=burst;
        this.conclusao=0;
        this.espera=0;
        this.turnaround=0;
    }
}
