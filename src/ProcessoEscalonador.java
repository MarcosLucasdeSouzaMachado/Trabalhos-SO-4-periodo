public class ProcessoEscalonador {
    public float chegada, duracao, restante, conclusao, resposta, espera, turnaround;
    /*
    float chegada: momento que o processo chega
    float duracao: tempo total de CPU
    float restante: tempo restante (mlk isso aqui é só pro SRT mais por favor nn apaga)
    float conclusão: quando esse treco termina?
    float espera: tempo total em espera
    float turnaround: eu acho que é autoexplicativo
    */
    public ProcessoEscalonador(float chegada, float duracao) {
        this.chegada=chegada;
        this.duracao=duracao;
        this.restante=duracao;
        this.resposta = -1;
        this.conclusao=0;
        this.espera=0;
        this.turnaround=0;
    }

    public void resetar(){
        this.restante=duracao;
        this.conclusao=0;
        this.espera=0;
        this.resposta = -1;
        this.turnaround=0;
    }
}
