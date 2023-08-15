import java.util.Arrays;
import java.util.List;

public class VerificaNumerosPrimos {

    public static void main(String[] args) {
        try {
            List<Integer> intList = Arrays.stream(args)
                    .map(Integer::parseInt).toList();

            List<Thread> threads = intList.stream().map(x -> new Thread(() -> {
                        if (verificarPrimo(x)) {
                            System.out.println("O numero " + x + " eh primo!");
                        } else {
                            System.out.println("O numero " + x + " nao eh primo!");
                        }
                    }))
                    .toList();

            threads.forEach(Thread::start);

            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    System.err.println("Erro ao aguardar a conclusão da thread: " + e.getMessage());
                }
            }

        } catch (NumberFormatException e) {
            System.err.println("Devem ser informados numeros inteiros para o programa");
            System.err.println(e.getMessage());
        }

    }

    public static boolean verificarPrimo(int num) {
        if (num <= 1) {
            return false;
        }

        double raiz = Math.sqrt(num);

        for (int i = 2; i <= raiz; i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }
}
