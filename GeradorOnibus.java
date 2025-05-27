public class GeradorOnibus extends Thread {
    private Estacao estacao;

    public GeradorOnibus(Estacao estacao) {
        this.estacao = estacao;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Capacidade do onibus
                int capacidadeOnibus = 50; 
                // Intervalo de tempo para gerar o onibus (2 a 3 minutos)
                //int tempoDeEspera = (int) (Math.random() * 1000 * 2) + (1000 * 2); // Teste
                int tempoDeEspera = (int) ((Math.random() * 60_000) + 120_000); // Entre 2 e 3 minutos
                // Exibe uma mensagem indicando que o onibus foi gerado
                //System.out.println(Relogio.tempoDecorrido(Main.tempoInicial)+ "[Gerador de Ônibus] Tempo de espera " + (tempoDeEspera / 60000) + " Minutos");
                System.out.println(Relogio.tempoDecorrido(Main.tempoInicial)+ "[Gerador de Ônibus] Tempo de espera " + Relogio.formatarTempo(tempoDeEspera));
                

                Thread.sleep(tempoDeEspera);

                //System.out.println("[Gerador de Ônibus] Gerando ônibus com capacidade " + capacidadeOnibus + "\nTempo de espera " + (tempoDeEspera / 60000) + " Minutos");

                //System.out.println("[Gerador de Ônibus] Gerando ônibus com capacidade " + capacidadeOnibus);
                estacao.onibusChega(capacidadeOnibus);  // Chama o metodo para embarcar os passageiros
                
            }
        } catch (InterruptedException e) {
            System.out.println("Erro ao gerar ônibus.");
        }
    }
}
