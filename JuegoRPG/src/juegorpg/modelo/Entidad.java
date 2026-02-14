package juegorpg.modelo;
import java.io.Serializable;

public abstract class Entidad implements Serializable
{
    private String nombre;
    private int vidaMaxima;
    private int vidaActual;
    private int ataque;
    private int defensa;
    private int nivel;
    
    public Entidad(String nombre, int vidaMaxima, int ataque, int defensa, int nivel)
    {
        this.nombre = nombre;
        this.vidaMaxima = vidaMaxima;
        this.ataque = ataque;
        this.defensa = defensa;
        this.nivel = nivel;
        vidaActual = vidaMaxima;
    }
    
    public boolean estaVivo()
    {
        return vidaActual > 0;
    }
    
    public void recibirDanio(int danio)
    {
        int danioReal = danio - defensa;
        
        if (danioReal < 1) 
        {
            danioReal = 1;
        }
        
        vidaActual -= danioReal;
        
        if (vidaActual < 0)
        {
            vidaActual = 0;
        }
    }
    
    public void curar(int cantidad)
    {
        vidaActual += cantidad;
        
        if (vidaActual > vidaMaxima) 
        {
            vidaActual = vidaMaxima;
        }
    }

    @Override
    public String toString() 
    {
        return nombre + " | Nivel: " + nivel + " | Vida: " + vidaActual + "/" + vidaMaxima + " | ATQ: " + ataque + " | DEF: " + defensa;
    }
    
    public abstract int calcularDanioAtaque();
    public abstract String obtenerDescripcion();

    public String getNombre() 
    {
        return nombre;
    }

    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }

    public int getVidaMaxima() 
    {
        return vidaMaxima;
    }

    public void setVidaMaxima(int vidaMaxima) 
    {
        this.vidaMaxima = vidaMaxima;
    }

    public int getVidaActual() 
    {
        return vidaActual;
    }

    public void setVidaActual(int vidaActual) 
    {
        this.vidaActual = vidaActual;
    }

    public int getAtaque() 
    {
        return ataque;
    }

    public void setAtaque(int ataque) 
    {
        this.ataque = ataque;
    }

    public int getDefensa() 
    {
        return defensa;
    }

    public void setDefensa(int defensa) 
    {
        this.defensa = defensa;
    }

    public int getNivel() 
    {
        return nivel;
    }

    public void setNivel(int nivel) 
    {
        this.nivel = nivel;
    }
}
