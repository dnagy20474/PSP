package exercicisUD2.exercici2pt6;

import java.util.*;

public class Exercici2pt6 {

    static void main(String[] args) {
        Eina[] destornillador = new Eina[]{new Eina(1, "destornillador")};
    }

    static class Eina {
        // Atributs de la clase Eina.
        private final int id;
        private final String nom;

        // Constructor amb els seus paràmentres
        public Eina(int id, String nom) {
            this.id = id;
            this.nom = nom;
        }

        // Getters
        public int getId() {
            return id;
        }

        public String getNom() {
            return nom;
        }

        // Mètode toString().

        @Override
        public String toString() {
            return "Eina{" +
                    "id=" + id +
                    ", nom='" + nom + '\'' +
                    '}';
        }
    }

    public static class Agent extends Thread {
        // Atributs de la clase Agent.
        private static final String nom = "";
        private static final String activitat = "";
        private static Eina[] eines = new Eina[0];
        private static final Random r = new Random();

        // Constructor amb els paràmetres.
        public Agent(String nom, String activitat, Eina[] eines) {
            this.eines = eines;
        }

        // Implementem el mètode run().


        // Getters.
        public String getNom() {
            return nom;
        }

        public String getActivitat() {
            return activitat;
        }

        public Eina[] getEines() {
            return eines;
        }

        public Random getR() {
            return r;
        }

        @Override
        public void run() {
            // Control d'error.
            try {
                while (true) {
                    // Mostrem l'activitat de cada agent.
                    System.out.printf("%s vol fer l'activitat %s%n%n", this.getNom(), activitat);

                    // fer(), és un mètode que fa com un synchronized, però el fèiem a part perquè
                    // té aixines pot emprar 2 o 3 activitats a la vegada.
                    fer(0);
                }
            } catch (InterruptedException ie) {
                System.out.printf("ERROR: %s%n%n", ie);
                System.out.printf("%s s'ha interromput. %n%n", this.getNom());
            }

        }

        // Mètode fer(), similar al synchronized.
        public static void fer(int i) throws InterruptedException {

            if (i == eines.length) {
                // Ja tenim totes les eines, fèiem l'activitat.
                int temps = 50 + r.nextInt(151); // 50-200 ms

                // Mostrem el que tarda l'activitat.
                System.out.printf("%s fa %s durant %d minuts %s%n%n", nom, activitat,
                        temps);

                Thread.sleep(temps); // Esperem a que es faci el temps.

                // Mostrem que ha acabat l'activitat.
                System.out.printf("%s ha acabat %s%n%n", nom, activitat);

            }

        }

    }

}
