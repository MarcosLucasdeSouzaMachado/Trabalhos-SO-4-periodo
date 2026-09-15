import java.util.*;

public class SRT {

    private List<ProcessoEscalonador> processos;

    public SRT(List<ProcessoEscalonador> processos) {
        for (ProcessoEscalonador processo : processos) {
            processo.resetar();
        }
        this.processos = processos;
    }

    public void executar(){
        for (ProcessoEscalonador p:processos){
            p.resetar();
        }

        int n=processos.size();
        int procesComplet=0;
        float tempAtual=0;

        while (procesComplet<n) {
            int indMen = -1;
            Float menTemp = Float.MAX_VALUE;

            for (int i=0; i<n; i++){
                ProcessoEscalonador p=processos.get(i);
                if (p.chegada<=tempAtual && p.restante>0 && p.restante<menTemp){
                    menTemp = p.restante;
                    indMen = i;
                }
            }
            //avanço de tempo
            if (indMen == -1){
                tempAtual++;
                continue;
            }
            //executore
            ProcessoEscalonador p=processos.get(indMen);
            if (p.restante == p.duracao) {
                p.resposta = tempAtual;
            }
            p.restante--;
            tempAtual++;
            //finalização
            if (p.restante==0){
                p.conclusao=tempAtual;
                p.turnaround=p.conclusao- p.chegada;
                p.espera=p.turnaround-p.duracao;
                procesComplet++;
            }
        }
    }
    public float getTempoRespostaMedio() {
        float soma=0;
        for (ProcessoEscalonador p:processos){
            soma+= (p.resposta - p.chegada);
        }
        return soma/processos.size();
    }
    public float getTempoEsperaMedio(){
        float soma =0;
        for (ProcessoEscalonador p:processos){
            soma +=p.espera;
        }
        return soma/processos.size();
    }
    public float getTempoTurnaroundMedio() {
        float soma=0;
        for (ProcessoEscalonador p:processos){
            soma += p.turnaround;
        }
        return soma/ processos.size();
    }
}