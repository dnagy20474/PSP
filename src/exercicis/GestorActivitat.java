package exercicis;

import java.io.*;

public class GestorActivitat {

    public static void main(String[] args) {

        // Control d'error (try-catch)
        try (BufferedReader br = new BufferedReader(new FileReader("activitat.txt"))) {

        } catch (IOException e) {
            // Mostrem el missatge d'error.
            System.out.println("Error de lectura: " + e);
        }

        // Mostrem l'inici del programa
        System.out.println("INICI DEL PROGRAMA");

        // Agafem el nom d'usuari actual de Windows.
        ProcessBuilder pb = new ProcessBuilder(); // Declarem el ProceesBuilder.
        pb.command("-c", "whoami"); // Executem l'usuari.

        try {
            // Declarem el proces per emprar els seus mètodes.
            // Emprarem el .start().
            Process p = pb.inheritIO().start();

            int codiResposta;
        } catch (IOException e) {
            System.out.println("Error en executar la comanda: " + e);
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("activitat.txt",  true))) {
            bw.write("");
        } catch (IOException e) {
            System.out.println("Error de escritura: " + e);
        }

    }

}