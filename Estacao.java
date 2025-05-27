import java.util.concurrent.Semaphore;

public class Estacao {
    private final Semaphore mutex = new Semaphore(1);
    private final Semaphore busArrived = new Semaphore(0);  // Indica que o ônibus chegou
    private final Semaphore allAboard = new Semaphore(0);  // Indica que o passageiro embarcou

    private int waitingPassengers = 0;  // Número de passageiros aguardando
    private boolean boardingInProgress = false;

    public void passageiroChega(String nome) throws InterruptedException {
        mutex.acquire();  // Garante que a operacao seja exclusiva

        // Se ja estiver embarcando, o passageiro aguarda o proximo onibus
        if (boardingInProgress) {
            System.out.println(Relogio.tempoDecorrido(Main.tempoInicial) + nome + " chegou durante o embarque. Vai esperar o próximo ônibus.");
            mutex.release();
            return;
        }

        waitingPassengers++;  // Incrementa passageiros
        System.out.println(Relogio.tempoDecorrido(Main.tempoInicial) +  nome + " chegou ao ponto de ônibus. Passageiros aguardando: " + waitingPassengers);
        mutex.release();

        busArrived.acquire();  // Aguarda a chegada do onibus
        embarcarNoOnibus(nome);
        allAboard.release();  // Notifica que o passageiro embarcou
    }

    public void onibusChega(int capacidade) throws InterruptedException {
        mutex.acquire();  // Garante exclusao mutua para modificar dados

        if (waitingPassengers == 0) {
            System.out.println(Relogio.tempoDecorrido(Main.tempoInicial) + "[Ônibus] Nenhum passageiro. Partindo vazio.");
            mutex.release();
            return;
        }

        boardingInProgress = true;  // Inicia o embarque
        int passageirosNoOnibus = Math.min(waitingPassengers, capacidade);
        System.out.println(Relogio.tempoDecorrido(Main.tempoInicial) + "[Ônibus] Chegou com capacidade " + capacidade + ". Passageiros embarcando: " + passageirosNoOnibus);

        // Libera os passageiros para embarcar
        for (int i = 0; i < passageirosNoOnibus; i++) {
            busArrived.release();  // Libera um passageiro para embarcar
        }

        mutex.release();

        // Espera os passageiros embarcarem
        for (int i = 0; i < passageirosNoOnibus; i++) {
            allAboard.acquire();
        }

        mutex.acquire();  // Garante a exclusao mutua para modificar os dados
        waitingPassengers -= passageirosNoOnibus;
        boardingInProgress = false;
        System.out.println(Relogio.tempoDecorrido(Main.tempoInicial) + "[Ônibus] Partindo com " + passageirosNoOnibus + " passageiros.");
        mutex.release();
    }

    private void embarcarNoOnibus(String nome) {
        System.out.println(Relogio.tempoDecorrido(Main.tempoInicial) + nome + " embarcou no ônibus.");
    }
}
