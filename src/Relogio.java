import java.util.*;

public class Relogio {

    public int calcular(EntradaMemoria entrada) {
        int molduras = entrada.molduras;
        Pagina[] frames = new Pagina[molduras];
        int ponteiro = 0;
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

            // falta de página
            faltas++;

            // procura lugar livre
            int livre = buscaLivre(frames);
            if (livre != -1) {
                frames[livre] = new Pagina(paginaBuscada, true, ref.operacao == 'W', ref.tempo);
                continue;
            }

            // memória cheia: aplicar algoritmo do relógio
            while (true) {
                Pagina atual = frames[ponteiro];

                if (atual.bitR == false) {
                    if (atual.bitM == false) {
                        // substitui sem escrever de volta
                        frames[ponteiro] = new Pagina(paginaBuscada, true, ref.operacao == 'W', ref.tempo);
                        ponteiro = (ponteiro + 1) % molduras;
                        break;
                    } else {
                        // página suja: limpa flag M, continua procurando
                        atual.bitM = false;
                        ponteiro = (ponteiro + 1) % molduras;
                    }
                } else {
                    // bit R=1: apaga e avança
                    atual.bitR = false;
                    ponteiro = (ponteiro + 1) % molduras;
                }
            }
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