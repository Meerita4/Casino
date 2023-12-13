package casino;

import java.util.Scanner;
import java.util.Arrays;

public class loteria {
    public static void loteria() {
        int ticket;
        int numero;
        int random;


        ticket = tickets();
        numero = numeros();
        random = numRandom();
        int[][] boletos = boletos(ticket, numero);
        int[] ganador = ganador();
        double comprobacion = comprobacion(ganador, boletos, ticket);
        balance(ticket, comprobacion);
    }

    public static int tickets() {
        Scanner sc = new Scanner(System.in);
        System.out.println("¿Cuantos tickets vas a comprar? Cada ticket vale 5€");
        int tickets = sc.nextInt();
        while (tickets < 1) {
            System.out.println("¿Cuantos tickets vas a comprar? Cada ticket vale 5€");
            tickets = sc.nextInt();
        }
        return tickets;
    }

    public static int numeros() {
        Scanner sc = new Scanner(System.in);
        System.out.println("¿Quieres que los boletos se generen de manera aleatoria o manualmente?");
        System.out.println("1. Al azar");
        System.out.println("2. Manualmente");
        int numeros = sc.nextInt();
        while (numeros < 1 || numeros > 2) {
            System.out.println("¿Quieres que los boletos se generen de manera aleatoria o manualmente?");
            System.out.println("1. Al azar");
            System.out.println("2. Manualmente");
            numeros = sc.nextInt();
        }
        return numeros;
    }

    public static int numRandom() {
        int numeroRandom = (int) (Math.random() * 50 + 1);
        return numeroRandom;

    }

    public static int[][] boletos(int ticket, int numeros) {
        int[][] matriz;

        if (numeros == 1) {
            matriz = boletosAzar(ticket);

        } else {
            matriz = boletosManual(ticket);
        }
        return matriz;
    }


    public static int[][] boletosAzar(int ticket) {
        int[][] boletoAzar = new int[ticket][5];

        for (int i = 0; i < ticket; i++) {
            for (int j = 0; j < 5; j++) {
                int num = numRandom();
                boletoAzar[i][j] = num;
            }
        }
        for (int i = 0; i < ticket; i++) {
            System.out.println("Boleto " + (i + 1) + ": " + Arrays.toString(boletoAzar[i]));
        }
        return boletoAzar;
    }

    public static int[][] boletosManual(int ticket) {
        int[][] boletoManual = new int[ticket][5];

        for (int i = 0; i < ticket; i++) {
            System.out.println("Boleto " + (i + 1));

            for (int j = 0; j < 5; j++) {
                Scanner sc = new Scanner(System.in);
                System.out.println("Dime los numeros");
                int num = sc.nextInt();
                //Filtro para que el numero no sea menor a 1 y mayor a 49
                while (num < 1 || num > 49) {
                    System.out.println("Introduce el numero correcto");
                    num = sc.nextInt();
                }
                boletoManual[i][j] = num;
            }
        }
        for (int i = 0; i < ticket; i++) {
            System.out.println("Boleto " + (i + 1) + ": " + Arrays.toString(boletoManual[i]));
        }
        return boletoManual;
    }


    public static int[] ganador() {
        int[] ganador = new int[5];
        for (int i = 0; i < 5; i++) {
            int numeros = numRandom();
            ganador[i] = numeros;
        }
        System.out.println("EL BOLETO GANADOR ES: " + Arrays.toString(ganador));
        return ganador;
    }

    //Funcion para comprobar si ha ganado algo y cuanto ha ganado
    public static double comprobacion(int[] ganador, int[][] boletos, int ticket) {
        double premio1 = 50;
        double premio2 = 200;
        double premio3 = 20000;
        double premio4 = 100000;
        double premio5 = 1000000;
        double acumulacion = 0.0;

        for (int i = 0; i < ticket; ++i) {
            int aciertos = 0;

            for (int j = 0; j < 5; ++j) {
                int NumeroDeBoletos = boletos[i][j];
                int numeroDelGanador = ganador[j];
                if (NumeroDeBoletos == numeroDelGanador) {
                    aciertos++;
                }
            }

            double premiado = 0.0;
            if (aciertos == 1) {
                premiado = premio1;
            } else if (aciertos == 2) {
                premiado = premio2;
            } else if (aciertos == 3) {
                premiado = premio3;
            } else if (aciertos == 4) {
                premiado = premio4;
            } else if (aciertos == 5) {
                premiado = premio5;
            }
            acumulacion += premiado;
            System.out.println(" ");
            System.out.println("En el Boleto " + (i + 1) + " has acertado " + aciertos + " numeros");
            System.out.println("Tu premio es de: " + (i + 1) + ": " + premiado + " euros.");
            System.out.println(" ");
        }

        System.out.println("Tu total es de: " + acumulacion);

        return acumulacion;
    }

    public static void balance(int ticket, double comprobacion) {
        double dinero = comprobacion - (int) ticket * 5;
        System.out.println("Tu balance es de :" + dinero);
    }
}
