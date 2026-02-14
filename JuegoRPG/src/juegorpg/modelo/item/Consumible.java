package juegorpg.modelo.item;

public class Consumible extends Item 
{
    private int efectoVida;
    private int efectoMana;

    public Consumible(String nombre, String descripcion, int precio, int efectoVida, int efectoMana) 
    {
        super(nombre, descripcion, precio);
        this.efectoVida = efectoVida;
        this.efectoMana = efectoMana;
    }

    @Override
    public String obtenerTipo() 
    {
        return "Consumible";
    }

    public int getEfectoVida() 
    {
        return efectoVida;
    }

    public void setEfectoVida(int efectoVida) 
    {
        this.efectoVida = efectoVida;
    }

    public int getEfectoMana() 
    {
        return efectoMana;
    }

    public void setEfectoMana(int efectoMana) 
    {
        this.efectoMana = efectoMana;
    }
    
    @Override
    public String toString() 
    {
        String efecto = "";
        
        if (efectoVida > 0 || efectoMana > 0 ) 
        {
            efecto += " | Cura: " + efectoVida + " HP" + " | Restaura: " + efectoMana + " MP";
        }

        return super.toString() + efecto;
    }
}
