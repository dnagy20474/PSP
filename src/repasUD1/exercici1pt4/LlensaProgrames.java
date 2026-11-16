package repasUD1.exercici1pt4;

import java.io.*;

public class LlensaProgrames {

    public static void main(String[] args) throws IOException, InterruptedException {

        // Creem els ProcessBuilders.
        ProcessBuilder pb1 = new ProcessBuilder("cmd.exe", "/c", "notepad.exe");
        ProcessBuilder pb2 = new ProcessBuilder("cmd.exe", "/c", "calc.exe");
        ProcessBuilder pb3 = new ProcessBuilder("cmd.exe", "/c", "mspaint.exe");

        // Llançem els procesos.
        pb1.inheritIO();
        pb2.inheritIO();
        pb3.inheritIO();

        // Guardem els Procesos retornats per start().
        Process p1 = pb1.start();
        Process p2 = pb2.start();
        Process p3 = pb3.start();

        // Esperem que finalitzin els procesoso amb el waitFor() i que retornin el codi.
        p1.waitFor();
        p2.waitFor();
        p3.waitFor();

        // Mostrem el codi de cada procés amb un getInputStream().read().
        System.out.println("Process 1: " + p1.getInputStream().read());
        System.out.println("Process 2: " + p2.getInputStream().read());
        System.out.println("Process 3: " + p3.getInputStream().read());

    }

}