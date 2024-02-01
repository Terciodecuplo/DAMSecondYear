import java.util.concurrent.Semaphore;

public class TiendaDeportes extends Thread {
    private static Semaphore cajaUno = new Semaphore(1);
    private static int dineroCajaUno = 0;
    private static Semaphore cajaDos = new Semaphore(1);
    private static int dineroCajaDos = 0;

    private static Semaphore cajaTres = new Semaphore(1);
    private static int dineroCajaTres = 0;

    private static Semaphore cajaCuatro = new Semaphore(1);
    private static int dineroCajaCuatro = 0;

    private static Semaphore tienda = new Semaphore(4);
    private static int cajaTotal = 0;
    private int cliente;

    public TiendaDeportes(int cliente) {
        this.cliente = cliente;
    }

    public static int getDineroCajaUno() {
        return dineroCajaUno;
    }

    public static int getDineroCajaDos() {
        return dineroCajaDos;
    }

    public static int getDineroCajaTres() {
        return dineroCajaTres;
    }

    public static int getDineroCajaCuatro() {
        return dineroCajaCuatro;
    }

    public static int getCajaTotal() {
        return cajaTotal;
    }

    public void run() {
        try {
            tienda.acquire();
            System.out.printf("Entra en la tienda el cliente %d.\n", cliente);
            int cajaAsignada;
            cajaAsignada = (int) Math.floor(Math.random() * 4);
            switch (cajaAsignada) {
                case 0:
                    cajaUno.acquire();
                    System.out.printf("La CAJA UNO está ocupada con el cliente %d.\n", cliente);
                    Thread.sleep((long) (Math.random() * 2000) + 1000);
                    cajaUno.release();
                    System.out.printf("El cliente %d ha dejado la CAJA UNO.\n", cliente);
                    dineroCajaUno += (int) (Math.random() * 100) + 100;
                    break;
                case 1:
                    cajaDos.acquire();
                    System.out.printf("La CAJA DOS está ocupada con el cliente %d.\n", cliente);
                    Thread.sleep((long) (Math.random() * 2000) + 100);
                    cajaDos.release();
                    System.out.printf("El cliente %d ha dejado la CAJA DOS.\n", cliente);
                    dineroCajaDos += (int) (Math.random() * 100) + 100;
                    break;
                case 2:
                    cajaTres.acquire();
                    System.out.printf("La CAJA TRES está ocupada con el cliente %d.\n", cliente);
                    Thread.sleep((long) (Math.random() * 2000) + 100);
                    cajaTres.release();
                    dineroCajaTres += (int) (Math.random() * 100) + 100;
                    break;
                case 3:
                    cajaCuatro.acquire();
                    System.out.printf("La CAJA CUATRO está ocupada con el cliente %d.\n", cliente);
                    Thread.sleep((long) (Math.random() * 2000) + 100);
                    cajaCuatro.release();
                    dineroCajaCuatro += (int) (Math.random() * 100) + 100;
                    break;
            }

            tienda.release();
            System.out.printf("El cliente %d ha dejado la TIENDA.\n", cliente);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
