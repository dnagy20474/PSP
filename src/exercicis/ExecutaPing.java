package exercicis;

import java.io.*;

public class ExecutaPing {

    public static void main(String[] args) {

        // Comprovar que s'ha indicat almenys el nom de l'ordinador
        if (args.length < 1) {
            System.out.println("Ús: java ExecutaPing <ordinador> [nombre_de_vegades]");
            return;
        }

        // Arguments per a la comanda ping
        String ordinador = args[0];
        int vegades = 5; // Valor per defecte de ping de Windows

        // Si s'ha indicat el nombre de vegades
        if (args.length >= 2) {
            try {
                vegades = Integer.parseInt(args[1]);

                if (vegades <= 0) {
                    System.out.println("El nombre de vegades ha de ser un nombre positiu.");
                    return;
                }

            } catch (NumberFormatException e) {
                System.out.println("El nombre de vegades ha de ser un enter.");
                return;
            }
        }

        try {
            // Cada element és un argument independent de ProcessBuilder
            ProcessBuilder pb = new ProcessBuilder(
                    "ping",
                    "-n",
                    String.valueOf(vegades),
                    ordinador
            );

            // Llançar el procés
            Process p = pb.inheritIO().start();

            // Esperar que acabi
            int codiRetorn = p.waitFor();

            // Mostrar el codi de retorn
            System.out.println("Codi de retorn: " + codiRetorn);

        } catch (IOException e) {
            System.out.println("Error en executar la comanda ping: " + e.getMessage());

        } catch (InterruptedException e) {
            System.out.println("El procés ha estat interromput.");
            Thread.currentThread().interrupt();
        }
    }
}