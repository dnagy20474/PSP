package exercicis;

import java.io.*;

public class GestorActivitatPrueba {

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
        ProcessBuilder pb1 = new ProcessBuilder(); // Declarem el ProceesBuilder.
        pb1.command("-c", "whoami"); // Executem l'usuari.

        try {
            // Declarem el proces per emprar els seus mètodes.
            // Emprarem el .start().
            Process p1 = pb1.inheritIO().start();

            // Executem el comando cd.
            ProcessBuilder pb2 = new ProcessBuilder("cd");

            // Empra'm també el .nheritIO() amh .start().
            Process p2 = pb2.inheritIO().start();

            // Emprem el waitFor() per a agafar el codi resposta.
            p1.waitFor();
            p2.waitFor();

            int codiResposta1;
        } catch (IOException e) {
            System.out.println("Error en executar la comanda: " + e);
        } catch (InterruptedException e) {
            System.out.println("El procés a estat interromput: " + e);
        }

        // Control de fluxe per a guardar el ficher.
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("activitat.txt",  true))) {
            bw.write("");

        } catch (IOException e) {
            System.out.println("Error de escritura: " + e);
        }

    }

}