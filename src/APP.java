 
import java.io.*;

import Model.Reserva;
 
public class APP {
 
    public static void main(String[] args) {
         
        // Creamos dos personas para agregar a un archivo con extensión ".dat"
        // es decir, escribiremos los datos en un archivo binario.
        Reserva reserva = new Reserva ();
         
        // Asignamos los valores que queramos a cada persona
        reserva.setDniPersona("12345678A");
        reserva.setSesionCodigo(1);
         
        // Creamos un objeto de tipo fila para asignarle un archivo
        File archivo = new File("escritura.dat");
         
        try {
            // Para poder escribir utilizaremos un FileOutputStream pasandole
            // como referencia el archivo de tipo File.
            FileOutputStream fos = new FileOutputStream(archivo);
             
            // Y crearemos también una instancia del tipo ObjectOutputStream
            // al que le pasaremos por parámetro
            // el objeto de tipo FileOutputStream
            ObjectOutputStream escribir = new ObjectOutputStream(fos);
             
            // Escribimos los objetos en el archivo.
            escribir.writeObject(reserva);
             
            // Cerramos los objetos para no consumir recursos.
            escribir.close();
            fos.close();
             
        } catch (Exception e) {
            System.out.println("Error al escribir en el archivo. "
                    + e.getMessage());   
        }
    }
}