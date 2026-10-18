package exercicis.exercici1pt9;

import java.io.*;

public class AnalitzadorProces {

    public static void main(String[] args) {

        // Comprovem si l'usuari ha introduït alguna ordre.
        if (args.length == 0) {
            System.out.println("ERROR: No s'ha proporcionat cap ordre.");
            return;
        }

        try {

            // Creem el ProcessBuilder amb els arguments rebuts.
            ProcessBuilder pb = new ProcessBuilder(args);

            // Mostrem l'ordre que executarem.
            System.out.println("Ordre executada: " + String.join(" ", args));

            // Iniciem el procés.
            Process p = pb.start();

            // -------------------------------------------------
            // Sortida estàndard
            // -------------------------------------------------

            BufferedReader sortidaNormal = new BufferedReader(
                    new InputStreamReader(p.getInputStream())
            );

            StringBuilder resultatNormal = new StringBuilder();

            String linia;

            while ((linia = sortidaNormal.readLine()) != null) {
                resultatNormal.append(linia).append(System.lineSeparator());
            }

            // -------------------------------------------------
            // Sortida d'error
            // -------------------------------------------------

            BufferedReader sortidaError = new BufferedReader(
                    new InputStreamReader(p.getErrorStream())
            );

            StringBuilder resultatError = new StringBuilder();

            while ((linia = sortidaError.readLine()) != null) {
                resultatError.append(linia).append(System.lineSeparator());
            }

            // Tanquem els streams.
            sortidaNormal.close();
            sortidaError.close();

            // Esperem que el procés finalitzi.
            int codiRetorn = p.waitFor();

            // -------------------------------------------------
            // Mostrem el resultat
            // -------------------------------------------------

            System.out.println();
            System.out.println("Codi de retorn: " + codiRetorn);

            System.out.println();
            System.out.println("===== SORTIDA ESTÀNDARD =====");

            if (resultatNormal.length() == 0) {
                System.out.println("El procés no ha produït cap sortida normal.");
            } else {
                System.out.print(resultatNormal);
            }

            System.out.println();
            System.out.println("===== SORTIDA D'ERROR =====");

            if (resultatError.length() == 0) {
                System.out.println("El procés no ha produït cap sortida d'error.");
            } else {
                System.out.print(resultatError);
            }

            // Si el codi de retorn és diferent de zero,
            // indiquem que el procés ha acabat amb errors.
            if (codiRetorn != 0) {
                System.out.println();
                System.out.println("EL PROCÉS HA ACABAT AMB ERRORS.");
            }

        } catch (IOException e) {

            System.out.println(
                    "ERROR d'entrada/sortida: " + e.getMessage()
            );

        } catch (InterruptedException e) {

            System.out.println(
                    "ERROR: El procés ha estat interromput."
            );

            // Tornem a marcar el fil com interromput.
            Thread.currentThread().interrupt();
        }
    }
}