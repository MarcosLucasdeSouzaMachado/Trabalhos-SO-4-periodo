import java.io.*;
import java.util.*;

public class GerenciarArquivos {

    public static EntradaMemoria lerArquivo(String nomeArquivo) throws IOException {
        List<Referencia> referencias = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;

            linha = reader.readLine();
            if (linha == null) throw new IOException("Arquivo vazio");
            int paginasVirtuais = Integer.parseInt(linha.trim());

            linha = reader.readLine();
            if (linha == null) throw new IOException("Falta o valor de molduras");
            int molduras = Integer.parseInt(linha.trim());

            linha = reader.readLine();
            if (linha == null) throw new IOException("Falta o ciclo de relógio");
            int cicloResetR = Integer.parseInt(linha.trim());

            while ((linha = reader.readLine()) != null) {
                linha = linha.trim();
                if (linha.isEmpty()) continue;

                String[] partes = linha.split("\\s+");
                if (partes.length != 3) continue;

                int pagina = Integer.parseInt(partes[0]);
                long tempo = Long.parseLong(partes[1]);
                char operacao = partes[2].charAt(0);

                referencias.add(new Referencia(pagina, tempo, operacao));
            }

            return new EntradaMemoria(paginasVirtuais, molduras, cicloResetR, referencias);
        }
    }

    public static void escreverArquivos(String nomeSaida,
                                        int faltasOtimo,
                                        int faltasNRU,
                                        int faltasRelogio,
                                        int faltasWSClock) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nomeSaida))) {
            writer.println(faltasOtimo);
            writer.println(faltasNRU);
            writer.println(faltasRelogio);
            writer.println(faltasWSClock);
        }
    }
}