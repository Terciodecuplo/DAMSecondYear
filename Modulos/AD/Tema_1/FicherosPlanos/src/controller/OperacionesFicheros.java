package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OperacionesFicheros {

    public void escribirFichero(String nombre){
        File file = new File("/Users/josemurciabelmonte/Documents/DAM/SEGUNDO CURSO/Modulos/AAD/Tema_1/FicherosPlanos/src/ficheros" + nombre);
        FileWriter fileWriter = null;
        try{
            fileWriter = new FileWriter(file, false);
        }catch (IOException e){
            System.out.println("Fallo a la hora de escribir el fichero");
        } finally{
            try {
                fileWriter.close();
            } catch (IOException |NullPointerException e){
                throw new RuntimeException(e);
            }
        }
    }
}
