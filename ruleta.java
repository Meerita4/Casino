package casino;

import java.util.Arrays;
import java.util.Scanner;

public class ruleta {
    public static void ruleta() {
        boolean juego = true;
        int dineroInicial;
        int tipoApuesta;
        int apuestaDelTipo;
        dineroInicial = dineroInicial();
        int dineroJuego = dineroInicial;
        int dineroApostar;
        int random;
        int ganarPerder;
        int averiguarParImpar;
        int seleccion = 0;
        int apuesta;
        while (juego) {
            tipoApuesta = tipoApuesta();
            apuestaDelTipo = apuestaDelTipo(tipoApuesta);
            dineroApostar = dineroApostar(dineroInicial);
            random = generarRandom();
            ganarPerder = ganado(random, seleccion, apuestaDelTipo, tipoApuesta);
            dineroJuego = actualizarDinero(dineroJuego, dineroApostar, ganarPerder, tipoApuesta);
            juego = continuar(dineroJuego);
        }
    }

    public static int dineroInicial() {
        System.out.println("BIENVEIDO A LA RULETA");
        Scanner sc = new Scanner(System.in);
        System.out.println("Mete el dinero inicial para jugar");
        int respuesta = sc.nextInt();
        //Filtro para que el dinero no sea menor o igual a 0.
        while (respuesta <= 0) {
            System.out.println("Mete el dinero inicial para jugar");
            respuesta = sc.nextInt();
        }
        return respuesta;

    }

    //Elegir que tipo de apuesta vamos a hacer
    public static int tipoApuesta() {
        Scanner sc = new Scanner(System.in);
        int resp;
        do {
            System.out.println("¿A qué vas a jugar?");
            System.out.println("1. Colores");
            System.out.println("2. Pares/Impares");
            System.out.println("3. Numeros");
            System.out.println("4. Filas");
            resp = sc.nextInt();
            //Filtro para que no pueda meter un numero menor a 1 y mayor a 4.
        } while (resp < 1 || resp > 4);
        return resp;
    }

    //En esta variable llamada eleccion se guarda la respuesta de lo que quiere usar el usuario
    public static int apuestaDelTipo(int tipoApuesta) {
        int eleccion = 0;//corregir luego cuando tenga numeros y filas
        if (tipoApuesta == 1) {
            eleccion = elegirColor();

        } else if (tipoApuesta == 2) {
            eleccion = paresImpares();

        } else if (tipoApuesta == 3) {
            eleccion = elegirNumero();

        } else if (tipoApuesta == 4) {
            eleccion = elegirFila();
        }
        return eleccion;
    }

