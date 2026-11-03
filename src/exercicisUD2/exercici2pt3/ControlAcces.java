package exercicisUD2.exercici2pt3;

import java.util.*;

public class ControlAcces {

    private HashMap<Integer, Integer> accessosPerPorta;
    private HashMap<TipusEntrada, Integer> accessosPerTipus;

    public ControlAcces() {
        accessosPerPorta = new HashMap<>();
        accessosPerTipus = new HashMap<>();

        // Comencem totes les portes a 0
        for (int i = 1; i <= 10; i++) {
            accessosPerPorta.put(i, 0);
        }

        // Comencem tots els tipus a 0
        accessosPerTipus.put(TipusEntrada.GENERAL, 0);
        accessosPerTipus.put(TipusEntrada.VIP, 0);
        accessosPerTipus.put(TipusEntrada.PREMSA, 0);

        // Null representa una entrada no valida
        accessosPerTipus.put(null, 0);
    }

    // synchronized evita que dos fils modifiquin els mapes al mateix temps
    public synchronized void registrarAcces(int porta, TipusEntrada tipus) {

        int accessosPorta = accessosPerPorta.get(porta);
        accessosPerPorta.put(porta, accessosPorta + 1);

        int accessosTipus = accessosPerTipus.get(tipus);
        accessosPerTipus.put(tipus, accessosTipus + 1);
    }

    public void mostrarResultats() {

        System.out.println("\n--- ACCESSOS PER PORTA ---");

        for (int i = 1; i <= 10; i++) {
            System.out.println("Porta " + i + ": " + accessosPerPorta.get(i));
        }

        System.out.println("\n--- ACCESSOS PER TIPUS ---");

        System.out.println("GENERAL: " + accessosPerTipus.get(TipusEntrada.GENERAL));

        System.out.println("VIP: " + accessosPerTipus.get(TipusEntrada.VIP));

        System.out.println("PREMSA: " + accessosPerTipus.get(TipusEntrada.PREMSA));

        System.out.println("NO VALIDA: " + accessosPerTipus.get(null));
    }
}
