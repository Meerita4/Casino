package casino;

import java.util.Scanner;

public class menu {
    public static void ini() {
        elegirJuego(pedirOpcion());
    }
    public static int pedirOpcion() {
        Scanner sc = new Scanner(System.in);
        System.out.println("¿A que quieres jugar?");
        System.out.println("1. Ruleta");
        System.out.println("2. Loteria");
        System.out.println("3. Ruleta multiapuesta");
        System.out.println("4. Salir");
        int resp = sc.nextInt();
        while (resp <1 || resp >4) {
            System.out.println("¿A que quieres jugar?");
            System.out.println("1. Ruleta");
            System.out.println("2. Loteria");
            System.out.println("3. Ruleta multiapuesta (Fuera de servicio)");
            System.out.println("4. Salir");
             resp = sc.nextInt();
        }
        return resp;
    }
    public static int elegirJuego(int resp) {
        if (resp == 1) {
            ruleta.ruleta();
        } else if (resp == 2) {
            loteria.loteria();
        } else if (resp == 3) {
            ruletaMultiapuesta.ruletaMulti();
        } else if(resp == 4) {
            System.out.println("Has decidido irte, te echaremos de menos ludopata!!");
            return 4;

        }
        return resp;
    }
}
