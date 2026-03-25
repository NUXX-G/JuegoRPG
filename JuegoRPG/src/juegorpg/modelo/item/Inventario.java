package juegorpg.modelo.item;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * El inventario del jugador. Guarda hasta 20 items.
 * Tiene metodos para agregar, quitar y obtener items por indice.
 * Se serializa junto con la partida guardada para persistir el progreso.
 *
 * @author Nelson Filipe Fardilha Karlsson
 * @version 1.0
 */
public class Inventario implements Serializable 
{

    /** Capacidad maxima de slots del inventario. */
    private static final int CAPACIDAD_MAXIMA = 20;

    private ArrayList<Item> items;

    /**
     * Crea un inventario vacio listo para usarse.
     */
    public Inventario() 
    {
        this.items = new ArrayList<>();
    }

    /**
     * Intenta agregar un item al inventario.
     * Si ya esta lleno (20 items), no lo agrega y devuelve false.
     *
     * @param item item a agregar
     * @return true si se agrego, false si el inventario estaba lleno
     */
    public boolean agregarItem(Item item) 
    {
        if (items.size() < CAPACIDAD_MAXIMA) 
        {
            items.add(item);
            return true;
        }
        return false;
    }

    /**
     * Quita un item del inventario por su posicion.
     * Si el indice esta fuera de rango, no hace nada y devuelve false.
     *
     * @param indice posicion del item a quitar (empieza en 0)
     * @return true si se quito, false si el indice era invalido
     */
    public boolean quitarItem(int indice) 
    {
        if (indice >= 0 && indice < items.size()) 
        {
            items.remove(indice);
            return true;
        }
        return false;
    }

    /**
     * Devuelve el item en la posicion indicada sin quitarlo.
     * Devuelve null si el indice esta fuera de rango.
     *
     * @param indice posicion del item (empieza en 0)
     * @return el item o null si no existe en esa posicion
     */
    public Item obtenerItem(int indice) 
    {
        if (indice < 0 || indice >= items.size()) return null;
        return items.get(indice);
    }

    /**
     * Devuelve cuantos items hay actualmente en el inventario.
     *
     * @return numero de items guardados
     */
    public int getCantidadActual() 
    { 
        return items.size(); 
    }

    /**
     * Comprueba si el inventario esta lleno (20 items).
     *
     * @return true si esta al maximo de capacidad
     */
    public boolean estaLleno() 
    { 
        return items.size() >= CAPACIDAD_MAXIMA; 
    }

    /**
     * Comprueba si el inventario esta completamente vacio.
     *
     * @return true si no hay ningun item
     */
    public boolean estaVacio() 
    { 
        return items.isEmpty(); 
    }

    /**
     * Imprime el inventario completo por consola.
     * Se usa para depuracion; la vista tiene su propia representacion.
     */
    public void mostrarInventario() 
    {
        if (items.isEmpty()) 
        {
            System.out.println("El inventario esta vacio.");
            return;
        }
        System.out.println("=== INVENTARIO (" + items.size() + "/" + CAPACIDAD_MAXIMA + ") ====");
        for (int i = 0; i < items.size(); i++) 
        {
            System.out.println("[" + (i + 1) + "] " + items.get(i).toString());
        }
        System.out.println("===================");
    }
}