package repasUD2.exercici2pt2;

public class CarreraFil {

    static void main(String[] args) {

        // Declaramos los 3 hilos.
        Thread corredor1 = new Thread(new Corredor("Corredor 1"));
        Thread corredor2 = new Thread(new Corredor("Corredor 2"));
        Thread corredor3 = new Thread(new Corredor("Corredor 3"));



    }

    public static class Corredor implements Runnable {

        private final String nombre;

        // Constructor de la clase.
        public Corredor(String nombre) {
            this.nombre = nombre;
        }

        @Override
        public void run() {

        }
    }
}