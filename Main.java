public class Main {
    public static long tempoInicial;
    public static void main(String[] args) {
        tempoInicial = System.currentTimeMillis(); // salva o tempo inicial
        Estacao estacao = new Estacao();
        
        // Criar e iniciar o gerador de ônibus
        GeradorOnibus geradorOnibus = new GeradorOnibus(estacao);
        geradorOnibus.start();  // Inicia o gerador de ônibus em uma thread separada

        // Gerar alunos aleatoriamente
        while (true) {
            int numeroAlunos = (int) (Math.random() * 100 + 1);  // Gera entre 1 e 100 alunos
            System.out.println("\nGerando " + numeroAlunos + " alunos.");

            //int tempoAula = (int) ((Math.random() * 9) + 2) * 1000; // tempo de aula teste
            int tempoAula = ((int) (Math.random() * 9) + 2) * 60 * 1000; // Sincroniza o tempo de aula
            System.out.println("Gerando Disciplinas com " + (tempoAula / 60000) + " minutos de duracao.");

            System.out.println(Relogio.tempoDecorrido(tempoInicial) + "Novo turno letivo criado!\n");
            
            // Criar e iniciar a thread para cada aluno
            for (int i = 1; i <= numeroAlunos; i++) {
                //int tempoAula = (int) ((Math.random() * 9) + 2) * 1000; // Teste individual de cada aluno

                Aluno aluno = new Aluno(estacao, "Aluno " + i, tempoAula);
                aluno.start();  // O aluno vai para a aula e depois para o ponto
            }

            try {
                // Espera aleatoria entre 1 e 2 minutos para gerar novas turmas de alunos
                // 
                //int delay = ((int) (Math.random() * 2) + 1) * 60 * 1000; // Geracao aleatoria do tempo entre o comeco das aulas
                //System.out.println("Tempo até próximo turno " + (delay / 60000) + " minutos");
                //Thread.sleep(delay);

                // Espera terminar as disciplinas atuais para começar uma nova.
                Thread.sleep(tempoAula);
            } catch (InterruptedException e) {
                System.out.println("Erro ao pausar a execução.");
            }
        }
    }
}
