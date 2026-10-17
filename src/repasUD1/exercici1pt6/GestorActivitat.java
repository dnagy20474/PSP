package repasUD1.exercici1pt6;

import java.io.*;

public class GestorActivitat {

    public static void main(String[] args) throws IOException, InterruptedException {

        // Mostrem per pantalla l'inici del programa.
        System.out.println("    INICI DEL PROGRAMA    ");

        // Creem un ProcessBuilder per obtenir el nom de l'usuari.
        ProcessBuilder pb1 = new ProcessBuilder("cmd", "/c", "echo", "%USERNAME%");

        // Iniciem el process.
        pb1.inheritIO();

        // Llançem el process amb un start().
        Process p1 = pb1.start();

        // Esperem que termini el process.
        p1.waitFor();

        // Cridem el mètode per crear el fitxer activitat.txt.
        crearFitxer(pb1, true); // Li passem el ProcessBuilder.

        // Creem un altre ProcessBuilder per registrar el directori actual.
        ProcessBuilder pb2 = new ProcessBuilder("cmd", "/c", "cd");

        // Iniciem el process.
        pb2.inheritIO();

        // Llançem el process amb un start().
        Process p2 = pb2.start();

        // Esperem que termini el process.
        p2.waitFor();

        // Cridem el fitxer per a guardar el nou ProcessBuilder.
        crearFitxer(pb2, false);

        // Creem un altre ProcessBuilder per mostrar el contingut d'un fitxer.
        ProcessBuilder pb3 = new ProcessBuilder("cmd", "/c", "type", "fitxer_secret.txt");

        // Iniciem el process.
        pb3.inheritIO();

        // Llançem el process amb un start().
        Process p3 = pb3.start();

        // Esperem que termini el process.
        p3.waitFor();
    }

    // Creem un mètode per a crear el fitxer.
    public static void crearFitxer(ProcessBuilder pb, boolean esModificable) {
        // Control d'Error amb el BufferedWriter, per crear el fitxer correctament i que ens mostri l'error si ni a.
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("activitat.txt", esModificable))) {
            while (true) {
                bw.write(pb.command().toString()); // Escribim en el fitxer la comanda.
            }
        } catch (IOException e) {
            System.out.println("ERROR: Al crear el fitxer, num ERROR: " + e);
        }

    }

    // Creem un mètode per a comprobar que existeix un fitxer.
    public static void comprobarFitxer(ProcessBuilder pb) {
        // Trèiem el nom del fitxer que hi ha a la comanda.
        File f = new  File(pb.command().toString());

        // Control d'error.
        try {
            // Comprovem si existeix el fitxer.
            if (!f.exists()) {
                FileWriter fw = new FileWriter("errors.txt");

                fw.close();

                fw.write(pb.command().toString());
            }
        } catch (IOException e) {
            System.out.println("ERROR: Al comprobar el fitxer, num ERROR: " + e);
        }
    }

}