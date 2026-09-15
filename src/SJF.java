import java.util.*;

public class SJF {

    private List<ProcessoEscalonador> processos;

    public SJF(List<ProcessoEscalonador> processos) {
        for (ProcessoEscalonador processo : processos) {
            processo.resetar();
        }
        this.processos = processos;
    }

    public void executar() {
        for (ProcessoEscalonador p:processos) {
            p.resetar();
        }
        int n= processos.size();
        boolean[] executado = new boolean[n];
        float tempAtual=0;
        int processExec=0;

        while (processExec<n) {
            int indMenor= -1;
            float menduracao = Float.MAX_VALUE;

            for (int i=0; i<n; i++){
                ProcessoEscalonador p=processos.get(i);
                if (p.chegada <= tempAtual && !executado[i] && p.duracao < menduracao) {
                    menduracao = p.duracao;
                    indMenor = i;
                }
            }
            //avanço de tempo
            if (indMenor == -1){
                float proxChegd = Float.MAX_VALUE;
                for (int i=0; i<n; i++){
                    if (!executado[i] && processos.get(i).chegada > tempAtual){
                        proxChegd = Math.min(proxChegd, processos.get(i).chegada);
                    }
                }
                tempAtual = proxChegd;
                continue;
            }
            //executor
            ProcessoEscalonador p=processos.get(indMenor);
            p.espera = tempAtual - p.chegada;
            tempAtual += p.duracao;
            p.conclusao = tempAtual;
            p.turnaround = p.conclusao -p.chegada;
            executado[indMenor] = true;
            processExec++;
        }
    }
    public float getTempoRespostaMedio() {
        float soma=0;
        for (ProcessoEscalonador p:processos) {
            soma+=(p.conclusao - p.duracao - p.chegada);
        }
        return soma/processos.size();
    }
    public float getTempoEsperaMedio(){
        float soma=0;
        for (ProcessoEscalonador p:processos) {
            soma+=p.espera;
        }
        return soma/processos.size();
    }
    public float getTempoTurnaroundMedio(){
        float soma=0;
        for (ProcessoEscalonador p:processos){
            soma+=p.turnaround;
        }
        return soma/processos.size();
    }
}
