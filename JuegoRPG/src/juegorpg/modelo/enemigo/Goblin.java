package juegorpg.modelo.enemigo;

/**
 * El Goblin. El enemigo mas debil del juego, pero impredecible.
 * Sus stats escalan con el nivel que se le pasa al crearlo.
 * Tiene un 20% de probabilidad de hacer el doble de danio en cada ataque,
 * lo que le da ese punto de aleatoriedad que puede sorprender al jugador.
 *
 * @author Nelson Filipe Fardilha Karlsson
 * @version 1.0
 */
public class Goblin extends Enemigo 
{

    /**
     * Crea un Goblin escalado al nivel indicado.
     * Todos sus stats (vida, ataque, defensa, exp, oro) dependen del nivel.
     *
     * @param nivel nivel del goblin (viene del nodo narrativo donde aparece)
     */
    public Goblin(int nivel) 
    {
        super("Goblin", nivel * 20, nivel * 4, nivel * 1, nivel, nivel * 50, nivel * 5, 1);
    }

    /**
     * Descripcion del Goblin para mostrar en pantalla.
     *
     * @return descripcion del enemigo
     */
    @Override
    public String obtenerDescripcion() 
    {
        return "Goblin: enemigo debil pero impredecible.";
    }

    /**
     * El Goblin ataca con su stat base, pero tiene un 20% de hacer critico (x2).
     * Ese 20% lo convierte en un enemigo de bajo nivel pero no de ignorar.
     *
     * @return danio del ataque, con posible critico del 20%
     */
    @Override
    public int calcularDanioAtaque() 
    {
        int ataque = getAtaque();
        int probabilidadAzar = (int)(Math.random() * 101);
        if (probabilidadAzar <= 20) ataque *= 2;
        return ataque;
    }
}