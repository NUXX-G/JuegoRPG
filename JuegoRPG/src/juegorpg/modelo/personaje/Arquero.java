package juegorpg.modelo.personaje;

import juegorpg.modelo.habilidad.HabilidadFisica;

/**
 * El Arquero. Stats medios pero con probabilidad de critico alta.
 * Su mecanica es la precision: cada ataque normal tiene una probabilidad
 * (precision%) de hacer el doble de danio. Empieza en 70% y sube con el nivel.
 * Aprende Disparo Certero y Lluvia de Flechas.
 *
 * @author Nelson Filipe Fardilha Karlsson
 * @version 1.0
 */
public class Arquero extends Personaje 
{

    /** Porcentaje de probabilidad de golpe critico (x2 danio). Maximo 95%. */
    private int precision;

    /**
     * Crea un Arquero equilibrado: vida media (90), ataque decente (12),
     * defensa baja (5) y precision inicial del 70%.
     *
     * @param nombre nombre del heroe
     */
    public Arquero(String nombre) 
    {
        super(nombre, 90, 12, 5, 1);
        precision = 70;
        aprenderHabilidad(new HabilidadFisica("Disparo Certero", "Flecha con punteria perfecta", 8, 12, 1.8));
        aprenderHabilidad(new HabilidadFisica("Lluvia de Flechas", "Multiples disparos", 12, 18, 1.5));
    }

    /**
     * Al subir de nivel sube un poco de vida, ataque y defensa,
     * y la precision sube 5 puntos. Tope maximo de precision en 95%
     * para que siempre haya algo de margen de fallo.
     */
    @Override
    protected void alSubirNivel() 
    {
        setVidaMaxima(getVidaMaxima() + 10);
        setVidaActual(getVidaMaxima());
        setAtaque(getAtaque() + 2);
        setDefensa(getDefensa() + 1);
        precision += 5;
        if (precision > 95) precision = 95;
    }

    /**
     * Genera un numero aleatorio entre 1 y 100. Si cae dentro del rango
     * de precision, el ataque hace el doble de danio (critico).
     * Si no, ataca con el stat base. Pura suerte con probabilidad controlada.
     *
     * @return danio del ataque, normal o critico segun la precision
     */
    @Override
    public int calcularDanioAtaque() 
    {
        int numeroRandom = (int)(Math.random() * 100) + 1;
        int ataque = getAtaque();
        if (numeroRandom <= precision) ataque *= 2;
        return ataque;
    }

    /**
     * Descripcion corta del Arquero para la pantalla de seleccion.
     *
     * @return descripcion de la clase
     */
    @Override
    public String obtenerDescripcion() 
    {
        return "Arquero: alta probabilidad de golpe critico. Velocidad y precision.";
    }

    /**
     * Extiende el toString con el porcentaje de precision actual.
     *
     * @return stats completos incluyendo precision
     */
    @Override
    public String toString() 
    {
        return super.toString() + " | Precision: " + precision + "%";
    }

    /** @return porcentaje de precision actual */
    public int getPrecision() 
    { 
        return precision; 
    }

    /** @param precision nuevo porcentaje de precision */
    public void setPrecision(int precision) 
    { 
        this.precision = precision; 
    }
}