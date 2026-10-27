package exercicisUD2.exercici2pt3;

import java.util.*;

public class Test {

    // Atribut de portes de entrada.
    public static final int NUM_PORTES_ENTRADA = 10;

    public static void main(String[] args) {



    }

    class ControlAcces implements Runnable {

        // Declarem 2 HashMaps.
        HashMap<Integer, Integer> map1 = new HashMap<>();
        HashMap<Integer, Integer> map2 = new HashMap<>();

        // Declarem el Random.
        Random r = new Random();

        // Implantació del métode run() de la interface Runnable
        @Override
        public void run() {
            // Generem aleatoriamente els accesos.
            for (int i = 1; i <= NUM_PORTES_ENTRADA; i++) {

            }
        }
    }
}
