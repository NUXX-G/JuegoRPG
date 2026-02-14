package juegorpg.guardado;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import juegorpg.modelo.item.Inventario;
import juegorpg.modelo.personaje.Personaje;
import juegorpg.narrativa.ArbolNarrativo;

public class PartidaGuardada implements Serializable
{
    private String nombreJugador;
    private Personaje personaje;
    private ArbolNarrativo arbolNarrativo;
    private Inventario inventario;
    private String fechaGuardado;
    
    public PartidaGuardada(String nombreJugador, Personaje personaje, ArbolNarrativo arbolNarrativo, Inventario inventario)
    {
        this.nombreJugador = nombreJugador;
        this.personaje = personaje;
        this.arbolNarrativo = arbolNarrativo;
        this.inventario = inventario;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        this.fechaGuardado = LocalDateTime.now().format(formatter);
    }

    public String getNombreJugador() 
    {
        return nombreJugador;
    }

    public Personaje getPersonaje() 
    {
        return personaje;
    }

    public ArbolNarrativo getArbolNarrativo() 
    {
        return arbolNarrativo;
    }

    public Inventario getInventario() 
    {
        return inventario;
    }

    public String getFechaGuardado() 
    {
        return fechaGuardado;
    }

    @Override
    public String toString() 
    {
        return "Partida de " + nombreJugador + " - Guardada: " + fechaGuardado;
    }
    
    
}
