package juegorpg.modelo.enemigo;

public class Orco extends Enemigo
{

    public Orco(int nivel) 
    {
        super("Orco", nivel * 40, nivel * 7, nivel * 3, nivel, nivel * 100, nivel * 12, 2);
    }

    @Override
    public String obtenerDescripcion() 
    {
        return "Orco: enemigo lento pero devastador.";
    }

    @Override
    public int calcularDanioAtaque() 
    {
        return getAtaque();
    }
    
}
