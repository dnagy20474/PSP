package repasUD2.exercici2pt1;

import java.util.*;

public class SimulaTasca {

    public static void main(String[] args) throws InterruptedException {

        // Declaramos los hilos y ponemos el nombre, grácias a la clase Tasca.
        Thread t1 =  new Thread(new Tasca("Descàrrega"));
        Thread t2 =  new Thread(new Tasca("Càlcul"));
        Thread t3 =  new Thread(new Tasca("Còpia"));
        Thread t4 =  new Thread(new Tasca("Anàlisi"));

        // Iniciamos los hilos.
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        // Feim els 4 joins.
        t1.join();
        t2.join();
        t3.join();
        t4.join();

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

            System.out.printf("Tasca %s iniciada %n", this.nombre);

            // Iteracions i pauses.
            for (int i = 1; i <= 5; i++) {

                // Declarem la variable pausa per guardar les pauses aleatòries.
                int pausa = r.nextInt(401) + 100; // r.nextInt(); aquest mètode serveix per calcular de 100 a 500 ms de pausa.

                // Control d'error perquè no peti el programa.
                try {
                    // Aquest mètode serveix per a fer l'espera de les pauses, quan toqui.
                    Thread.sleep(pausa);

                    // Mostrem la tasca i el pas amb printf
                    System.out.printf("Tasca %s: pas %s/5 %n%n", this.nombre, i);
                    System.out.printf("Tasca %s: pas %s acabat. %n%n", this.nombre, i);

                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt(); // Interumpe el bucle.
                    System.out.printf("[ERROR]: %s", ie);

                    return;
                }
            }

            System.out.printf("Tasca %s: ha acabat %n%n", this.nombre);
        }
    }
}