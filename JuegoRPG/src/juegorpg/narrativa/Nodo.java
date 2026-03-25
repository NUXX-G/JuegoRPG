package juegorpg.narrativa;

import java.util.ArrayList;
import juegorpg.modelo.enemigo.Enemigo;
import java.io.Serializable;

/**
 * Un nodo del arbol narrativo. Representa una ubicacion o situacion
 * del juego: puede ser un cruce, un combate, el final de la historia, etc.
 * Cada nodo tiene un id unico, una descripcion que se muestra al jugador,
 * una lista de opciones hacia donde ir, y opcionalmente un enemigo.
 *
 * @author Nelson Filipe Fardilha Karlsson
 * @version 1.0
 */
public class Nodo implements Serializable 
{

    /** Identificador unico del nodo (ej: "bosque", "cueva", "final_victoria"). */
    private String id;

    /** Texto narrativo que se muestra al jugador al llegar a este nodo. */
    private String descripcion;

    /** Opciones disponibles desde este nodo para avanzar a otros. */
    private ArrayList<Opcion> opciones;

    /**
     * Enemigo que bloquea este nodo. Si hay enemigo, hay combate antes
     * de poder ver las opciones. Se pone a null al derrotarlo.
     */
    private Enemigo enemigo;

    /**
     * Indica si este nodo es un final de la historia.
     * Si es true, el juego termina al llegar aqui (victoria o huida).
     */
    private boolean esNodoFinal;

    /**
     * Crea un nodo con su id y su descripcion narrativa.
     * Empieza sin opciones, sin enemigo y sin ser nodo final.
     *
     * @param id          identificador unico del nodo
     * @param descripcion texto que se muestra al jugador al llegar
     */
    public Nodo(String id, String descripcion) 
    {
        this.id = id;
        this.descripcion = descripcion;
        opciones = new ArrayList<>();
        enemigo = null;
        esNodoFinal = false;
    }

    /**
     * Anade una opcion de decision a este nodo.
     * Se llama desde ArbolNarrativo al construir la historia.
     *
     * @param opcion opcion con el texto y el nodo al que lleva
     */
    public void agregarOpcion(Opcion opcion) 
    {
        opciones.add(opcion);
    }

    /**
     * Comprueba si este nodo tiene un enemigo activo (no derrotado).
     * Si tiene enemigo, hay combate antes de mostrar las opciones.
     *
     * @return true si hay un enemigo vivo en este nodo
     */
    public boolean tieneCombate() 
    {
        return enemigo != null;
    }

    /** @return id unico del nodo */
    public String getId() 
    { 
        return id; 
    }

    /** @return descripcion narrativa del nodo */
    public String getDescripcion() 
    { 
        return descripcion; 
    }

    /** @return lista de opciones disponibles desde este nodo */
    public ArrayList<Opcion> getOpciones() 
    { 
        return opciones; 
    }

    /** @return enemigo del nodo, o null si no hay o ya fue derrotado */
    public Enemigo getEnemigo() 
    { 
        return enemigo; 
    }

    /** @param enemigo enemigo a asignar a este nodo (null para quitarlo) */
    public void setEnemigo(Enemigo enemigo) 
    { 
        this.enemigo = enemigo; 
    }

    /** @return true si este nodo es un final de la historia */
    public boolean isEsNodoFinal() 
    { 
        return esNodoFinal; 
    }

    /** @param esNodoFinal true para marcar este nodo como final */
    public void setEsNodoFinal(boolean esNodoFinal) 
    { 
        this.esNodoFinal = esNodoFinal; 
    }
}