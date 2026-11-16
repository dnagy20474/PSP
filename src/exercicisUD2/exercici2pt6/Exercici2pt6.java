package exercicisUD2.exercici2pt6;

import java.util.*;

public class Exercici2pt6 {

    public static void main(String[] args) {
        // Creem les 3 eines (només n'hi ha una de cada tipus).
        Eina destornillador = new Eina(0, "destornillador");
        Eina taladro        = new Eina(1, "taladro");
        Eina alicates       = new Eina(2, "alicates");

        // Les eines SEMPRE en ordre d'id (0, 1, 2) per evitar un deadlock.
        new Agent("Agent 1 (A1)", "A1", destornillador, taladro).start();
        new Agent("Agent 2 (A1)", "A1", destornillador, taladro).start();
        new Agent("Agent 3 (A2)", "A2", destornillador, alicates).start();
        new Agent("Agent 4 (A3)", "A3", destornillador, taladro, alicates).start();
    }

    public static class Eina {
        // Atributs de la classe Eina.
        private final int id;
        private final String nom;

        // Constructor amb els seus paràmetres.
        public Eina(int id, String nom) {
            this.id = id;
            this.nom = nom;
        }

        // Getters.
        public int getId() {
            return id;
        }

        public String getNom() {
            return nom;
        }

        // Mètode toString().
        @Override
        public String toString() {
            return nom;
        }
    }

    public static class Agent extends Thread {
        // Nombre de vegades que cada agent fa la seva activitat.
        private static final int CICLES = 5;

        // Atributs de la classe Agent (cada agent té els seus).
        private final String nom;
        private final String activitat;
        private final Eina[] eines;   // eines necessàries, ja ordenades per id
        private final Random r = new Random();

        // Constructor amb els paràmetres. Els "..." permeten passar 2 o 3 eines.
        public Agent(String nom, String activitat, Eina... eines) {
            this.nom = nom;
            this.activitat = activitat;
            this.eines = eines;
        }

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

        // Implementem el mètode run().
        @Override
        public void run() {
            // Control d'errors: sleep() pot llançar una InterruptedException.
            try {
                // Cada agent fa l'activitat un nombre determinat de vegades.
                for (int cicle = 1; cicle <= CICLES; cicle++) {
                    // Mostrem l'activitat que vol fer l'agent.
                    System.out.printf("%s vol fer l'activitat %s (vegada %d)%n",
                            nom, activitat, cicle);

                    // fer() fa de synchronized, però l'hem separat en un mètode perquè
                    // cada activitat pot necessitar 2 o 3 eines a la vegada.
                    fer(0);
                }

                // Missatge final, quan l'agent ha acabat tots els cicles.
                System.out.printf("%s ha acabat les %d activitats.%n", nom, CICLES);
            } catch (InterruptedException ie) {
                // Si el fil és interromput mentre dorm, ho indiquem i acabem.
                System.out.printf("%s s'ha interromput.%n", nom);
            }
        }

        // Mètode fer(): bloqueja l'eina 'i' i, dins, crida per a la següent.
        // Equival a tenir els synchronized imbricats, però serveix per a qualsevol
        // nombre d'eines.
        private void fer(int i) throws InterruptedException {

            if (i == eines.length) {
                // Ja tenim totes les eines, fem l'activitat.
                int temps = 50 + r.nextInt(151); // 50-200 ms

                // Mostrem el que triga l'activitat.
                System.out.printf("%s fa %s durant %d ms%n", nom, activitat, temps);

                Thread.sleep(temps); // Esperem el temps de l'activitat.

                // Mostrem que ha acabat l'activitat.
                System.out.printf("%s ha acabat %s%n", nom, activitat);

                // Sortim: sense aquest return aniria a eines[i] i petaria.
                return;
            }

            // Bloquegem l'eina actual i passem a la següent.
            // En sortir del synchronized, l'eina queda lliure.
            synchronized (eines[i]) {
                fer(i + 1);
            }
        }
    }
}