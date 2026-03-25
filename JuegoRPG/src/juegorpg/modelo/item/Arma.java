package juegorpg.modelo.item;

/**
 * Un arma del inventario. Da bonus de ataque al equiparla.
 * Se obtiene como drop aleatorio al derrotar enemigos.
 * El bonus de ataque escala con el nivel del enemigo que la suelta.
 *
 * @author Nelson Filipe Fardilha Karlsson
 * @version 1.0
 */
public class Arma extends Item 
{

    /** Bonus de ataque que aporta esta arma al equiparla. */
    private int bonusAtaque;

    /**
     * Crea un arma con su bonus de ataque.
     *
     * @param nombre      nombre del arma (ej: "Espada +6")
     * @param descripcion descripcion del arma
     * @param precio      precio en oro
     * @param bonusAtaque puntos de ataque extra que da
     */
    public Arma(String nombre, String descripcion, int precio, int bonusAtaque) 
    {
        super(nombre, descripcion, precio);
        this.bonusAtaque = bonusAtaque;
    }

    /**
     * Devuelve el tipo de este item.
     *
     * @return "Arma"
     */
    @Override
    public String obtenerTipo() 
    { 
        return "Arma"; 
    }

    /**
     * Extiende el toString con el bonus de ataque que da.
     *
     * @return string con toda la info del arma
     */
    @Override
    public String toString() 
    {
        return super.toString() + " | ATQ +" + bonusAtaque;
    }

    /** @return bonus de ataque del arma */
    public int getBonusAtaque() 
    { 
        return bonusAtaque; 
    }

    /** @param bonusAtaque nuevo bonus de ataque */
    public void setBonusAtaque(int bonusAtaque) 
    { 
        this.bonusAtaque = bonusAtaque; 
    }
}