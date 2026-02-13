package juegorpg.modelo.enemigo;

public class Goblin extends Enemigo
{
    public Goblin(int nivel) 
    {
        super("Goblin", nivel * 20, nivel * 4, nivel * 1, nivel, nivel * 15, nivel * 5, 1);
    }

    @Override
    public String obtenerDescripcion() 
    {
        return "Goblin: enemigo debil pero impredecible.";
    }

    @Override
    public int calcularDanioAtaque() 
    {
        int ataque = getAtaque();
        int probabilidadAzar = (int)(Math.random() * 101);
        
        if ( probabilidadAzar <= 20) 
        {
            ataque *= 2;
        }
        
        return ataque;
    }
    
}
