package exercicisUD2.exercici2pt3;

import java.util.*;

public class Porta extends Thread {

    private int numeroPorta;
    private ControlAcces controlAcces;
    private Random random;

    public Porta(int numeroPorta, ControlAcces controlAcces) {
        this.numeroPorta = numeroPorta;
        this.controlAcces = controlAcces;
        random = new Random();
    }

    @Override
    public void run() {

        // Cada porta fa entre 100 i 200 accessos
        int numeroAccessos = random.nextInt(101) + 100;

        for (int i = 0; i < numeroAccessos; i++) {

            int numero = random.nextInt(4);

            TipusEntrada tipus;

            if (numero == 0) {
                tipus = TipusEntrada.GENERAL;
            } else if (numero == 1) {
                tipus = TipusEntrada.VIP;
            } else if (numero == 2) {
                tipus = TipusEntrada.PREMSA;
            } else {
                tipus = null;
            }

            // Enviem l'accés al control central
            controlAcces.registrarAcces(numeroPorta, tipus);
        }
    }
}
