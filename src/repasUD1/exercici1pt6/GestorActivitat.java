package repasUD1.exercici1pt6;

import java.io.*;

public class GestorActivitat {

    public static void main(String[] args) {

        System.out.println("INICI DEL PROGRAMA");

        try {
            // -------------------------------------------------
            // 1. Registrar el nom de l'usuari
            // -------------------------------------------------
            ProcessBuilder pb1 = new ProcessBuilder(
                    "cmd", "/c", "echo", "%USERNAME%"
            );

            // Sobreescrivim activitat.txt per començar un registre nou
            pb1.redirectOutput(
                ProcessBuilder.Redirect.to(new File("activitat.txt"))
            );

            // No volem guardar ni mostrar errors d'aquesta ordre
            pb1.redirectError(ProcessBuilder.Redirect.DISCARD);

            Process p1 = pb1.start();
            p1.waitFor();


            // -------------------------------------------------
            // 2. Registrar el directori actual
            // -------------------------------------------------
            ProcessBuilder pb2 = new ProcessBuilder(
                    "cmd", "/c", "cd"
            );

            // Afegim la sortida al final d'activitat.txt
            pb2.redirectOutput(
                ProcessBuilder.Redirect.appendTo(new File("activitat.txt"))
            );

            pb2.redirectError(ProcessBuilder.Redirect.DISCARD);

            Process p2 = pb2.start();
            p2.waitFor();


            // -------------------------------------------------
            // 3. Intentar mostrar fitxer_secret.txt
            // -------------------------------------------------
            ProcessBuilder pb3 = new ProcessBuilder(
                    "cmd", "/c", "type", "fitxer_secret.txt"
            );

            // La sortida normal no es guarda
            pb3.redirectOutput(ProcessBuilder.Redirect.DISCARD);

            // L'error es guarda a errors.txt
            pb3.redirectError(
                    ProcessBuilder.Redirect.to(new File("errors.txt"))
            );

            Process p3 = pb3.start();
            p3.waitFor();

            // Creem s'últim ProcessBuilder per la comanda dir.
            ProcessBuilder pb4 = new ProcessBuilder("cmd", "/c", "dir");

            // Iniciem el process.
            pb4.inheritIO();

            // Enllaçem el process amb un start().
            Process p4 = pb4.start();

            // Esperem que acabi el process amb un waitFor().
            p4.waitFor();

            // Mostrar per pantalla el resultat de la comanda.
            System.out.println("La comanda es: " + pb4.toString() + p4.toString());
            System.out.println(); // Salt de lìnea.

        } catch (IOException e) {

            System.out.println("Error d'entrada/sortida: " + e);

        } catch (InterruptedException e) {

            System.out.println("El procés ha estat interromput: " + e);

            // Restablim el flag d'interrupció del fil
            Thread.currentThread().interrupt();
        }

        System.out.println("FI DEL PROGRAMA");
    }
}