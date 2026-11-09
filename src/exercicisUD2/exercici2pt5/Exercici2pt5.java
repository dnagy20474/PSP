package exercicisUD2.exercici2pt5;

import java.util.*;

public class Exercici2pt5 {

    public static void main(String[] args) {

        // Instanciem l'array de llibres, que serà compartit per tots els estudiants.
        Llibre[] llibres = new Llibre[9];

        // Inicialitzem cada llibre amb el seu identificador.
        for (int i = 0; i < llibres.length; i++) {
            llibres[i] = new Llibre(i);
        }

        // Llancem els 4 estudiants amb un for, passant-los el mateix array de llibres.
        for (int i = 1; i <= 4; i++) {
            new Estudiant("Estudiant " + i, llibres).start();
        }
    }

    public static class Llibre {
        // Identificador del llibre.
        private final int id;

        // Constructor de la classe, al qual li passem l'identificador.
        public Llibre(int id) {
            this.id = id;
        }

        // Getter.
        public int getId() {
            return id;
        }

        // Permet mostrar el llibre als missatges (p. ex. "Llibre 3").
        @Override
        public String toString() {
            return "Llibre " + id;
        }
    }

    public static class Estudiant extends Thread {
        // Referència a l'array de llibres compartida per tots els estudiants.
        private final Llibre[] llibres;

        // Generador de nombres aleatoris per triar llibres i calcular temps.
        private final Random r = new Random();

        // Nom de l'estudiant.
        private final String nom;

        // Constructor de la classe amb els seus paràmetres.
        public Estudiant(String nom, Llibre[] llibres) {
            this.nom = nom;
            this.llibres = llibres;
        }

        // Implementem el mètode run().
        @Override
        public void run() {

            // Control d'errors: sleep() pot llançar una InterruptedException.
            try {

                // for que es repeteix per cada alumne.
                for (int i = 0; i <= 5; i++) {
                    // Triem el primer llibre a l'atzar.
                    int a = r.nextInt(llibres.length);
                    int b;

                    // Fem un do-while perquè el segon llibre sigui diferent del primer.
                    do {
                        b = r.nextInt(llibres.length);
                    } while (b == a);

                    // Ordenem els índexs perquè tothom bloquegi els llibres en el mateix
                    // ordre (de menor a major). Així evitem un deadlock, i a més
                    // els podem usar directament als synchronized.
                    int primer = Math.min(a, b);
                    int segon = Math.max(a, b);

                    // Mostrem els llibres que vol l'estudiant.
                    System.out.printf("%s vol els llibres %s i %s%n",
                            nom, llibres[primer], llibres[segon]);

                    // Bloquegem els llibres en ordre amb un synchronized.
                    // Si algun està ocupat, l'estudiant espera aquí.
                    synchronized (llibres[primer]) {
                        synchronized (llibres[segon]) {
                            // Estudiar entre 1 i 3 h = 60-180 ms (1 min = 1 ms).
                            int estudi = 60 + r.nextInt(121);

                            // Mostrem els llibres que té i el temps que estudiarà.
                            System.out.printf("%s té %s i %s i estudia %d min%n",
                                    nom, llibres[primer], llibres[segon], estudi);

                            // Esperem el temps d'estudi (1-3 h simulades).
                            Thread.sleep(estudi);
                        }
                    }

                    // En sortir dels synchronized els llibres queden lliures.
                    // Mostrem els llibres retornats.
                    System.out.printf("%s retorna %s i %s%n%n",
                            nom, llibres[primer], llibres[segon]);

                    // Descansar entre 1 i 2 h = 60-120 ms.
                    int descans = 60 + r.nextInt(61);

                    // Mostrem el temps de descans.
                    System.out.printf("%s descansa %d min%n%n", nom, descans);

                    // Esperem el temps de descans (1-2 h simulades).
                    Thread.sleep(descans);
                }
            } catch (InterruptedException ie) {
                // Si el fil és interromput mentre dorm, ho indiquem i acabem.
                System.out.printf("%s interromput.%n%n", nom);
            }
        }
    }
}