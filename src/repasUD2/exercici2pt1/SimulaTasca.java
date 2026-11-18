package repasUD2.exercici2pt1;

import java.util.*;

public class SimulaTasca {

    void main(String[] args) throws InterruptedException {

        // Array de fils.
        Thread[] fils = new Thread[]{
                new Thread(new Tasca("Descàrrega")),
                new Thread(new Tasca("Càlcul")),
                new Thread(new Tasca("Còpia")),
                new Thread(new Tasca("Anàlisi"))
        };

        // Començem els fils.
        fils[0].start();
        fils[1].start();
        fils[2].start();
        fils[3].start();

        // Mostrem els missatges de inci.
        for (int i = 0; i < fils.length; i++) {
            System.out.printf("Tasca %s iniciada. %n%n", i);

        }

        System.out.println(); // Salt de lìnea.

        // Feim els 4 joins.
        fils[0].join();
        fils[1].join();
        fils[2].join();
        fils[3].join();

        System.out.println("Totes les tasques han acabat.");

    }

    // Clase donde aremos los procesos e hilos.
    public static class Tasca implements Runnable {
        private final String nombre;

        public Tasca(String nombre) {
            this.nombre = nombre;
        }

        // Declarem un random per a les pauses aleatòries.
        Random r = new Random();

        // implementamos el mètodo de run().
        @Override
        public void run() {

            // Declarem la variable pausa per guardar les pauses aleatòries.
            int pausa = r.nextInt(401) + 100; // r.nextInt(); aquest mètode serveix per calcular de 100 a 500 ms de pausa.

            // Iteracions i pauses.
            for (int i = 0; i < 5; i++) {

                // Control d'error perquè no peti el programa.
                try {
                    // Aquest mètode serveix per a fer l'espera de les pauses, quan toqui.
                    Thread.sleep(pausa);

                    // Mostrem la tasca i el pas amb printf
                    System.out.printf("Tasca %s: pas %s/%s %n%n", this.nombre, pausa, i);
                    System.out.printf("Tasca %s: pas %s acabat. %n%n", this.nombre, pausa);
                    System.out.println();

                    System.out.printf("Tasca %s: ha acabat %n%n", this.nombre);

                } catch (InterruptedException ie) {
                    System.out.printf("[ERROR]: %s", ie);
                }
            }
        }
    }
}