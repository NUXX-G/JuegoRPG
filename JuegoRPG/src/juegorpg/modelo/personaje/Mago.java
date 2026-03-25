package juegorpg.modelo.personaje;

import juegorpg.modelo.habilidad.HabilidadMagica;

/**
 * El Mago. Poca vida, mucho danio.
 * Su mecanica es el mana: si tiene 20 o mas de mana, el ataque normal
 * hace x3 de danio y gasta 20 de mana. Sin mana, ataca flojo.
 * Hay que gestionar bien el mana para no quedarse vendido.
 *
 * @author Nelson Filipe Fardilha Karlsson
 * @version 1.0
 */
public class Mago extends Personaje 
{

    /** Mana maximo. Sube al subir de nivel. */
    private int manaMaximo;

    /** Mana actual disponible para lanzar hechizos. */
    private int manaActual;

    /**
     * Crea un Mago fragil pero letal: poca vida (70) y defensa baja (3),
     * pero empieza con 100 de mana y aprende Bola de Fuego y Rayo Arcano.
     *
     * @param nombre nombre del heroe
     */
    public Mago(String nombre) 
    {
        super(nombre, 70, 8, 3, 1);
        this.manaMaximo = 100;
        this.manaActual = manaMaximo;
        aprenderHabilidad(new HabilidadMagica("Bola de Fuego", "Lanza una esfera ardiente", 20, 25, 30));
        aprenderHabilidad(new HabilidadMagica("Rayo Arcano", "Descarga magica concentrada", 15, 20, 20));
    }

    /**
     * Al subir de nivel sube un poco de todo, pero lo importante es
     * que el mana maximo sube 20 y se restaura entero. El Mago escala
     * principalmente en mana y ataque magico.
     */
    @Override
    protected void alSubirNivel() 
    {
        setVidaMaxima(getVidaMaxima() + 10);
        setVidaActual(getVidaMaxima());
        setAtaque(getAtaque() + 5);
        setDefensa(getDefensa() + 1);
        manaMaximo += 20;
        manaActual = manaMaximo;
    }

    /**
     * Si tiene mana suficiente (20 o mas), el ataque hace x3 de danio y gasta mana.
     * Si no tiene mana, ataca con el stat base sin bonus. Hay que gestionar bien
     * cuando gastar el mana y cuando no.
     *
     * @return danio calculado segun el mana disponible
     */
    @Override
    public int calcularDanioAtaque() 
    {
        int ataque = getAtaque();
        if (manaActual >= 20) 
        {
            ataque *= 3;
            manaActual -= 20;
        }
        return ataque;
    }

    /**
     * Descripcion corta del Mago para la pantalla de seleccion.
     *
     * @return descripcion de la clase
     */
    @Override
    public String obtenerDescripcion() 
    {
        return "Mago: danio magico devastador. Fragil pero letal con mana.";
    }

    /**
     * Extiende el toString con el mana actual y maximo.
     *
     * @return stats completos incluyendo mana
     */
    @Override
    public String toString() 
    {
        return super.toString() + " | Mana: " + manaActual + "/" + manaMaximo;
    }

    /** @return mana maximo */
    public int getManaMaximo() 
    { 
        return manaMaximo; 
    }

    /** @param manaMaximo nuevo mana maximo */
    public void setManaMaximo(int manaMaximo) 
    { 
        this.manaMaximo = manaMaximo; 
    }

    /** @return mana actual */
    public int getManaActual() 
    { 
        return manaActual; 
    }

    /** @param manaActual nuevo mana actual */
    public void setManaActual(int manaActual) 
    { 
        this.manaActual = manaActual; 
    }
}