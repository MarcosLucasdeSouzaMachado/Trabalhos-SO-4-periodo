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
    /*public static void main(String[] args){
        List<ProcessoSJF> processos = new ArrayList<>();
        //processos usados de teste abaixo
        processos.add(new ProcessoSJF(1, 6));
        processos.add(new ProcessoSJF(2, 2));
        processos.add(new ProcessoSJF(3, 8));
        processos.add(new ProcessoSJF(4, 3));

        processos.sort(Comparator.comparingInt(p -> p.burst));

        int tempAtual=0;
        int somaEspera=0;
        int somaTurnaround=0;

        System.out.println("Ordem de Execução: ");
        for (ProcessoSJF p:processos){
            p.espera = tempAtual;
            p.turnaround=p.espera+p.burst;
            tempAtual+=p.burst;

            somaEspera += p.espera;
            somaTurnaround += p.turnaround;

            System.out.println(p.chegada + " (burst: " + p.burst + ") --> espera: " + p.espera+ " | turnaround: " + p.turnaround);
        }

        double MediaEspera = (double) somaEspera/processos.size();
        double MediaTurnaround = (double) somaTurnaround / processos.size();

        System.out.println("\nTempo médio de espera: " + MediaEspera);
        System.out.println("Tempo médio de turnaround: " + MediaTurnaround);



    }*/
}
