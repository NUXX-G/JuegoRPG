package juegorpg.rng;

/**
 * Generador de numeros aleatorios del juego.
 * Centraliza toda la aleatoriedad en un solo sitio para que sea
 * facil cambiar la implementacion si hace falta (ej: usar Random en vez de Math.random).
 * Los metodos son estaticos, no hace falta instanciar esta clase.
 *
 * @author Nelson Filipe Fardilha Karlsson
 * @version 1.0
 */
public class GeneradorRNG 
{

    /**
     * Genera un entero aleatorio entre min y max, ambos incluidos.
     * Formula: (random * (max - min + 1)) + min.
     *
     * @param min valor minimo posible (incluido)
     * @param max valor maximo posible (incluido)
     * @return entero aleatorio en el rango [min, max]
     */
    public static int entero(int min, int max) 
    {
        return (int)(Math.random() * (max - min + 1)) + min;
    }

    /**
     * Comprueba si ocurre un evento con una probabilidad dada en porcentaje.
     * Por ejemplo, probabilidad(30) devuelve true el 30% de las veces.
     *
     * @param porcentaje probabilidad del 1 al 100
     * @return true si el evento ocurre segun la probabilidad
     */
    public static boolean probabilidad(int porcentaje) 
    {
        return entero(1, 100) <= porcentaje;
    }

    /**
     * Aplica la mecanica de critico fisico al danio dado.
     * Hay un 20% de probabilidad de que el danio se duplique.
     * Se usa en algunos calculos de combate.
     *
     * @param danioBase danio base antes de aplicar el posible critico
     * @return danio final, normal o duplicado segun el critico
     */
    public static int criticoFisico(int danioBase) 
    {
        if (probabilidad(20)) return danioBase * 2;
        return danioBase;
    }
}