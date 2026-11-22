package repasUD2.exercici2pt3;

import java.util.*;

public class Festival {

    static void main(String[] args) {



    }

    public static class ControlAcces {

        private int portesEntrada = 10;

        HashMap<Integer, Integer> accesPerPorta;
        HashMap<String, Integer> tipusEntrada;

        public ControlAcces() {
            accesPerPorta = new HashMap<>();
            tipusEntrada = new HashMap<>();
        }

        synchronized public void registrarAccesPorta(int puerta) {
            int contador = accesPerPorta.getOrDefault(puerta, 0);
            accesPerPorta.put(puerta, contador + 1);
        }

        synchronized public void registrarTipusEntrada(String tipus) {
            int contador = tipusEntrada.getOrDefault(tipus, 0);
            tipusEntrada.put(tipus, contador + 1);
        }

        synchronized public void mostrarAccesPorta() {
            for (Integer porta : accesPerPorta.keySet()) {
                System.out.println("Porta: " + porta + ": " + accesPerPorta.get(porta));
            }
        }

        synchronized public void mostrarTipusEntrada() {
            for (String tipus : tipusEntrada.keySet()) {
                System.out.println(tipus + ": " + tipusEntrada.get(tipus));
            }
        }
    }

    public class Porta extends Thread {
        private int numPorta;
        private ControlAcces control;

        public Porta(int numPorta, ControlAcces control) {
            this.numPorta = numPorta;
            this.control = control;
        }

        @Override
        public void run() {
            Random r = new  Random();

            int numAccesos = r.nextInt(101) + 100;
            int tipu = r.nextInt(4);
            
            String tipus;

            switch (tipu) {
                case 0 -> tipus = "GENERAL";
                case 1 -> tipus = "VIP";
            }

        }
    }
}