package juegorpg.guardado;

import java.io.*;

public class GestorGuardado 
{
    public static boolean guardarPartida(PartidaGuardada partida, String nombreArchivo)
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo + ".dat"))) 
        {
            oos.writeObject(partida);
            return true;
        } 
        catch (IOException e) 
        {
            System.out.println("Error al guardar: " + e.getMessage());
            return false;
        }
    }
    
    public static PartidaGuardada cargarPartida(String nombreArchivo)
    {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo + ".dat"))) 
        {
            return (PartidaGuardada) ois.readObject();
        } 
        catch (IOException | ClassNotFoundException e) 
        {
            System.out.println("Error al cargar: " + e.getMessage());
            return null;
        }
    }
    
    public static boolean existePartida(String nombreArchivo) 
    {
        File archivo = new File(nombreArchivo + ".dat");
        return archivo.exists();
    }
}
