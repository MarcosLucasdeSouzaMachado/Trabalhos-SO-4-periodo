import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;

public class RR {
    private float quantum;
    private List<ProcessoEscalonador> processos;
    public float temp_esp_med;// todo tempo que o processo não é executado
    public float temp_resp_med;// chegada até primeira execução
    public float turnaround_med;// chegada a conclusão total

    public RR(List<ProcessoEscalonador> processos, Float quantum) {
                for (ProcessoEscalonador processo : processos) {
                    processo.resetar();
        }
        this.processos = processos;
        temp_esp_med = 0;
        temp_resp_med = 0;
        turnaround_med = 0;
        this.quantum = quantum;
    }

    public void escalonar() {
        float tempoExecutado;
        float tempo = 0;
        int num_procs = processos.size();
        int proximo = 0;
        Queue<ProcessoEscalonador> fila;
        ProcessoEscalonador processo;

        processos.sort(Comparator.comparingDouble(p -> p.chegada));
        fila = new ArrayDeque<>();

        while (proximo < num_procs || !fila.isEmpty()) {
            if (fila.isEmpty() && proximo < num_procs) {
                tempo = Math.max(tempo, processos.get(proximo).chegada);
            }

            while (proximo < num_procs && processos.get(proximo).chegada <= tempo) {
                fila.add(processos.get(proximo));
                proximo++;
            }

            processo = fila.poll();

            if (processo.resposta < 0) {
                processo.resposta = tempo;
                temp_resp_med += tempo - processo.chegada;
            }

            tempoExecutado = Math.min(quantum, processo.restante);
            processo.restante -= tempoExecutado;
            tempo += tempoExecutado;

            while (proximo < num_procs && processos.get(proximo).chegada <= tempo) {
                fila.add(processos.get(proximo));
                proximo++;
            }

            if (processo.restante > 0) {
                fila.add(processo);
            } else {
                processo.conclusao = tempo;
                turnaround_med += processo.conclusao - processo.chegada;
                if ((processo.conclusao - processo.chegada) > 0) {
                    temp_esp_med += (processo.conclusao - processo.chegada) - processo.duracao;
                }
            }
        }

        temp_resp_med = temp_resp_med / num_procs;
        temp_esp_med = temp_esp_med / num_procs;
        turnaround_med = turnaround_med / num_procs;

    }

}