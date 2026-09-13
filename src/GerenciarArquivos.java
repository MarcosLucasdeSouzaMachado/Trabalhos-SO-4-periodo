import java.io.*;
import java.util.*;

public class GerenciarArquivos {
    public static List<ProcessoEscalonador> lerArquivo(String nomeArquivo) throws IOException{
        List<ProcessoEscalonador> processos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String primaLinha = reader.readLine();
            int r = Integer.parseInt(primaLinha.trim());
            String linha;
            int id=1;
            while ((linha = reader.readLine())!=null) {
                linha = linha.trim();
                if (linha.isEmpty()) continue;

                String[] partes = linha.split("\\s+");
                int chegada=Integer.parseInt(partes[0]);
                int burst=Integer.parseInt(partes[1]);

                processos.add(new ProcessoEscalonador(id++, chegada, burst));
            }
        }
        return processos;
    }

    public static void escreverArquivos(String nomeArquivo,
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
        try (PrintWriter writer = new PrintWriter(new FileWriter(nomeArquivo))){
            writer.printf("%.0f %.3f %.3f\n", tempRespMedioFIFO, tempEspMedioFIFO, tempTurnMedioFIFO);
            writer.printf("%.0f %.3f %.3f\n", tempRespMedioSJF, tempEspMedioSJF, tempTurnMedioSJF);
            writer.printf("%.0f %.3f %.3f\n", tempRespMedioSRT, tempEspMedioSRT, tempTurnMedioSRT);
            writer.printf("%.0f %.3f %.3f\n", tempRespMedioRR, tempEspMedioRR, tempTurnMedioRR);
        }
    }
}
