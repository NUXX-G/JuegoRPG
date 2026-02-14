package juegorpg.modelo.item;

public class Arma extends Item
{
    private int bonusAtaque;
    
    public Arma(String nombre, String descripcion, int precio, int bonusAtaque) 
    {
        super(nombre, descripcion, precio);
        this.bonusAtaque = bonusAtaque;
    }

    @Override
    public String obtenerTipo() 
    {
        return "Arma";
    }

    public int getBonusAtaque() 
    {
        return bonusAtaque;
    }

    public void setBonusAtaque(int bonusAtaque) 
    {
        this.bonusAtaque = bonusAtaque;
    }
    
    @Override
    public String toString() {
        return super.toString() +  " | ATQ +" + bonusAtaque; 
    }
}
