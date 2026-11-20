package repasUD2.exercici2pt3;

import java.util.*;

public class Festival {

    static void main(String[] args) {



    }

    public static class ControlAcces implements Runnable {

        private int portesEntrada = 10;


        HashMap<Object, Object> nombreAcces = new HashMap<>();
        HashMap<Object, Object> tipusEntrada = new HashMap<>();

        @Override
        public void run() {

            synchronized (nombreAcces) {

            } synchronized (tipusEntrada) {

            }
        }
    }
}