    //Elegimos el numero al que vamos a apostar.
    public static int elegirNumero() {
        int numero;
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("Elige un numero entre el 0 y 36");
            numero = sc.nextInt();
            int comprobacion = 0;
        } while (numero < 0 || numero > 36);
        return numero;
    }

    // Funcion para seleccionar el color.
    public static int elegirColor() {
        Scanner sc = new Scanner(System.in);
        int respuesta;
        do {
            System.out.println("¿A que vas a apostar?");
            System.out.println("1. Rojos");
            System.out.println("2. Negros");
            respuesta = sc.nextInt();
            //Filtro para que no puedan poner una respuesta menor a 1 y mayor a 2.
        } while (respuesta < 1 || respuesta > 2);
        return respuesta;
    }

    public static int elegirFila() {
        Scanner sc = new Scanner(System.in);
        int respuesta;
        do {
            System.out.println("¿A que fila vas a jugar?");
            System.out.println("1. Primera Fila");
            System.out.println("2. Segunda Fila");
            System.out.println("3. Tercera Fila");
            respuesta = sc.nextInt();
        } while (respuesta < 1 || respuesta > 3);
        return respuesta;
    }

    public static int dineroApostar(int dineroInicial) {
        Scanner sc = new Scanner(System.in);
        System.out.println("¿Cuanto vas a apostar?");
        int respuesta = sc.nextInt();
        while (respuesta > dineroInicial || respuesta <= 0) {
            System.out.println("¿Cuanto vas a apostar?");
            respuesta = sc.nextInt();
        }
        return respuesta;
    }

    // Generamos el numero random;
    public static int generarRandom() {
        int numRandom = (int) (Math.random() * 37);
        System.out.println("Número generado es: " + numRandom);
        return numRandom;
    }

    public static int ganado(int random, int seleccion, int apuestaDelTipo, int tipoApuesta) {
        // Solo devuelve 1 o 2; 1 = ganar y 2 = perder.
        int sele = 0;
        if (tipoApuesta == 1) {
            sele = colores(random, apuestaDelTipo);
        } else if (tipoApuesta == 2) {
            sele = averiguarParImpar(random, apuestaDelTipo);

        } else if (tipoApuesta == 3) {
            sele = numeros(random, apuestaDelTipo);
        } else if (tipoApuesta == 4) {
            sele = filas(random, apuestaDelTipo);
        }
        return sele;
    }

    /// Comprobamos si ha ganado o perdido dependiendo si es rojo o negro y dentro del if llamamos a la funcion de actualizar dinero
    public static int colores(int random, int apuestaDelTipo) {
        int[] rojos = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};
        int[] negros = {2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35};
        // La comprobación es para saber si está en rojos o negros.
        int comprobacion = 0;
        int ganado = 0;
        for (int i = 0; i < rojos.length; i++) {
            // aqui comprobamos que el numero random esta en el array rojos.
            if (random == rojos[i]) {
                System.out.println("El numero es rojo");
                comprobacion = 1;
            }
        }
        for (int i = 0; i < negros.length; i++) {
            // aqui comprobamos que el número random esta en el array negros.
            if (random == negros[i]) {
                System.out.println("Es negro");
                comprobacion = 2;
            }
        }
        if (comprobacion == apuestaDelTipo) {
            ganado = 1;
            System.out.println("HAS GANADO");
        } else {
            ganado = 2;
            System.out.println("HAS PERDIDO, F");
        }

        return ganado;
    }

    /// Funcion para actualizar dinero
    public static int actualizarDinero(int dineroJuego, int dineroApostar, int ganarPerder, int tipoApuesta) {

        if (tipoApuesta == 1 || tipoApuesta == 2) {
            if (ganarPerder == 1) {
                dineroJuego = dineroJuego - dineroApostar;
                dineroJuego += dineroApostar * 2;
                System.out.println("Tu dinero es de:" + dineroJuego);
                return dineroJuego;
            } else {
                dineroJuego = dineroJuego - dineroApostar;
                System.out.println("Tu dinero es de:" + dineroJuego);
                return dineroJuego;
            }
        } else if (tipoApuesta == 3) {
            if (ganarPerder == 1) {
                dineroJuego = dineroJuego - dineroApostar;
                dineroJuego += dineroApostar * 36;
                System.out.println("Tu dinero es de:" + dineroJuego);
                return dineroJuego;
            } else {
                dineroJuego = dineroJuego - dineroApostar;
                System.out.println("Tu dinero es de:" + dineroJuego);
                return dineroJuego;
            }

        } else if (tipoApuesta == 4) {
            if (ganarPerder == 1) {
                dineroJuego = dineroJuego - dineroApostar;
                dineroJuego += dineroApostar * 3;
                System.out.println("Tu dinero es de:" + dineroJuego);
                return dineroJuego;
            } else {
                dineroJuego = dineroJuego - dineroApostar;
                System.out.println("Tu dinero es de:" + dineroJuego);
                return dineroJuego;
            }

        }


        return dineroJuego;

    }

    // Aqui preguntamos a que quiere apostar.
    public static int paresImpares() {
        int seleccion;
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("¿A qué vas a apostar?");
            System.out.println("1. Pares");
            System.out.println("2. Impares");
            seleccion = sc.nextInt();
        } while (seleccion < 1 || seleccion > 2);

        return seleccion;
    }

    //Funcion para averiguar si es par o impar
    public static int averiguarParImpar(int random, int apuestaDelTipo) {
        int[] pares = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36};
        int[] impares = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35};
        // La comprobación es para saber si está en pares o impares.
        int comprobacionPI = 0;
        int ganadoPI = 0;
        for (int i = 0; i < pares.length; i++) {
            // aqui comprobamos que el numero random esta en el array rojos.
            if (random == pares[i]) {
                System.out.println("El numero es par");
                comprobacionPI = 1;
            }
        }
        for (int i = 0; i < impares.length; i++) {
            // aqui comprobamos que el número random esta en el array negros.
            if (random == impares[i]) {
                System.out.println("Es impar");
                comprobacionPI = 2;
            }
        }
        // si la comparacionn es lo mismo que ha apostado el usuario, entonces ha ganado
        if (comprobacionPI == apuestaDelTipo) {
            ganadoPI = 1;
            System.out.println("HAS GANADO");
        } else {
            ganadoPI = 2;
            System.out.println("HAS PERDIDO, F");
        }

        return ganadoPI;
    }

    // Funcion para preguntar a que numero va a apostar

    public static int numeros(int random, int apuestaDelTipo) {

        int num = 0;

        if (apuestaDelTipo == random) {
            System.out.println("HAS GANADO!!!");
            num = 1;
        } else {
            System.out.println("HAS PERDIDO!!");
            num = 2;
        }
        return num;
    }

    public static int filas(int random, int apuestaDelTipo) {
        int[] primeraFila = {1, 4, 7, 10, 13, 16, 19, 22, 25, 28, 31, 34};
        int[] segundaFila = {2, 5, 8, 11, 14, 17, 20, 23, 26, 29, 32, 35};
        int[] terceraFila = {3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36};
        int respuesta = 0;
        int ganado = 0;

        for (int i = 0; i < primeraFila.length; i++) {
            if (random == primeraFila[i]) {
                System.out.println("El numero está en la primera fila");
                respuesta = 1;
            }
        }
        for (int i = 0; i < segundaFila.length; i++) {
            if (random == segundaFila[i]) {
                System.out.println("El numero está en la segunda fila");
                respuesta = 2;
            }
        }
        for (int i = 0; i < terceraFila.length; i++) {
            if (random == terceraFila[i]) {
                System.out.println("El numero está en la tercera fila");
                respuesta = 1;
            }
        }
        if (respuesta == apuestaDelTipo) {
            ganado = 1;
            System.out.println("HAS GANADO");
        } else {
            ganado = 2;
            System.out.println("HAS PERDIDO, F");
        }
        return ganado;

    }


    public static boolean continuar(int dineroJuego) {
        //Filtro para saber si el dinero es 0 o igual a 0 no puede jugar, se le echa del programa.
        if (dineroJuego <= 0) {
            System.out.println("Saldo insuficiente, A LA PUTA CALLE");
            return false;
        } else {
            Scanner sc = new Scanner(System.in);
            System.out.println("¿Desea continuar?");
            System.out.println("1.Si");
            System.out.println("2.No");
            int respuesta = sc.nextInt();

            //Filtro para si la respuesta es menor a 1 o mayor a 2, le vuelve a preguntar.
            while (respuesta < 1 || respuesta > 2) {
                System.out.println("¿Desea continuar?");
                System.out.println("1.Si");
                System.out.println("2.No");
                respuesta = sc.nextInt();
            }
            // ponemos esto para que si la respuesta es 1 me sigue el bucle, si no, se va del bucle.
            if (respuesta == 1) {
                return true;
            } else {
                return false;
            }
        }

    }

}


