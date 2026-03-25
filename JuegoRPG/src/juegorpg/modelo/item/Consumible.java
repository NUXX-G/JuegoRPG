package juegorpg.modelo.item;

/**
 * Un consumible del inventario. Restaura vida y/o mana al usarlo.
 * Se gasta al usar, desaparece del inventario.
 * El jugador empieza con 2 pociones de vida y 1 de mana.
 *
 * @author Nelson Filipe Fardilha Karlsson
 * @version 1.0
 */
public class Consumible extends Item 
{

    /** Cantidad de vida que restaura al usarlo. 0 si no cura vida. */
    private int efectoVida;

    /** Cantidad de mana que restaura al usarlo. 0 si no restaura mana. */
    private int efectoMana;

    /**
     * Crea un consumible con sus efectos de vida y mana.
     *
     * @param nombre      nombre del consumible (ej: "Pocion de Vida")
     * @param descripcion descripcion de lo que hace
     * @param precio      precio en oro
     * @param efectoVida  vida que restaura (puede ser 0)
     * @param efectoMana  mana que restaura (puede ser 0)
     */
    public Consumible(String nombre, String descripcion, int precio, int efectoVida, int efectoMana) 
    {
        super(nombre, descripcion, precio);
        this.efectoVida = efectoVida;
        this.efectoMana = efectoMana;
    }

    /**
     * Devuelve el tipo de este item.
     *
     * @return "Consumible"
     */
    @Override
    public String obtenerTipo() 
    { 
        return "Consumible"; 
    }

    /**
     * Muestra el consumible con sus efectos de curacion.
     * Solo muestra los efectos si son mayores que 0.
     *
     * @return string con toda la info del consumible
     */
    @Override
    public String toString() 
    {
        String efecto = "";
        if (efectoVida > 0 || efectoMana > 0) 
        {
            efecto += " | Cura: " + efectoVida + " HP | Restaura: " + efectoMana + " MP";
        }
        return super.toString() + efecto;
    }

    /** @return vida que restaura */
    public int getEfectoVida() 
    { 
        return efectoVida; 
    }

    /** @param efectoVida nueva cantidad de vida a restaurar */
    public void setEfectoVida(int efectoVida) 
    { 
        this.efectoVida = efectoVida; 
    }

    /** @return mana que restaura */
    public int getEfectoMana() 
    { 
        return efectoMana; 
    }

    /** @param efectoMana nueva cantidad de mana a restaurar */
    public void setEfectoMana(int efectoMana) 
    { 
        this.efectoMana = efectoMana; 
    }
}