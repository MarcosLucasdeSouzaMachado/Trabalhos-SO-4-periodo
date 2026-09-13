import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AcessarPendrive {
    public static void main(String[] args) {
        Path caminhoArquivo = Paths.get("E:/dados.txt");
        try {
            String conteudo = new String(Files.readAllBytes(caminhoArquivo));
            System.out.println("Conteudo do arquivo: ");
            System.out.println(conteudo);
        } catch (IOException e) {
            System.out.println("Erro nenhum pendrive conectado: " + e.getMessage());
        }
    }
}
