package exercicis;

import java.io.*;

public class LlensaProgrames {

    public static void main(String[] args) throws IOException, InterruptedException {

        // Creem els ProcessBuilders, per a executar els programes.
        ProcessBuilder pb = new ProcessBuilder("notepad.exe");
        ProcessBuilder pb2 = new ProcessBuilder("calc.exe");
        ProcessBuilder pb3 = new ProcessBuilder("mspaint.exe");

        // Declarem els Process per a guardar el start de cada Process.
        Process p1 = pb.start();
        Process p2 = pb2.start();
        Process p3 = pb3.start();

        // Declarem els codis de retorn per a fer un waitFor.
        int codiRetorn1 = p1.waitFor();
        int codiRetorn2 = p2.waitFor();
        int codiRetorn3 = p3.waitFor();

        // Mostrem el resultat del codi per consola.
        System.out.println("EL codi de retorn1 es " +  codiRetorn1);
        System.out.println("EL codi de retorn2 es " +  codiRetorn2);
        System.out.println("EL codi de retorn3 es " +  codiRetorn3);

    }

}