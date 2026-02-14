package juegorpg.rng;

public class GeneradorRNG 
{
    public static int entero(int min, int max)
    {
        return (int)(Math.random() * (max - min + 1)) + min;
    }
    
    public static boolean probabilidad(int porcentaje)
    {
        return entero(1, 100) <= porcentaje;
    }
    
    public static int criticoFisico(int danioBase)
    {
        int danioTotal = danioBase;
        
        if (probabilidad(20)) 
        {
            danioTotal *= 2;
        }
    return danioTotal;
    }
}
