package exercicisUD2.exercici2pt3;

public class Test {

    public static void main(String[] args) {

        // Totes les portes utilitzen el mateix objecte
        ControlAcces control = new ControlAcces();

        Porta[] portes = new Porta[10];

        // Creem les 10 portes
        for (int i = 0; i < 10; i++) {
            portes[i] = new Porta(i + 1, control);
        }

        // Comencem els fils
        for (int i = 0; i < 10; i++) {
            portes[i].start();
        }

        // Esperem que acabin tots els fils
        for (int i = 0; i < 10; i++) {
            try {
                portes[i].join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Quan han acabat tots, mostrem els resultats
        control.mostrarResultats();
    }
}
