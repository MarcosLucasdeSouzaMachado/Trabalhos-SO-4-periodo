public class Otimo {
    public int calcular(EntradaMemoria entrada) {
        int molduras = entrada.molduras;
        Pagina[] frames = new Pagina[molduras];
        int faltas = 0;
        int frameAtual = -1;

        for (Referencia ref : entrada.referencias) {
            int paginaBuscada = ref.pagina;
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

            int livre = buscaLivre(frames);
            if (livre != -1) {
                frames[livre] = new Pagina(paginaBuscada, true, ref.operacao == 'W', ref.tempo);
                continue;
            }

            int vitima = 0;
            int maiorDistancia = -1;
            for (int i = 0; i < frames.length; i++) {
                int proximaUtilizacao = Integer.MAX_VALUE;

                for (int j = frameAtual + 1; j < entrada.referencias.size(); j++) {
                    if (entrada.referencias.get(j).pagina == frames[i].numero) {
                        proximaUtilizacao = j;
                        break;
                    }
                }

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
