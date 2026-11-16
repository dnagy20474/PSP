package exercicisUD2.exercici2pt8;

import java.util.*;

public class PasPerPont {

    public static void main(String[] args) {

        final Pont pont = new Pont();

        int tMinPerArribadaPersona = 1;
        int tMaxPerArribadaPersona = 30;
        int tMinPas = 10;
        int tMaxPas = 50;
        int minPesPersona = 40;
        int maxPesPersona = 120;

        System.out.println(">>>>>>>Comença la simulació.");

        Random r = new Random();

        int idPersona = 1;

        while (true) {

            int tPerArribadaPersona = tMinPerArribadaPersona +
                    r.nextInt(tMaxPerArribadaPersona - tMinPerArribadaPersona + 1);

            int pesPersona = minPesPersona + r.nextInt(maxPesPersona - minPesPersona + 1);

            // Assignem un sentit aleatori: 1 o -1
            int sentit = r.nextBoolean() ? 1 : -1;

            System.out.printf("Següent persona arriba en %d segons.\n", tPerArribadaPersona);

            // Posem aquest control d'error perquè no peti el programa per la creació de masa fils.
            try {
                Thread.sleep(tPerArribadaPersona);

            } catch (InterruptedException ex) {
                System.out.println("Interromput el procés principal");
                Thread.currentThread().interrupt();

                break;
            }

            Thread filPersona = new Thread(new Persona(pont, pesPersona, tMinPas, tMaxPas,
                    "P" + idPersona, sentit));

            filPersona.start();
            idPersona++;
        }
    }
}

class Pont {

    private static final int PES_MAXIM = 200;
    private static final int MAX_PERSONES = 4;
    private static final int MAX_PERSONES_SENTIT = 3;

    private int pes = 0;
    private int numPersones = 0;

    // Sentit actual del pont: 0 = buit
    private int sentitActual = 0;

    private int personesSentit = 0;

    synchronized public int getPes() {
        return pes;
    }

    synchronized public int getNumPersones() {
        return numPersones;
    }

    synchronized public int getSentitActual() {
        return sentitActual;
    }

    synchronized public boolean autoritzacioPas(Persona persona) {

        int sentitPersona = persona.getSentit();

        // Comprovem totes les condicions
        if ((numPersones < MAX_PERSONES) && (pes + persona.getPes() <= PES_MAXIM) &&
                (personesSentit < MAX_PERSONES_SENTIT) &&
                (numPersones == 0 || sentitActual == sentitPersona)) {

            // Si el pont està buit, establim el sentit
            if (numPersones == 0) {
                sentitActual = sentitPersona;
            }

            numPersones++;
            personesSentit++;
            pes += persona.getPes();

            return true;
        }

        return false;
    }

    synchronized public void acabaPas(Persona persona) {

        pes -= persona.getPes();
        numPersones--;
        personesSentit--;

        // Si no queda ningú, el pont queda lliure
        if (numPersones == 0) {
            sentitActual = 0;
            personesSentit = 0;
        }
    }
}

class Persona implements Runnable {

    private final String idPersona;
    private final int pes;
    private final int tMinPas;
    private final int tMaxPas;
    private final Pont pont;
    private final int sentit;

    public int getPes() {
        return pes;
    }

    public int getSentit() {
        return sentit;
    }

    public Persona(Pont pont, int pes, int tMinPas,
                   int tMaxPas, String idP, int sentit) {

        this.pont = pont;
        this.pes = pes;
        this.tMinPas = tMinPas;
        this.tMaxPas = tMaxPas;
        this.idPersona = idP;
        this.sentit = sentit;
    }

    @Override
    public void run() {
        String direccio = sentit == 1 ? "DRETA" : "ESQUERRA";

        System.out.printf("- %s de %d kg vol creuar cap a %s.\n", idPersona, pes, direccio);

        boolean autoritzat = false;

        while (!autoritzat) {
            synchronized (pont) {
                autoritzat = pont.autoritzacioPas(this);

                if (!autoritzat) {
                    try {
                        System.out.printf("# %s ha d'esperar.\n", idPersona);

                        pont.wait();

                    } catch (InterruptedException ex) {
                        System.out.printf("Interrupció mentre %s espera.\n", idPersona);

                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
        }

        System.out.printf("> %s pot creuar cap a %s. Pes al pont: %d kg, persones: %d.\n", idPersona, direccio,
                pont.getPes(), pont.getNumPersones());

        Random r = new Random();

        int tempsPas = tMinPas +
                r.nextInt(tMaxPas - tMinPas + 1);

        try {
            System.out.printf("%s trigarà %d segons a passar.\n", idPersona, tempsPas);

            Thread.sleep(tempsPas);

        } catch (InterruptedException ex) {
            System.out.printf("Interrupció mentre %s passa.\n", idPersona);

            Thread.currentThread().interrupt();
        }

        // La persona surt del pont
        synchronized (pont) {
            pont.acabaPas(this);

            System.out.printf("< %s surt del pont. Pes: %d kg, persones: %d.\n", idPersona, pont.getPes(),
                    pont.getNumPersones());

            pont.notifyAll();
        }
    }
}