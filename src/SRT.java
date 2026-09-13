import java.util.*;


public class SRT {
    public static void executar(List<ProcessoEscalonador> processos){
        for (ProcessoEscalonador p:processos){
            p.resetar();
        }

        int n=processos.size();
        int procesComplet=0;
        int tempAtual=0;

        while (procesComplet<n) {
            int indMen = -1;
            int menTemp = Integer.MAX_VALUE;

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
            p.restante--;
            tempAtual++;
            //finalização
            if (p.restante==0){
                p.conclusao=tempAtual;
                p.turnaround=p.conclusao- p.chegada;
                p.espera=p.turnaround-p.burst;
                procesComplet++;
            }
        }
    }
    public static double getTempoRespostaMedio(List<ProcessoEscalonador> processos) {
        double soma=0;
        for (ProcessoEscalonador p:processos){
            soma+=(p.conclusao - p.burst-p.chegada);
        }
        return soma/processos.size();
    }
    public static double getTempoEsperaMedio(List<ProcessoEscalonador> processos){
        double soma =0;
        for (ProcessoEscalonador p:processos){
            soma +=p.espera;
        }
        return soma/processos.size();
    }
    public static double getTempoTurnaroundMedio(List<ProcessoEscalonador> processos) {
        double soma=0;
        for (ProcessoEscalonador p:processos){
            soma += p.turnaround;
        }
        return soma/ processos.size();
    }
    /*public static void main(String[] args) {
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
    }*/


}

