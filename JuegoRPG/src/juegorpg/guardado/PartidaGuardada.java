package juegorpg.guardado;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import juegorpg.modelo.item.Inventario;
import juegorpg.modelo.personaje.Personaje;
import juegorpg.narrativa.ArbolNarrativo;

/**
 * Contenedor serializable con todos los datos de una partida guardada.
 * Guarda el personaje, el arbol narrativo (con el nodo actual), el inventario
 * y la fecha en que se guardo. Se serializa a disco a traves de GestorGuardado.
 *
 * @author Nelson Filipe Fardilha Karlsson
 * @version 1.0
 */
public class PartidaGuardada implements Serializable 
{

    private String nombreJugador;
    private Personaje personaje;
    private ArbolNarrativo arbolNarrativo;
    private Inventario inventario;

    /** Fecha y hora del guardado en formato dd/MM/yyyy HH:mm:ss. */
    private String fechaGuardado;

    /**
     * Crea una partida guardada con el estado actual del juego.
     * La fecha se genera automaticamente en el momento de crear el objeto.
     *
     * @param nombreJugador  nombre del jugador
     * @param personaje      personaje con todos sus stats y habilidades
     * @param arbolNarrativo arbol narrativo con el nodo actual ya guardado
     * @param inventario     inventario con todos los items actuales
     */
    public PartidaGuardada(String nombreJugador, Personaje personaje, ArbolNarrativo arbolNarrativo, Inventario inventario) 
    {
        this.nombreJugador = nombreJugador;
        this.personaje = personaje;
        this.arbolNarrativo = arbolNarrativo;
        this.inventario = inventario;
        this.fechaGuardado = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }

    /**
     * Muestra un resumen de la partida guardada con el nombre del jugador
     * y la fecha en que se guardo.
     *
     * @return string resumen de la partida
     */
    @Override
    public String toString() 
    {
        return "Partida de " + nombreJugador + " - Guardada: " + fechaGuardado;
    }

    /** @return nombre del jugador */
    public String getNombreJugador() 
    { 
        return nombreJugador; 
    }

    /** @return personaje guardado con todos sus stats */
    public Personaje getPersonaje() 
    { 
        return personaje; 
    }

    /** @return arbol narrativo con el nodo donde estaba el jugador */
    public ArbolNarrativo getArbolNarrativo() 
    { 
        return arbolNarrativo; 
    }

    /** @return inventario con todos los items guardados */
    public Inventario getInventario() 
    { 
        return inventario; 
    }

    /** @return fecha y hora del guardado */
    public String getFechaGuardado() 
    { 
        return fechaGuardado; 
    }
}