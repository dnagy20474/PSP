package exercicisUD2.exercici2pt1;

import java.util.*;

public class Test {

    void main() {
        // Creem un Thread.
        Thread t1 = new Thread(new Tasca("Descàrrega"));
        Thread t2 = new Thread(new Tasca("Cálcul"));
        Thread t3 = new Thread(new Tasca("Copia"));
        Thread t4 = new Thread(new Tasca("Anàlisis"));

        try {
            // Iniciem les tasques.
            t1.start();
            System.out.println("Tasca Descàrrega iniciada.");
            t2.start();
            System.out.println("Tasca Cálcul iniciada.");
            t3.start();
            System.out.println("Tasca Copia iniciada.");
            t4.start();
            System.out.println("Tasca Anàlisis iniciada." + "\n");

            // Feiem els joins per cada tasca.
            try {
                t1.join();
                t2.join();
                t3.join();
                t4.join();
            } catch (InterruptedException e) {
                System.out.println("ERROR: " + e);
            }

        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        }
    }

    class Tasca implements Runnable {
        private String nom;

        Tasca (String nom) {
            this.nom = nom;
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
                System.out.println("Tasca " + i + ": pas " + pausa);
                try {
                    Thread.sleep(pausa);
                    System.out.println("Tasca " + i + ": pas acabat");
                } catch (InterruptedException e) {
                    System.out.println("ERROR: " + e);
                }
            }
        }
    }
}