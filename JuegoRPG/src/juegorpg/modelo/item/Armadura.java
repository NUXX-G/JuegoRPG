package juegorpg.modelo.item;

public class Armadura extends Item
{
     private int bonusDefensa;  

    public Armadura(String nombre, String descripcion, int precio, int bonusDefensa) 
    {
        super(nombre, descripcion, precio);
        this.bonusDefensa = bonusDefensa;
    }

    @Override
    public String obtenerTipo() 
    {
        return "Armadura";
    }

    public int getBonusDefensa() 
    {
        return bonusDefensa;
    }

    public void setBonusDefensa(int bonusDefensa) 
    {
        this.bonusDefensa = bonusDefensa;
    }
    
    @Override
    public String toString() 
    {
        return super.toString() + " | DEF +" + bonusDefensa;
    }
}
