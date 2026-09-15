import java.util.List;
import java.util.Comparator;

public class FIFO {

    private float count_delta;
    private List<ProcessoEscalonador> processos;
    public float temp_esp_med;// todo tempo que o processo não é executado
    public float temp_resp_med;// chegada até primeira execução
    public float turnaround_med;// chegada a conclusão total

    public FIFO(List<ProcessoEscalonador> processos) {
        for (ProcessoEscalonador processo : processos) {
            processo.resetar();
        }
        this.processos = processos;
        temp_esp_med = 0;
        temp_resp_med = 0;
        turnaround_med = 0;
        count_delta = 0;
    }

    public void escalonar() {
        int num_procs = processos.size();

        processos.sort(Comparator.comparingDouble(p -> p.chegada));
        for (ProcessoEscalonador processo : processos) {
            if (count_delta <= processo.chegada) {
                count_delta = processo.chegada;
            }
            temp_resp_med += count_delta - processo.chegada;
            count_delta += processo.duracao;
            turnaround_med += count_delta - processo.chegada;
        }
        
        temp_resp_med = temp_resp_med/num_procs;
        temp_esp_med = temp_resp_med;
        turnaround_med = turnaround_med/num_procs;

    }

}