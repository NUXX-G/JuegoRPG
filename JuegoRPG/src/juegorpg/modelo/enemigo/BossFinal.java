package juegorpg.modelo.enemigo;

public class BossFinal extends Enemigo
{
    private int fase;

    public BossFinal(int nivel) 
    {
        super("Señor de la Oscuridad", nivel * 80, nivel * 15, nivel * 10, nivel, nivel * 500, nivel * 100, 3);
        this.fase = 1;
    }
    
    @Override
    public void recibirDanio(int danio)
    {
        super.recibirDanio(danio);
        
        if (fase == 1 && getVidaActual() <= getVidaMaxima() / 2) 
        {
            fase = 2;
            
            setAtaque(getAtaque() * 2);
            
            System.out.println("¡El Señor de la Oscuridad entra en FASE 2!");
        }
    }
    
    @Override
    public String obtenerDescripcion() 
    {
        return "Señor de la Oscuridad: el mal absoluto. Cambia de fase al 50% de vida.";
    }

    @Override
    public int calcularDanioAtaque() 
    {
        int ataque = getAtaque();
        
        if (fase == 2) 
        {
            ataque += (int)(Math.random() * 21);
        }
        
        return ataque;
    }
    
    @Override
    public String toString() {
        return super.toString() + " | Fase: " + fase; 
    }
}
