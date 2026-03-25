package juegorpg.modelo.item;

/**
 * Una armadura del inventario. Da bonus de defensa al equiparla.
 * Se obtiene como drop aleatorio al derrotar enemigos,
 * igual que las armas pero escalando en defensa en vez de ataque.
 *
 * @author Nelson Filipe Fardilha Karlsson
 * @version 1.0
 */
public class Armadura extends Item 
{

    /** Bonus de defensa que aporta esta armadura al equiparla. */
    private int bonusDefensa;

    /**
     * Crea una armadura con su bonus de defensa.
     *
     * @param nombre      nombre de la armadura (ej: "Peto +3")
     * @param descripcion descripcion de la armadura
     * @param precio      precio en oro
     * @param bonusDefensa puntos de defensa extra que da
     */
    public Armadura(String nombre, String descripcion, int precio, int bonusDefensa) 
    {
        super(nombre, descripcion, precio);
        this.bonusDefensa = bonusDefensa;
    }

    /**
     * Devuelve el tipo de este item.
     *
     * @return "Armadura"
     */
    @Override
    public String obtenerTipo() 
    { 
        return "Armadura"; 
    }

    /**
     * Extiende el toString con el bonus de defensa que da.
     *
     * @return string con toda la info de la armadura
     */
    @Override
    public String toString() 
    {
        return super.toString() + " | DEF +" + bonusDefensa;
    }

    /** @return bonus de defensa de la armadura */
    public int getBonusDefensa() 
    { 
        return bonusDefensa; 
    }

    /** @param bonusDefensa nuevo bonus de defensa */
    public void setBonusDefensa(int bonusDefensa) 
    { 
        this.bonusDefensa = bonusDefensa; 
    }
}