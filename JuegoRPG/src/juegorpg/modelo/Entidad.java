package juegorpg.modelo;

import java.io.Serializable;

/**
 * Clase base abstracta para todo lo que puede luchar en el juego.
 * Tanto personajes como enemigos heredan de aqui, porque al final
 * todos tienen nombre, vida, ataque y defensa. Lo comun va aqui.
 *
 * @author Nelson Filipe Fardilha Karlsson
 * @version 1.0
 */
public abstract class Entidad implements Serializable 
{

    private String nombre;
    private int vidaMaxima;
    private int vidaActual;
    private int ataque;
    private int defensa;
    private int nivel;

    /**
     * Constructor base. Inicializa los stats de la entidad.
     * La vida actual empieza al maximo, como es logico.
     *
     * @param nombre     nombre que se muestra en combate
     * @param vidaMaxima vida maxima de la entidad
     * @param ataque     stat de ataque base
     * @param defensa    stat de defensa base
     * @param nivel      nivel inicial de la entidad
     */
    public Entidad(String nombre, int vidaMaxima, int ataque, int defensa, int nivel) 
    {
        this.nombre = nombre;
        this.vidaMaxima = vidaMaxima;
        this.ataque = ataque;
        this.defensa = defensa;
        this.nivel = nivel;
        vidaActual = vidaMaxima;
    }

    /**
     * Comprueba si la entidad sigue viva.
     * Si la vida es mayor que 0, sigue en pie.
     *
     * @return true si sigue viva, false si esta muerta
     */
    public boolean estaVivo() 
    {
        return vidaActual > 0;
    }

    /**
     * Aplica danio a la entidad restando la defensa.
     * El danio minimo siempre es 1, nunca se cura por recibir un golpe.
     * La vida tampoco baja de 0.
     *
     * @param danio danio bruto antes de restar la defensa
     */
    public void recibirDanio(int danio) 
    {
        int danioReal = danio - defensa;
        if (danioReal < 1) danioReal = 1;
        vidaActual -= danioReal;
        if (vidaActual < 0) vidaActual = 0;
    }

    /**
     * Cura la entidad sumando vida.
     * No puede pasar del maximo, si te curas de mas se queda en el tope.
     *
     * @param cantidad cantidad de vida a restaurar
     */
    public void curar(int cantidad) 
    {
        vidaActual += cantidad;
        if (vidaActual > vidaMaxima) vidaActual = vidaMaxima;
    }

    /**
     * Calcula el danio del ataque normal de esta entidad.
     * Cada subclase lo implementa a su manera segun su mecanica.
     *
     * @return danio calculado del ataque
     */
    public abstract int calcularDanioAtaque();

    /**
     * Devuelve una descripcion del personaje o enemigo para mostrar en pantalla.
     * Cada subclase pone su propio texto aqui.
     *
     * @return descripcion en texto
     */
    public abstract String obtenerDescripcion();

    /**
     * Muestra los stats basicos en formato de una sola linea.
     * Se usa en el log de combate y en el inventario.
     *
     * @return string con nombre, nivel, vida, ataque y defensa
     */
    @Override
    public String toString() 
    {
        return nombre + " | Nivel: " + nivel + " | Vida: " + vidaActual + "/" + vidaMaxima
                + " | ATQ: " + ataque + " | DEF: " + defensa;
    }

    // ── GETTERS Y SETTERS ────────────────────────────────────────────────────

    /** @return nombre de la entidad */
    public String getNombre() 
    { 
        return nombre; 
    }

    /** @param nombre nuevo nombre */
    public void setNombre(String nombre) 
    { 
        this.nombre = nombre; 
    }

    /** @return vida maxima */
    public int getVidaMaxima() 
    { 
        return vidaMaxima; 
    }

    /** @param vidaMaxima nueva vida maxima */
    public void setVidaMaxima(int vidaMaxima) 
    { 
        this.vidaMaxima = vidaMaxima; 
    }

    /** @return vida actual */
    public int getVidaActual() 
    { 
        return vidaActual; 
    }

    /** @param vidaActual nueva vida actual */
    public void setVidaActual(int vidaActual) 
    { 
        this.vidaActual = vidaActual; 
    }

    /** @return stat de ataque */
    public int getAtaque() 
    { 
        return ataque; 
    }

    /** @param ataque nuevo stat de ataque */
    public void setAtaque(int ataque) 
    { 
        this.ataque = ataque; 
    }

    /** @return stat de defensa */
    public int getDefensa() 
    { 
        return defensa; 
    }

    /** @param defensa nuevo stat de defensa */
    public void setDefensa(int defensa) 
    { 
        this.defensa = defensa; 
    }

    /** @return nivel actual */
    public int getNivel() 
    { 
        return nivel; 
    }

    /** @param nivel nuevo nivel */
    public void setNivel(int nivel) 
    { 
        this.nivel = nivel; 
    }
}