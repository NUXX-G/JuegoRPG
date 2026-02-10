package juegorpg.modelo.personaje;

import juegorpg.modelo.Entidad;

public abstract class Personaje extends Entidad
{
    private int experiencia;
    private int experienciaSiguienteNivel;
    private int oro;
    
    public Personaje(String nombre, int vidaMaxima, int ataque, int defensa, int nivel) 
    {
        super(nombre, vidaMaxima, ataque, defensa, nivel);
        experiencia = 0;
        experienciaSiguienteNivel = 100;
        oro = 10;
    }
    
    public void ganarExperiencia(int cantidad)
    {
        experiencia += cantidad;
        
        if (experiencia >= experienciaSiguienteNivel) 
        {
            subirNivel();
        }
    }
    
    public void subirNivel()
    {
        setNivel(getNivel() + 1);
        experiencia = 0;
        experienciaSiguienteNivel = (int) (experienciaSiguienteNivel * 1.5);
        alSubirNivel();
    }  
    
    protected abstract void alSubirNivel(); 

    public int getExperiencia() 
    {
        return experiencia;
    }

    public void setExperiencia(int experiencia) 
    {
        this.experiencia = experiencia;
    }

    public int getExperienciaSiguienteNivel() 
    {
        return experienciaSiguienteNivel;
    }

    public void setExperienciaSiguienteNivel(int experienciaSiguienteNivel) 
    {
        this.experienciaSiguienteNivel = experienciaSiguienteNivel;
    }

    public int getOro() 
    {
        return oro;
    }

    public void setOro(int oro) 
    {
        this.oro = oro;
    }

    @Override
    public String toString() 
    {
        return super.toString() + " | EXP: " + experiencia + "/" + experienciaSiguienteNivel + " | ORO: " + oro; 
    }
}
