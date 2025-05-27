public class Relogio {
    public static String tempoDecorrido(long tempoInicial) {
        long agora = System.currentTimeMillis();
        long milissegundos = agora - tempoInicial;
        long minutos = (milissegundos / 1000) / 60;
        long segundos = (milissegundos / 1000) % 60;
        return String.format("[%02d:%02d] ", minutos, segundos);
    }
    
    public static String formatarTempo(long milissegundos) {
        long minutos = (milissegundos / 1000) / 60;
        long segundos = (milissegundos / 1000) % 60;
        return String.format("%d minutos e %d segundos", minutos, segundos);
    }
}
