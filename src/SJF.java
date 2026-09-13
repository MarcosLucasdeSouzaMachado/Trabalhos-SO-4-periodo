import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class ProcessoSJF {
    int chegada, burst, espera, turnaround;
    /*
        burst vai ser o tempo total de execução necessario
    */

    public ProcessoSJF(int chegada, int burst){
        this.chegada=chegada;
        this.burst=burst;
    }
}
public class SJF {
    public static void main(String[] args){
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



    }
}
