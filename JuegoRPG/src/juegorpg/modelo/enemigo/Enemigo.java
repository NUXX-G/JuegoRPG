package juegorpg.modelo.enemigo;

import juegorpg.modelo.Entidad;

/**
 * Clase base abstracta para todos los enemigos del juego.
 * Hereda de Entidad y le anade lo especifico de un enemigo:
 * la experiencia y el oro que da al morir, y su nivel de dificultad.
 * Goblin, Orco, Dragon y BossFinal heredan de aqui.
 *
 * @author Nelson Filipe Fardilha Karlsson
 * @version 1.0
 */
public abstract class Enemigo extends Entidad 
{

    /** Experiencia que gana el jugador al matar este enemigo. */
    private int experienciaAlMorir;

    /** Oro que suelta el enemigo al morir. */
    private int oroAlMorir;

    /**
     * Dificultad del enemigo en escala 1-3.
     * 1 = facil (Goblin), 2 = medio (Orco), 3 = dificil (Dragon, Boss).
     */
    private int dificultad;

    /**
     * Constructor base del enemigo. Pasa los stats generales a Entidad
     * y guarda los rewards de experiencia y oro.
     *
     * @param nombre              nombre del enemigo
     * @param vidaMaxima          vida maxima
     * @param ataque              stat de ataque
     * @param defensa             stat de defensa
     * @param nivel               nivel del enemigo
     * @param experienciaAlMorir  exp que da al morir
     * @param oroAlMorir          oro que da al morir
     * @param dificultad          nivel de dificultad (1-3)
     */
    public Enemigo(String nombre, int vidaMaxima, int ataque, int defensa, int nivel, int experienciaAlMorir, int oroAlMorir, int dificultad) 
    {
        super(nombre, vidaMaxima, ataque, defensa, nivel);
        this.experienciaAlMorir = experienciaAlMorir;
        this.oroAlMorir = oroAlMorir;
        this.dificultad = dificultad;
    }

    /**
     * Descripcion del enemigo. Cada subclase define la suya.
     *
     * @return descripcion en texto
     */
    public abstract String obtenerDescripcion();

    /**
     * Calcula el danio del ataque del enemigo.
     * Cada enemigo tiene su propia logica de combate.
     *
     * @return danio calculado
     */
    public abstract int calcularDanioAtaque();

    /**
     * Muestra los stats del enemigo incluyendo sus rewards.
     *
     * @return string con todos los datos del enemigo
     */
    @Override
    public String toString() 
    {
        return super.toString() + " | EXP: " + experienciaAlMorir
                + " | ORO: " + oroAlMorir + " | Dificultad: " + dificultad;
    }

    /** @return experiencia que da al morir */
    public int getExperienciaAlMorir() 
    { 
        return experienciaAlMorir; 
    }

    /** @return oro que suelta al morir */
    public int getOroAlMorir() 
    { 
        return oroAlMorir; 
    }

    /** @return nivel de dificultad (1-3) */
    public int getDificultad() 
    { 
        return dificultad; 
    }

    /** @param experienciaAlMorir nueva experiencia al morir */
    public void setExperienciaAlMorir(int experienciaAlMorir) 
    { 
        this.experienciaAlMorir = experienciaAlMorir; 
    }

    /** @param oroAlMorir nuevo oro al morir */
    public void setOroAlMorir(int oroAlMorir) 
    { 
        this.oroAlMorir = oroAlMorir; 
    }

    /** @param dificultad nueva dificultad */
    public void setDificultad(int dificultad) 
    { 
        this.dificultad = dificultad; 
    }
}