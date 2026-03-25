package juegorpg.modelo.personaje;

import juegorpg.modelo.habilidad.HabilidadFisica;

/**
 * El Guerrero. La clase mas tanky del juego.
 * Tiene mucha vida y defensa, y su mecanica especial es la rabia:
 * cada vez que recibe danio, acumula rabia, y esa rabia potencia
 * su siguiente ataque. Al atacar gasta la mitad de la rabia acumulada.
 *
 * @author Nelson Filipe Fardilha Karlsson
 * @version 1.0
 */
public class Guerrero extends Personaje 
{

    /** Rabia acumulada. Sube al recibir golpes, potencia el ataque. Maximo 100. */
    private int rabia;

    /**
     * Crea un Guerrero con stats de tanque: mucha vida (120) y buena defensa (8).
     * Aprende dos habilidades fisicas de entrada: Golpe Devastador y Embestida.
     *
     * @param nombre nombre del heroe
     */
    public Guerrero(String nombre) 
    {
        super(nombre, 120, 15, 8, 1);
        aprenderHabilidad(new HabilidadFisica("Golpe Devastador", "Golpe brutal con arma", 10, 15, 2.0));
        aprenderHabilidad(new HabilidadFisica("Embestida", "Carga con el escudo", 5, 10, 1.5));
    }

    /**
     * Sobreescribe recibirDanio para acumular rabia al recibir golpes.
     * Cada golpe suma 10 de rabia, con tope en 100.
     * Cuanto mas te pegan, mas fuerte pegas despues.
     *
     * @param danio danio bruto recibido
     */
    @Override
    public void recibirDanio(int danio) 
    {
        super.recibirDanio(danio);
        rabia += 10;
        if (rabia > 100) rabia = 100;
    }

    /**
     * Mejoras al subir de nivel: mucha vida extra (+80), algo de ataque (+8)
     * y defensa (+4). El Guerrero escala principalmente en aguante.
     */
    @Override
    protected void alSubirNivel() 
    {
        setVidaMaxima(getVidaMaxima() + 80);
        setVidaActual(getVidaMaxima());
        setAtaque(getAtaque() + 8);
        setDefensa(getDefensa() + 4);
    }

    /**
     * Calcula el danio del ataque normal sumando la rabia acumulada (dividida entre 10).
     * Despues de atacar, la rabia se reduce a la mitad. Asi que conviene
     * acumularla antes de soltar el golpe gordo.
     *
     * @return danio del ataque con bonus de rabia
     */
    @Override
    public int calcularDanioAtaque() 
    {
        int ataqueReal = getAtaque() + (rabia / 10);
        rabia /= 2;
        return ataqueReal;
    }

    /**
     * Descripcion corta del Guerrero para la pantalla de seleccion.
     *
     * @return descripcion de la clase
     */
    @Override
    public String obtenerDescripcion() 
    {
        return "Guerrero: especialista en combate cuerpo a cuerpo. Alta vida y defensa.";
    }

    /**
     * Extiende el toString con la rabia actual.
     *
     * @return stats completos incluyendo rabia
     */
    @Override
    public String toString() 
    {
        return super.toString() + " | Rabia: " + rabia + "/100";
    }

    /** @return rabia acumulada actual */
    public int getRabia() 
    { 
        return rabia; 
    }

    /** @param rabia nueva rabia */
    public void setRabia(int rabia) 
    { 
        this.rabia = rabia; 
    }
}