import java.util.ArrayList;
import java.util.List;

class ProcessoSRT {
    int id, chegada, burst, restante, conclusao, espera, turnaround;
    /*
        burst vai ser o tempo total de execução necessario
        restante é o tempo restante de execução
    */

    public ProcessoSRT(int id, int chegada, int burst) {
        this.id = id;
        this.chegada = chegada;
        this.burst = burst;
        this.restante = burst;
    }
}
public class SRT {
    public static void exec(List<ProcessoEscalonador> processos){

    }
    public static void main(String[] args) {
        List<ProcessoSRT> processos = new ArrayList<>();
        //processos usados de teste abaixo
        processos.add(new ProcessoSRT(1, 0, 7));
        processos.add(new ProcessoSRT(2, 2, 4));
        processos.add(new ProcessoSRT(3, 4, 1));
        processos.add(new ProcessoSRT(4, 5, 4));

        int n = processos.size();
        int completo=0, tempAtual=0, menTemp=Integer.MAX_VALUE;
        int indMen=-1;
        boolean encontrou = false;

        while (completo<n){
            encontrou = false;
            menTemp = Integer.MAX_VALUE;

            //Esse for aqui só serve pra encontrar os processos que ja estão com o menor tempo restante (NÃO APAGA)
            for (int i=0; i<n; i++){
                ProcessoSRT p = processos.get(i);
                if (p.chegada <= tempAtual && p.restante > 0 && p.restante < menTemp){
                    menTemp=p.restante;
                    indMen=i;
                    encontrou = true;
                }
            }

            if (!encontrou){
                tempAtual++;
                continue;
            }

            processos.get(indMen).restante--;
            tempAtual++;

            if (processos.get(indMen).restante==0) {
                completo++;
                ProcessoSRT p=processos.get(indMen);
                p.conclusao=tempAtual;
                p.turnaround=p.conclusao-p.chegada;
                p.espera=p.turnaround-p.burst;
            }
        }

        System.out.println("ID\\tChegada\\tBurst\\tConclusão\\tEspera\\tTurnaround");
        double TtlEsp = 0, TtlTurnaround = 0;
        for (ProcessoSRT p:processos){
            TtlEsp +=p.espera;
            TtlTurnaround +=p.turnaround;
            System.out.println(p.id + "\t" + p.chegada + "\t" + p.burst + "\t" + p.conclusao + "\t\t" + p.espera + "\t" + p.turnaround);
        }

        System.out.println("\nTempo médio de espera: " + (TtlEsp/n));
        System.out.println("Tempo médio de turnaround: " + (TtlTurnaround/n));
    }


}
