package exercicisUD2.exercici2pt1;

import java.util.*;

public class Tasca implements Runnable {

    static void main() {
        // Creem un Thread.
        Thread t1 = new Thread("Descàrrega");
        Thread t2 = new Thread("Cálcul");
        Thread t3 = new Thread("Copia");
        Thread t4 = new Thread("Anàlisis");

        try {
            // Iniciem les tasques.
            t1.start();
            System.out.println("Tasca Descàrrega iniciada.");
            t2.start();
            System.out.println("Tasca Cálcul iniciada.");
            t3.start();
            System.out.println("Tasca Copia iniciada.");
            t4.start();
            System.out.println("Tasca Anàlisis iniciada.");
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        }
    }

    // Mètode implementat de la interfície Runnable.
    @Override
    public void run() {
        // Declarem el random per a generar pauses aleatòries.
        Random r = new Random();

        // Feiem 5 iteracions.
        for (int i = 0; i < 5; i++) {
            // Variable per a guardar la pausa.
            int pausa = 1 + r.nextInt(500-100);
            System.out.println("Tasca " + i + ": " + pausa);
        }
    }
}