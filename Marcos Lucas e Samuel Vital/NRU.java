public class NRU {
    public int calcular(EntradaMemoria entrada) {
        int molduras = entrada.molduras;
        Pagina[] frames = new Pagina[molduras];
        int faltas = 0;

        for (Referencia ref : entrada.referencias) {
            int paginaBuscada = ref.pagina;
            // verifica se a página está na memória
            int pos = buscaPagina(frames, paginaBuscada);

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
            int classeMenor = 4;
            int quantidadeNaClasse = 0;
            int classe;
            int frames_classe[] = new int[frames.length];

            for (int i = 0; i < frames.length; i++) {
                //Atualiza o bit R de acordo com o ciclo de reset, ou seja, verifica se a página foi referenciada recentemente
                if (entrada.cicloResetR > 0
                        && Math.floorDiv(frames[i].ultimoAcesso, (long) entrada.cicloResetR)
                        < Math.floorDiv(ref.tempo, (long) entrada.cicloResetR)) {
                    frames[i].bitR = false;
                }

                //Classifica a página de acordo com as classes do NRU
                if (!frames[i].bitR && !frames[i].bitM) {
                    classe = 0;
                } else if (!frames[i].bitR) {
                    classe = 1;
                } else if (!frames[i].bitM) {
                    classe = 2;
                } else {
                    classe = 3;
                }

                //Grava os frames, ou páginas, da menor classe para depois escolher aleatoriamente uma para substituir
                if (classe < classeMenor) {
                    classeMenor = classe;
                    quantidadeNaClasse = 1;
                    frames_classe = new int[frames.length];
                    frames_classe[quantidadeNaClasse-1] = i;
                } else if (classe == classeMenor) {
                    quantidadeNaClasse++;
                    frames_classe[quantidadeNaClasse-1] = i;
                }
            }

            //Realiza a escolha aleatória de uma página para substituir
            int escolha = java.util.concurrent.ThreadLocalRandom.current()
                    .nextInt(quantidadeNaClasse);
            frames[frames_classe[escolha]] = new Pagina(paginaBuscada, true, ref.operacao == 'W', ref.tempo);

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
