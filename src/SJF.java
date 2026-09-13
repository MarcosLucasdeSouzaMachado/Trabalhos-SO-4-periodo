import java.util.*;

public class SJF {
    public static void executar(List<ProcessoEscalonador> processos) {
        for (ProcessoEscalonador p:processos) {
            p.resetar();
        }
        int n= processos.size();
        boolean[] executado = new boolean[n];
        int tempAtual=0;
        int processExec=0;

        while (processExec<n) {
            int indMenor= -1;
            int menBurst = Integer.MAX_VALUE;

            for (int i=0; i<n; i++){
                ProcessoEscalonador p=processos.get(i);
                if (p.chegada <= tempAtual && !executado[i] && p.burst < menBurst) {
                    menBurst = p.burst;
                    indMenor = i;
                }
            }
            //avanço de tempo
            if (indMenor == -1){
                int proxChegd = Integer.MAX_VALUE;
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
            tempAtual += p.burst;
            p.conclusao = tempAtual;
            p.turnaround = p.conclusao -p.chegada;
            executado[indMenor] = true;
            processExec++;
        }
    }
    public static double getTempoRespostaMedio(List<ProcessoEscalonador> processos) {
        double soma=0;
        for (ProcessoEscalonador p:processos) {
            soma+=(p.conclusao - p.burst - p.chegada);
        }
        return soma/processos.size();
    }
    public static double getTempoEsperaMedio(List<ProcessoEscalonador> processos){
        double soma=0;
        for (ProcessoEscalonador p:processos) {
            soma+=p.espera;
        }
        return soma/processos.size();
    }
    public static double getTempoTurnaroundMedio(List<ProcessoEscalonador> processos){
        double soma=0;
        for (ProcessoEscalonador p:processos){
            soma+=p.turnaround;
        }
        return soma/processos.size();
    }
}
