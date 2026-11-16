package exercicisUD2.exercici2pt1;

import java.util.Random;

public class Test {

    public static void main(String[] args) {

        // Creem els 4 Threads de les tasques.
        Thread t1 = new Thread(new Tasca("Descàrrega"));
        Thread t2 = new Thread(new Tasca("Càlcul"));
        Thread t3 = new Thread(new Tasca("Còpia"));
        Thread t4 = new Thread(new Tasca("Anàlisi"));

        // Iniciem les 4 tasques.
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        // Esperam que acabin les 4 tasques.
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();

        } catch (InterruptedException e) {
            System.out.println("ERROR: " + e);
            Thread.currentThread().interrupt();
        }

        // Les 4 tasques ja han acabat.
        // Ara podem iniciar la tasca Informe.
        Thread t5 = new Thread(new Tasca("Informe"));

        t5.start();

        // Esperam que acabi l'Informe.
        try {
            t5.join();

        } catch (InterruptedException e) {
            System.out.println("ERROR: " + e);
            Thread.currentThread().interrupt();
        }

        System.out.println("Totes les tasques han acabat.");
    }


    static class Tasca implements Runnable {

        private String nom;

        Tasca(String nom) {
            this.nom = nom;
        }

        @Override
        public void run() {

            Random r = new Random();

            System.out.println("Tasca " + nom + " iniciada.");

            // Feim 5 iteracions.
            for (int i = 1; i <= 5; i++) {

                System.out.println(
                        "Tasca " + nom + ": pas " + i + "/5"
                );

                // Pausa aleatòria entre 100 i 500 ms.
                int pausa = 100 + r.nextInt(401);

                try {
                    Thread.sleep(pausa);

                    System.out.println(
                            "Tasca " + nom + ": pas " + i + " acabat."
                    );

                } catch (InterruptedException e) {
                    System.out.println("ERROR: " + e);
                    Thread.currentThread().interrupt();
                    return;
                }
            }

            System.out.println("Tasca " + nom + " acabada.");
        }
    }
}