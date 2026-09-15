import java.io.*;
import java.util.*;

public class GerenciarArquivos {

    public static Map<List<ProcessoEscalonador>, Float> lerArquivo(String nomeArquivo) throws IOException{
        List<ProcessoEscalonador> processos = new ArrayList<>();
        Float quantum = 0.0f;
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String primaLinha = reader.readLine();
            quantum = Float.parseFloat(primaLinha.trim());
            String linha;
            while ((linha = reader.readLine())!=null) {
                linha = linha.trim();
                if (linha.isEmpty()) continue;

                String[] partes = linha.split("\\s+");
                float chegada=Float.parseFloat(partes[0]);
                float duracao=Float.parseFloat(partes[1]);

                processos.add(new ProcessoEscalonador(chegada, duracao));
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo");
        }
        Map<List<ProcessoEscalonador>, Float> retorno = new HashMap<List<ProcessoEscalonador>, Float> ();
        retorno.put(processos, quantum);
        return retorno;
    }

    public static void escreverArquivos(String nomeSaida,
                                        double tempRespMedioFIFO,
                                        double tempEspMedioFIFO,
                                        double tempTurnMedioFIFO,
                                        double tempRespMedioSJF,
                                        double tempEspMedioSJF,
                                        double tempTurnMedioSJF,
                                        double tempRespMedioSRT,
                                        double tempEspMedioSRT,
                                        double tempTurnMedioSRT,
                                        double tempRespMedioRR,
                                        double tempEspMedioRR,
                                        double tempTurnMedioRR) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nomeSaida))){
            writer.printf("%.3f %.3f %.3f\n", tempRespMedioFIFO, tempEspMedioFIFO, tempTurnMedioFIFO);
            writer.printf("%.3f %.3f %.3f\n", tempRespMedioSJF, tempEspMedioSJF, tempTurnMedioSJF);
            writer.printf("%.3f %.3f %.3f\n", tempRespMedioSRT, tempEspMedioSRT, tempTurnMedioSRT);
            writer.printf("%.3f %.3f %.3f\n", tempRespMedioRR, tempEspMedioRR, tempTurnMedioRR);
        }
    }

}
