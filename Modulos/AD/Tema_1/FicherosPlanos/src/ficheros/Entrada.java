package ficheros;

import controller.OperacionesFicheros;

public class Entrada {
    public static void main(String[] args) {
        OperacionesFicheros operacionesFicheros = new OperacionesFicheros();
        operacionesFicheros.escribirFichero("prueba_plana.txt");
    }
}
