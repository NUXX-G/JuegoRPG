package juegorpg.modelo.enemigo;

import juegorpg.modelo.Entidad;

public class Enemigo extends Entidad
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
    


    @Override
    public int calcularDanioAtaque() 
    {
    }

    @Override
    public String obtenerDescripcion() 
    {
    }
}
