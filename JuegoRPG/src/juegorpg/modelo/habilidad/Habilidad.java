package juegorpg.modelo.habilidad;
import java.io.Serializable;

public abstract class Habilidad implements Serializable
{
    private String nombre;
    private String descripcion;
    private int danioBase;
    private int costoMana;
    
    public Habilidad(String nombre, String descripcion, int danioBase, int costoMana)
    {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.danioBase = danioBase;
        this.costoMana = costoMana;
    }
    
    public abstract int calcularDanio(int ataqueUsuario);

    public String getNombre() 
    {
        return nombre;
    }

    public String getDescripcion() 
    {
        return descripcion;
    }

    public int getDanioBase() 
    {
        return danioBase;
    }

    public int getCostoMana() 
    {
        return costoMana;
    }

    public void setDanioBase(int danioBase) 
    {
        this.danioBase = danioBase;
    }

    public void setCostoMana(int costoMana) 
    {
        this.costoMana = costoMana;
    }

    @Override
    public String toString() 
    {
        return  nombre + "(Costo: " + costoMana + " MP) - " + descripcion + " | Daño base: " + danioBase;
    }
}
