import java.util.*;

public class WSClock {

    public int calcular(EntradaMemoria entrada) {
        int molduras = entrada.molduras;
        Pagina[] frames = new Pagina[molduras];
        int ponteiro = 0;
        int faltas = 0;

        for (Referencia ref : entrada.referencias) {
            int paginaBuscada = ref.pagina;

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

            int livre = buscaLivre(frames);
            if (livre != -1) {
                frames[livre] = new Pagina(paginaBuscada, true, ref.operacao == 'W', ref.tempo);
                continue;
            }

            while (true) {
                Pagina atual = frames[ponteiro];

                long idade = ref.tempo - atual.ultimoAcesso;

                // condição WSClock:
                // se não foi referenciada recentemente e não é suja, troca
                if (atual.bitR == false && atual.bitM == false) {
                    frames[ponteiro] = new Pagina(paginaBuscada, true, ref.operacao == 'W', ref.tempo);
                    ponteiro = (ponteiro + 1) % molduras;
                    break;
                }

                // se não foi referenciada recentemente, mas está suja, pode ser limpa
                if (atual.bitR == false && atual.bitM == true && idade >= entrada.cicloResetR) {
                    frames[ponteiro] = new Pagina(paginaBuscada, true, ref.operacao == 'W', ref.tempo);
                    ponteiro = (ponteiro + 1) % molduras;
                    break;
                }

                // se foi usada recentemente, zera R e avança
                atual.bitR = false;
                ponteiro = (ponteiro + 1) % molduras;
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