package exercicis;

import java.io.IOException;
import java.util.Arrays;

public class ExecutaPing {

    public static void main(String[] args) {

        // Comprobem que s'introdueix la comanda al iniciar.
        if (args.length <= 0) {
            System.out.println("Falta indicar la comanda a executar.");

            System.exit(1);
        }

        // Creem el ProcessBuilder.
        ProcessBuilder pb = new ProcessBuilder(args); // Li pasem el args.
        pb.inheritIO();

        // Feim un control de error (try-catch).
        try {
            // Declarem el Process amb un start(), pel codi.
            Process p = pb.start();
            int codRet = p.waitFor();

            // Mostrem l'estat del codRet per consola.
            System.out.println("L'execució de " + Arrays.toString(args)
                + " retorna " + codRet + " " + (codRet==0? "(execució correcte)" :
                    "(ERROR)"));

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
