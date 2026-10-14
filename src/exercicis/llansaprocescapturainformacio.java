package exercicis;

import java.io.*;

public class llansaprocescapturainformacio {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Has d'indicar una ordre per executar.");
            return;
        }

        ProcessBuilder pb = new ProcessBuilder(args);

        try {
            Process p = pb.start();

            // Capturam la sortida normal
            InputStream is = p.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            // Capturam els errors
            InputStream es = p.getErrorStream();
            InputStreamReader esr = new InputStreamReader(es);
            BufferedReader er = new BufferedReader(esr);

            System.out.println("SORTIDA DEL PROCÉS");
            System.out.println("------------------");

            String linia;
            boolean hiHaSortida = false;

            while ((linia = br.readLine()) != null) {
                System.out.println(linia);
                hiHaSortida = true;
            }

            if (!hiHaSortida) {
                System.out.println("Cap sortida");
            }

            System.out.println();
            System.out.println("ERRORS DEL PROCÉS");
            System.out.println("-----------------");

            boolean hiHaError = false;

            while ((linia = er.readLine()) != null) {
                System.out.println(linia);
                hiHaError = true;
            }

            if (!hiHaError) {
                System.out.println("Cap error");
            }

            // Esperam que el procés acabi
            int codiRetorn = p.waitFor();

            System.out.println();
            System.out.println("CODI DE RETORN: " + codiRetorn);

            if (codiRetorn == 0) {
                System.out.println("Execució correcta");
            } else {
                System.out.println("S'ha produït un error");
            }

            br.close();
            er.close();

        } catch (IOException e) {
            System.out.println("Error d'entrada/sortida: " + e.getMessage());

        } catch (InterruptedException e) {
            System.out.println("El procés ha estat interromput.");
            Thread.currentThread().interrupt();
        }
    }
}