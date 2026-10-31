public class Main {
    public static void main(String[] args) {
        try {
            for (int i = 1; i <= 10; i++) {
                String nomeEntrada = String.format("D://TESTE-%02d.txt", i);
                String nomeSaida = String.format("D://TESTE-%02d-RESULTADO.txt", i);

                System.out.println("Processando " + nomeEntrada + "...");

                EntradaMemoria entrada = GerenciarArquivos.lerArquivo(nomeEntrada);

                //Otimo otimo = new Otimo();
                //int faltasOtimo = otimo.calcular(entrada);

                //NRU nru = new NRU();
                //int faltasNRU = nru.calcular(entrada);

                Relogio relogio = new Relogio();
                int faltasRelogio = relogio.calcular(entrada);

                WSClock wsClock = new WSClock();
                int faltasWSClock = wsClock.calcular(entrada);

                GerenciarArquivos.escreverArquivos(nomeSaida,
                        //faltasOtimo,
                        //faltasNRU,
                        faltasRelogio,
                        faltasWSClock);

                System.out.println(nomeSaida + " feito");
            }

            System.out.println("Todos os arquivos criados");
        } catch (IOException e) {
            System.out.println("Erro lendo ou escrevendo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}