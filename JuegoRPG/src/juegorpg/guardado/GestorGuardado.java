package juegorpg.guardado;

import java.io.*;

/**
 * Gestiona el guardado y la carga de partidas en disco.
 * Usa serializacion de Java para convertir el objeto PartidaGuardada
 * a un archivo .dat. Los metodos son estaticos, no hace falta instanciar esta clase.
 * El archivo se guarda en el directorio donde se ejecuta el juego.
 *
 * @author Nelson Filipe Fardilha Karlsson
 * @version 1.0
 */
public class GestorGuardado 
{

    /**
     * Guarda una partida en disco como archivo .dat.
     * Si ya existe un archivo con ese nombre, lo sobreescribe.
     * Si falla (permisos, disco lleno, etc.), imprime el error y devuelve false.
     *
     * @param partida        objeto con todos los datos de la partida a guardar
     * @param nombreArchivo  nombre del archivo sin extension (ej: "partida_guardada")
     * @return true si se guardo correctamente, false si hubo algun error
     */
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

    /**
     * Carga una partida desde disco.
     * Lee el archivo .dat y deserializa el objeto PartidaGuardada.
     * Devuelve null si el archivo no existe o hay algun error de lectura.
     *
     * @param nombreArchivo nombre del archivo sin extension (ej: "partida_guardada")
     * @return la partida cargada, o null si hubo algun error
     */
    public static PartidaGuardada cargarPartida(String nombreArchivo) 
    {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo + ".dat"))) 
        {
            return (PartidaGuardada) ois.readObject();
        } catch (IOException | ClassNotFoundException e) 
        {
            System.out.println("Error al cargar: " + e.getMessage());
            return null;
        }
    }

    /**
     * Comprueba si existe un archivo de partida guardada.
     * Se usa antes de intentar cargar para evitar errores.
     *
     * @param nombreArchivo nombre del archivo sin extension
     * @return true si el archivo existe en disco
     */
    public static boolean existePartida(String nombreArchivo) 
    {
        return new File(nombreArchivo + ".dat").exists();
    }
}