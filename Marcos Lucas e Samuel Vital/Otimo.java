public class Otimo {
    public int calcular(EntradaMemoria entrada) {
        int molduras = entrada.molduras;
        Pagina[] frames = new Pagina[molduras];
        int faltas = 0;
        int frameAtual = -1;

        for (Referencia ref : entrada.referencias) {
            int paginaBuscada = ref.pagina;
            // verifica se a página está na memória
            int pos = buscaPagina(frames, paginaBuscada);
            frameAtual++;

            if (pos != -1) {
                frames[pos].bitR = true;
                frames[pos].ultimoAcesso = ref.tempo;

                if (ref.operacao == 'W') {
                    frames[pos].bitM = true;
                }

                continue;
            }

            faltas++;

            // procura lugar livre
            int livre = buscaLivre(frames);
            if (livre != -1) {
                frames[livre] = new Pagina(paginaBuscada, true, ref.operacao == 'W', ref.tempo);
                continue;
            }

            // memória cheia: aplicar algoritmo do ótimo
            int vitima = 0;
            int maiorDistancia = -1;
            for (int i = 0; i < frames.length; i++) {
                int proximaUtilizacao = Integer.MAX_VALUE;

                // procura a próxima utilização da página
                // Normalmente, não é possível saber isso, mas aqui temos a previsão do futuro
                for (int j = frameAtual + 1; j < entrada.referencias.size(); j++) {
                    if (entrada.referencias.get(j).pagina == frames[i].numero) {
                        proximaUtilizacao = j;
                        break;
                    }
                }

                //Escolhe a página que será utilizada mais tardiamente
                if (proximaUtilizacao > maiorDistancia) {
                    maiorDistancia = proximaUtilizacao;
                    vitima = i;
                }
            }

            frames[vitima] = new Pagina(paginaBuscada, true, ref.operacao == 'W', ref.tempo);

        }
        return faltas;
    }

    private int buscaPagina(Pagina[] frames, int pagina) {
        for (int i = 0; i < frames.length; i++) {
            if (frames[i] != null && frames[i].numero == pagina) {
                return i;
            }
        }
        return -1;
    }

    private int buscaLivre(Pagina[] frames) {
        for (int i = 0; i < frames.length; i++) {
            if (frames[i] == null) {
                return i;
            }
        }
        return -1;
    }
}
