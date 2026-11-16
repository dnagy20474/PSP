package exercicisUD2.exercici2pt4;

import java.util.*;

public class Test {

    // Aquesta classe guarda el numero que s'ha d'endevinar
    // i controla si el joc ja ha acabat.
    static class NumeroOcult {

        private int numero;
        private boolean acabat;

        // Constructor de la clase.
        public NumeroOcult(int numero) {
            this.numero = numero;
            this.acabat = false;
        }

        // synchronized fa que només un fil pugui comprovar
        // i modificar l'estat del joc al mateix temps.
        public synchronized int propostaNumero(int num) {

            // Si un altre fil ja ha encertat
            if (acabat) {
                return -1;
            }

            // Si el numero es correcte
            if (num == numero) {
                acabat = true;
                return 1;
            }

            // El número no és correcte i el joc continua
            return 0;
        }
    }

    // Aquesta classe representa cada un dels 10 fils
    static class Jugador extends Thread {

        private int numeroJugador;
        private NumeroOcult numeroOcult;
        private Random random;

        public Jugador(int numeroJugador, NumeroOcult numeroOcult) {
            this.numeroJugador = numeroJugador;
            this.numeroOcult = numeroOcult;
            this.random = new Random();
        }

        @Override
        public void run() {

            boolean acabat = false;

            while (!acabat) {

                // Generem un numero entre 0 i 100
                int proposta = random.nextInt(101);

                int resultat = numeroOcult.propostaNumero(proposta);

                if (resultat == 1) {

                    // Aquest jugador ha encertat
                    System.out.println("El jugador " + numeroJugador
                            + " ha encertat el numero: " + proposta);

                    acabat = true;

                } else if (resultat == -1) {

                    // Un altre jugador ja havia encertat
                    System.out.println("El jugador " + numeroJugador
                            + " acaba perquè un altre jugador ha encertat.");

                    acabat = true;
                }
            }
        }
    }

    public static void main(String[] args) {

        Random random = new Random();

        // El fil principal genera el numero ocult
        int numero = random.nextInt(101);

        System.out.println("Comença el joc!");

        // Creem l'objecte que compartiran els 10 fils
        NumeroOcult numeroOcult = new NumeroOcult(numero);

        // Array per guardar els 10 jugadors
        Jugador[] jugadors = new Jugador[10];

        // Creem els 10 fils
        for (int i = 0; i < 10; i++) {
            jugadors[i] = new Jugador(i + 1, numeroOcult);
        }

        // Posem en marxa els 10 fils
        for (int i = 0; i < 10; i++) {
            jugadors[i].start();
        }

        // Esperem que acabin tots els fils
        for (int i = 0; i < 10; i++) {
            try {
                jugadors[i].join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("El joc ha acabat.");
    }
}
