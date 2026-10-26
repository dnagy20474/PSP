package exercicisUD2.exercici2pt2;

import java.util.*;

public class Test {

    public static void main(String[] args) {

        // Creem els 3 Threads.
        Thread corredor1 = new Thread(new Corredor(), "Corredor-1");
        Thread corredor2 = new Thread(new Corredor(), "Corredor-2");
        Thread corredor3 = new Thread(new Corredor(), "Corredor-3");

        // Iniciem els tres corredors.
        corredor1.start();
        corredor2.start();
        corredor3.start();

        // Comprovem si els fils encara estan executant-se.
        System.out.println(
                "Corredor-1 està executant-se: " + corredor1.isAlive()
        );

        System.out.println(
                "Corredor-2 està executant-se: " + corredor2.isAlive()
        );

        System.out.println(
                "Corredor-3 està executant-se: " + corredor3.isAlive()
        );

        // Esperam que acabin els tres corredors.
        try {
            corredor1.join();
            corredor2.join();
            corredor3.join();

        } catch (InterruptedException e) {
            System.out.println("ERROR: " + e);
            Thread.currentThread().interrupt();
        }

        System.out.println("La cursa ha acabat.");
    }


    static class Corredor implements Runnable {

        @Override
        public void run() {

            Random r = new Random();

            // Cada corredor fa 10 passos.
            for (int i = 1; i <= 10; i++) {

                System.out.println(
                        Thread.currentThread().getName()
                                + " fa el pas " + i + "/10"
                );

                // Pausa aleatòria entre 100 i 500 ms.
                int pausa = 100 + r.nextInt(401);

                try {
                    Thread.sleep(pausa);

                } catch (InterruptedException e) {
                    System.out.println("ERROR: " + e);
                    Thread.currentThread().interrupt();
                    return;
                }
            }

            // El corredor ha completat els 10 passos.
            System.out.println(
                    Thread.currentThread().getName()
                            + " ha arribat a la meta!"
            );
        }
    }
}