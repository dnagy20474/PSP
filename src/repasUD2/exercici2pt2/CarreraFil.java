package repasUD2.exercici2pt2;

import java.util.*;

public class CarreraFil {

    static void main(String[] args) {

        // Declaramos los 3 hilos.
        Thread corredor1 = new Thread(new Corredor(), "Corredor 1");
        Thread corredor2 = new Thread(new Corredor(), "Corredor 2");
        Thread corredor3 = new Thread(new Corredor(), "Corredor 3");

        corredor1.start();
        corredor2.start();
        corredor3.start();

        try {
            corredor1.join();
            corredor2.join();
            corredor3.join();

        } catch (InterruptedException e) {
            System.out.printf("ERROR: %s%n", e);
        }

        System.out.println("La cursa a acabat.");
    }

    public static class Corredor implements Runnable {

        Random r = new Random();

        @Override
        public void run() {

                for (int i = 1; i <= 10; i++) {

                    int pausa = r.nextInt(401) + 100;

                    try {
                        System.out.printf("%s fa el pas %s/10 %n%n", Thread.currentThread().getName(), i);

                        Thread.sleep(pausa);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        System.out.printf("ERROR: %s%n", ie);

                        return;
                    }
                }

            System.out.printf("%s ha arribat al final!%n%n", Thread.currentThread().getName());
        }
    }
}