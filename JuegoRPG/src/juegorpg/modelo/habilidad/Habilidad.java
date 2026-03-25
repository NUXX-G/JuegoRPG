package juegorpg.modelo.habilidad;

import java.io.Serializable;

/**
 * Clase abstracta base para todas las habilidades del juego.
 * Una habilidad tiene nombre, descripcion, danio base y costo de mana.
 * Las subclases (HabilidadFisica y HabilidadMagica) implementan
 * como calculan su danio real segun el ataque del usuario.
 *
 * @author Nelson Filipe Fardilha Karlsson
 * @version 1.0
 */
public abstract class Habilidad implements Serializable 
{

    private String nombre;
    private String descripcion;
    private int danioBase;
    private int costoMana;

    /**
     * Constructor base de habilidad.
     *
     * @param nombre      nombre de la habilidad que aparece en pantalla
     * @param descripcion descripcion corta de lo que hace
     * @param danioBase   danio base antes de aplicar multiplicadores
     * @param costoMana   mana que cuesta usar la habilidad
     */
    public Habilidad(String nombre, String descripcion, int danioBase, int costoMana) 
    {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.danioBase = danioBase;
        this.costoMana = costoMana;
    }

    /**
     * Calcula el danio total de la habilidad segun el ataque del usuario.
     * Cada subclase lo implementa diferente: las fisicas usan multiplicador
     * de ataque, las magicas usan un bonus fijo magico.
     *
     * @param ataqueUsuario stat de ataque del personaje que usa la habilidad
     * @return danio total calculado
     */
    public abstract int calcularDanio(int ataqueUsuario);

    /**
     * Muestra la habilidad en formato legible para el log de combate.
     *
     * @return string con nombre, costo, descripcion y danio base
     */
    @Override
    public String toString() 
    {
        return nombre + " (Costo: " + costoMana + " MP) - " + descripcion + " | Danio base: " + danioBase;
    }

    /** @return nombre de la habilidad */
    public String getNombre() 
    { 
        return nombre; 
    }

    /** @return descripcion de la habilidad */
    public String getDescripcion() 
    { 
        return descripcion; 
    }

    /** @return danio base de la habilidad */
    public int getDanioBase() 
    { 
        return danioBase; 
    }

    /** @return costo en mana para usar esta habilidad */
    public int getCostoMana() 
    { 
        return costoMana; 
    }

    /** @param danioBase nuevo danio base */
    public void setDanioBase(int danioBase) 
    { 
        this.danioBase = danioBase; 
    }

    /** @param costoMana nuevo costo de mana */
    public void setCostoMana(int costoMana) 
    { 
        this.costoMana = costoMana; 
    }
}