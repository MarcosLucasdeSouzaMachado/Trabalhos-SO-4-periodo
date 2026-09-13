import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            for (int i=1; i<=10; i++) {
                String nomeEntrada = String.format("TESTE-%02d%.txt", i);
                String nomeSaida= String.format("TESTE-%02D%-RESULTADO.txt", i);

                System.out.println("Processando " + nomeEntrada + "...");
                //Ler arquivos
                List<ProcessoEscalonador> processos = GerenciarArquivos.lerArquivo(nomeEntrada);
                if (processos.isEmpty()) {
                    //lembrar de remover o imbecil
                    System.out.println("SEM ARQUIVO INSIRA UM PENDRIVE SEU IMBECIL");
                    continue;
                }
                // usar isso aqui pra definir o quantum que está em gerenciar arquivos pfvr.
                //int quantum = GerenciarArquivos.lerArquivo();

                //Executar os algoritimos

                //adicionar FIFO aqui

                //SJF
                SJF.executar(processos);
                double respSJF = SJF.getTempoRespostaMedio(processos);
                double espSJF = SJF.getTempoEsperaMedio(processos);
                double turnSJF = SJF.getTempoTurnaroundMedio(processos);
                //SRT
                SRT.executar(processos);
                double respSRT = SRT.getTempoRespostaMedio(processos);
                double espSRT = SRT.getTempoEsperaMedio(processos);
                double turnSRT = SRT.getTempoTurnaroundmedio(processos);

                //adicionar RR aqui

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