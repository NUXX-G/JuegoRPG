package juegorpg.modelo.enemigo;

public class Dragon extends Enemigo
{
    private int turnosEnfurecido;
    
    public Dragon(int nivel) 
    {
        super("Dragon", nivel * 30, nivel * 12, nivel * 6, nivel, nivel * 200, nivel * 40, 3);
    }

    @Override
    public String obtenerDescripcion() 
    {
        return "Dragon: bestia ancient. Cada tres turnos desata su furia.";
    }

    @Override
    public int calcularDanioAtaque() 
    {
        turnosEnfurecido++;
        int ataque = getAtaque();
        
        if (turnosEnfurecido >= 3) 
        {
            ataque *= 3;
            
            turnosEnfurecido = 0;
        }
        
        return ataque;
    }

    public int getTurnosEnfurecido() 
    {
        return turnosEnfurecido;
    }

    public void setTurnosEnfurecido(int turnosEnfurecido) 
    {
        this.turnosEnfurecido = turnosEnfurecido;
    }
}
