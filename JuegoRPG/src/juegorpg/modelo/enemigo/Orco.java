package juegorpg.modelo.enemigo;

/**
 * El Orco. Enemigo de dificultad media, lento pero muy duro.
 * Tiene el doble de vida y mas ataque que el Goblin del mismo nivel.
 * Su ataque es predecible (siempre hace el mismo danio), pero como es
 * tan alto duele igual. Dificultad 2.
 *
 * @author Nelson Filipe Fardilha Karlsson
 * @version 1.0
 */
public class Orco extends Enemigo 
{

    /**
     * Crea un Orco escalado al nivel indicado.
     * Mucho mas vida y ataque que el Goblin, pero sin mecanicas especiales.
     *
     * @param nivel nivel del orco
     */
    public Orco(int nivel) 
    {
        super("Orco", nivel * 40, nivel * 7, nivel * 3, nivel, nivel * 100, nivel * 12, 2);
    }

    /**
     * Descripcion del Orco.
     *
     * @return descripcion del enemigo
     */
    @Override
    public String obtenerDescripcion() 
    {
        return "Orco: enemigo lento pero devastador.";
    }

    /**
     * El Orco siempre hace el mismo danio, sin aleatoriedad.
     * Predecible, pero duele. Hay que tener buena defensa.
     *
     * @return danio fijo del ataque base
     */
    @Override
    public int calcularDanioAtaque() 
    {
        return getAtaque();
    }
}