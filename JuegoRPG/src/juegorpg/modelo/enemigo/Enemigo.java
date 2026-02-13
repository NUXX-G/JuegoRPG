package juegorpg.modelo.enemigo;

import juegorpg.modelo.Entidad;

public abstract class Enemigo extends Entidad
{
    private int experienciaAlMorir;
    private int oroAlMorir;
    private int dificultad;

    public Enemigo(String nombre, int vidaMaxima, int ataque, int defensa, int nivel, int experienciaAlMorir, int oroAlMorir, int dificultad) 
    {
        super(nombre, vidaMaxima, ataque, defensa, nivel);
        this.experienciaAlMorir = experienciaAlMorir;
        this.oroAlMorir = oroAlMorir;
        this.dificultad = dificultad;
    }
    

    public abstract String obtenerDescripcion();
    public abstract int calcularDanioAtaque();

    public int getExperienciaAlMorir() 
    {
        return experienciaAlMorir;
    }

    public int getOroAlMorir() 
    {
        return oroAlMorir;
    }

    public int getDificultad() 
    {
        return dificultad;
    }

    public void setExperienciaAlMorir(int experienciaAlMorir) 
    {
        this.experienciaAlMorir = experienciaAlMorir;
    }

    public void setOroAlMorir(int oroAlMorir) 
    {
        this.oroAlMorir = oroAlMorir;
    }

    public void setDificultad(int dificultad) 
    {
        this.dificultad = dificultad;
    }
    
    @Override
    public String toString() 
    {
        return super.toString() + " | EXP: " + experienciaAlMorir + " | ORO: " + oroAlMorir + " | Dificultad: " + dificultad;
    }
}
