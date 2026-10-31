import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            for (int i=1; i<=10; i++) {
                String nomeEntrada = String.format("D://TESTE-%02d.txt", i);
                String nomeSaida= String.format("D://TESTE-%02d-RESULTADO.txt", i);

                System.out.println("Processando " + nomeEntrada + "...");
                //Ler arquivos
                 
                Map<List<ProcessoEscalonador>, Float> retorno = GerenciarArquivos.lerArquivo(nomeEntrada);
                Iterator<Map.Entry<List<ProcessoEscalonador>, Float>> iterator = retorno.entrySet().iterator();
                Map.Entry<List<ProcessoEscalonador>, Float> entrada = iterator.next();
                List<ProcessoEscalonador> processos = entrada.getKey();
                Float quantum = entrada.getValue();

                if (processos.isEmpty()) {
                    System.out.println("SEM ARQUIVO, INSIRA UM PENDRIVE");
                    continue;
                }

                //Executar os algoritimos
                //FIFO 
                FIFO escalonadorFifo = new FIFO(processos);
                escalonadorFifo.escalonar();
                float respFIFO = escalonadorFifo.temp_resp_med; 
                float espFIFO = escalonadorFifo.temp_esp_med;
                float turnFIFO = escalonadorFifo.turnaround_med;
                //SJF
                SJF escalonadorSJF = new SJF(processos);
                escalonadorSJF.executar();
                float respSJF = escalonadorSJF.getTempoRespostaMedio();
                float espSJF = escalonadorSJF.getTempoEsperaMedio();
                float turnSJF = escalonadorSJF.getTempoTurnaroundMedio();
                //SRT
                SRT escalonadorSRT = new SRT(processos);
                escalonadorSRT.executar();
                float respSRT = escalonadorSRT.getTempoRespostaMedio();
                float espSRT = escalonadorSRT.getTempoEsperaMedio();
                float turnSRT = escalonadorSRT.getTempoTurnaroundMedio();
                //RR
                RR escalonadorRR = new RR(processos, quantum);
                escalonadorRR.escalonar();
                float respRR = escalonadorRR.temp_resp_med; 
                float espRR = escalonadorRR.temp_esp_med;
                float turnRR = escalonadorRR.turnaround_med;
                //escritor de resultados
                GerenciarArquivos.escreverArquivos(nomeSaida,
                        respFIFO, espFIFO, turnFIFO,
                        respSJF, espSJF, turnSJF,
                        respSRT, espSRT, turnSRT,
                        respRR, espRR, turnRR);
                System.out.println(nomeSaida + " feito");
            }
            System.out.println("Todos os arquivos criados");
        } catch (IOException e) {
            System.out.println("Erro lendo ou escrevendo: " + e.getMessage());
            e.printStackTrace();
        }

    }
}