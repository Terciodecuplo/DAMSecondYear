import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        int clientes;
        int cajaTotal;
        System.out.print("Número de clientes: ");
        clientes = scanner.nextInt();
        ArrayList<TiendaDeportes> tiendaDeportes = new ArrayList<>();
        for (int i = 0; i < clientes; i++) {
            tiendaDeportes.add(new TiendaDeportes(i));
            tiendaDeportes.get(i).start();
        }
        for (int i = 0; i < clientes; i++) {
            tiendaDeportes.get(i).join();
        }
        cajaTotal = TiendaDeportes.getDineroCajaUno() + TiendaDeportes.getDineroCajaDos() + TiendaDeportes.getDineroCajaTres() + TiendaDeportes.getDineroCajaTres();
        System.out.printf("La tienda se ha cerrado. La caja total del día es %d.\n", cajaTotal);
        System.out.printf("La CAJA UNO ha vendido %d €.\n", TiendaDeportes.getDineroCajaUno());
        System.out.printf("La CAJA DOS ha vendido %d €.\n", TiendaDeportes.getDineroCajaDos());
        System.out.printf("La CAJA TRES ha vendido %d €.\n", TiendaDeportes.getDineroCajaTres());
        System.out.printf("La CAJA CUATRO ha vendido %d €.\n", TiendaDeportes.getDineroCajaCuatro());

    }
}
