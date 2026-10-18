package exercicis.exercici1pt8;

import java.io.*;

public class GestorSistema {

    public static void main(String[] args) {

        // Missatge inicial
        System.out.println("COMENÇA LA RECOLLIDA D'INFORMACIÓ");

        try {

            // -----------------------------------------
            // 1. Obtenir el nom de l'usuari
            // -----------------------------------------
            ProcessBuilder pb1 = new ProcessBuilder(
                    "cmd", "/c", "echo", "%USERNAME%"
            );

            // Guardem la sortida a usuari.txt.
            // "to" sobreescriu el contingut anterior.
            pb1.redirectOutput(
                    ProcessBuilder.Redirect.to(new File("usuari.txt"))
            );

            // Els errors no ens interessen.
            pb1.redirectError(ProcessBuilder.Redirect.DISCARD);

            Process p1 = pb1.start();

            // Esperem que acabi abans de continuar.
            p1.waitFor();

            // -----------------------------------------
            // 2. Obtenir el directori de treball
            // -----------------------------------------
            ProcessBuilder pb2 = new ProcessBuilder(
                    "cmd", "/c", "cd"
            );

            // Afegim la sortida al final d'usuari.txt.
            // "appendTo" NO elimina el contingut anterior.
            pb2.redirectOutput(
                    ProcessBuilder.Redirect.appendTo(new File("usuari.txt"))
            );

            // Descartem els possibles errors.
            pb2.redirectError(ProcessBuilder.Redirect.DISCARD);

            Process p2 = pb2.start();

            // Esperem que acabi abans de continuar.
            p2.waitFor();

            // -----------------------------------------
            // 3. Intentar consultar un fitxer inexistent
            // -----------------------------------------
            ProcessBuilder pb3 = new ProcessBuilder(
                    "cmd", "/c", "type", "fitxer_secret.txt"
            );

            // La sortida normal no ens interessa.
            pb3.redirectOutput(ProcessBuilder.Redirect.DISCARD);

            // Guardem els errors a errors.txt.
            pb3.redirectError(
                    ProcessBuilder.Redirect.to(new File("errors.txt"))
            );

            Process p3 = pb3.start();

            // Esperem que acabi.
            p3.waitFor();

        } catch (IOException e) {

            // Control d'errors d'entrada/sortida
            System.out.println("ERROR d'entrada/sortida: " + e);

        } catch (InterruptedException e) {

            // Control d'interrupcions
            System.out.println("ERROR: El procés ha estat interromput.");

            // Tornem a marcar el fil com interromput.
            Thread.currentThread().interrupt();
        }

        // Missatge final
        System.out.println("LA RECOLLIDA D'INFORMACIÓ HA FINALITZAT");
    }
}