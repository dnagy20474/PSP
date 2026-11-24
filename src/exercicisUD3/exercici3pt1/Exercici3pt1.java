package exercicisUD3.exercici3pt1;

import java.net.*;
import java.util.*;

public class Exercici3pt1 {

    static void main(String[] args) {
        getNetworkInterfaces();
    }

    static void getNetworkInterfaces() {

        try {
            Enumeration<NetworkInterface> interfaces =
                    NetworkInterface.getNetworkInterfaces();

            int total = 0;

            while (interfaces.hasMoreElements()) {

                NetworkInterface interfaz = interfaces.nextElement();

                total++;

                System.out.println("\n================================");
                System.out.println("INTERFAZ Nº " + total);
                System.out.println("================================");

                System.out.println("Nombre: " + interfaz.getName());
                System.out.println("Nombre descriptivo: " + interfaz.getDisplayName());
                System.out.println("Activa: " + interfaz.isUp());
                System.out.println("Loopback: " + interfaz.isLoopback());
                System.out.println("Virtual: " + interfaz.isVirtual());
                System.out.println("MTU: " + interfaz.getMTU());

                System.out.println("Direcciones IP:");

                Enumeration<InetAddress> direcciones =
                        interfaz.getInetAddresses();

                while (direcciones.hasMoreElements()) {
                    InetAddress direccion = direcciones.nextElement();

                    System.out.println("  - " + direccion.getHostAddress());
                }
            }

            System.out.println("\n================================");
            System.out.println("TOTAL DE INTERFACES: " + total);
            System.out.println("================================");

        } catch (SocketException se) {
            System.out.println("[ERROR]: " + se.getMessage());
        }
    }
}