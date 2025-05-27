//import java.security.Principal;

public class Aluno extends Thread {
    private Estacao estacao;
    private String nome;
    private int tempoAula;  // Tempo da aula em milissegundos
    
    public Aluno(Estacao estacao, String nome, int tempoAula) {
        this.estacao = estacao;
        this.nome = nome;
        this.tempoAula = tempoAula;
        // Duração aleatória da aula entre 2 e 10 minutos (em milissegundos)
        //this.tempoAula = (int) (Math.random() * 9 + 2) * 3000;  // entre 2 e 10 minutos
    }

    @Override
    public void run() {
        try {
            // Aluno vai para a aula (durante o tempo aleatório)
            System.out.println(Relogio.tempoDecorrido(Main.tempoInicial) + nome + " foi para a aula. Tempo da aula: " + (tempoAula / 60000) + " minutos.");
            Thread.sleep(tempoAula);  // Tempo da aula (simula a duração da aula)

            // Depois da aula, aluno vai para o ponto de ônibus
            estacao.passageiroChega(nome);
        } catch (InterruptedException e) {
            System.out.println("Erro ao fazer o aluno " + nome + " esperar.");
        }
    }
}
