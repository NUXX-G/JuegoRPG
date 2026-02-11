package juegorpg.modelo.personaje;

public class Arquero extends Personaje
{
    private int precision;
    
    public Arquero(String nombre)
    {
        super(nombre, 90, 12, 5, 1);
        precision = 70;
    }

    @Override
    protected void alSubirNivel() 
    {
        setAtaque(getAtaque() + 2);
        setDefensa(getDefensa() + 1);
        
        precision += 5;
        
        if (precision > 95) 
        {
            precision = 95;
        }

    }

    @Override
    public int calcularDanioAtaque() 
    { 
        int numeroRandom = (int)(Math.random() * 100) + 1;
        int ataque = getAtaque();
        
        if (numeroRandom <= precision) 
        {
            ataque *= 2;
        }
        
        return ataque;
    }

    @Override
    public String obtenerDescripcion() 
    {
        return "Arquero: alta probabilidad de golpe critico. Velocidad y precision.";
    }
    
    @Override
    public String toString() 
    {
        return super.toString() + " | Precision: " + precision + "%"; 
    }

    public int getPrecision() 
    {
        return precision;
    }

    public void setPrecision(int precision) 
    {
        this.precision = precision;
    }
}
