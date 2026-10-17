package repasUD1.exercici1pt5;

import java.io.*;
import java.util.*;

public class ExecutaPing {

    public static void main(String[] args) {

        // Comprobar si hi ha arguments, per a les comandes.
        if (args.length < 0) {
            System.out.println("Te que tenir més de un argument.");
            System.exit(1);
        }

        try {
            // Creem un ProcessBuilder per als arguments introduits.
            ProcessBuilder pb = new ProcessBuilder(args);

            // Iniciem el Process.
            pb.inheritIO();

            // Llançem el process, amb un start().
            Process p = pb.start();

            // Esperem que acabi el process amb el waitFor().
            p.waitFor();

            System.out.println(); // Salt de lìnea.

            // Mostrem el codi de retorn.
            System.out.println("Codi retorn: " + p.getInputStream().read());

        } catch (IOException e) {
            System.out.println("Error de execució: " + e);
        } catch (InterruptedException e) {
            System.out.println("Error de codi: " + e);
        }

    }

}