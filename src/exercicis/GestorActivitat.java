package exercicis;

import java.io.*;

public class GestorActivitat {

    public static void main(String[] args) {

        try {
            // 1. INICI DEL PROGRAMA
            ProcessBuilder pb1 = new ProcessBuilder("cmd", "/c", "echo INICI DEL PROGRAMA");
            pb1.redirectOutput(ProcessBuilder.Redirect.INHERIT);

            Process p1 = pb1.start();
            p1.waitFor();

            // 2. REGISTRAR EL NOM DE L'USUARI
            ProcessBuilder pb2 = new ProcessBuilder("cmd", "/c", "echo %USERNAME%");
            pb2.redirectOutput(new File("activitat.txt"));

            Process p2 = pb2.start();
            p2.waitFor();

            // 3. REGISTRAR EL DIRECTORI ACTUAL
            ProcessBuilder pb3 = new ProcessBuilder("cmd", "/c", "cd");
            pb3.redirectOutput(
                    ProcessBuilder.Redirect.appendTo(new File("activitat.txt"))
            );

            Process p3 = pb3.start();
            p3.waitFor();

            // 4. INTENTAR CONSULTAR UN FITXER QUE NO EXISTEIX
            ProcessBuilder pb4 = new ProcessBuilder(
                    "cmd", "/c", "type fitxer_secret.txt"
            );

            // La sortida normal no es guarda
            pb4.redirectOutput(ProcessBuilder.Redirect.DISCARD);

            // L'error es guarda a errors.txt
            pb4.redirectError(new File("errors.txt"));

            Process p4 = pb4.start();
            p4.waitFor();

            // 5. FER UNA CONSULTA SENSE GUARDAR-NE EL RESULTAT
            ProcessBuilder pb5 = new ProcessBuilder("cmd", "/c", "dir");

            // No mostrar ni guardar la sortida
            pb5.redirectOutput(ProcessBuilder.Redirect.DISCARD);

            Process p5 = pb5.start();
            p5.waitFor();

            // 6. FINAL DEL PROGRAMA
            System.out.println("FI DEL PROGRAMA");

        } catch (IOException e) {
            System.out.println("Error en executar un procés: " + e.getMessage());

        } catch (InterruptedException e) {
            System.out.println("El procés ha estat interromput: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}