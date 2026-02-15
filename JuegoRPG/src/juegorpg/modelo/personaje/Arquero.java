package juegorpg.modelo.personaje;

import juegorpg.modelo.habilidad.HabilidadFisica;

public class Arquero extends Personaje
{
    private int precision;
    
    public Arquero(String nombre)
    {
        super(nombre, 90, 12, 5, 1);
        precision = 70;
        aprenderHabilidad(new HabilidadFisica("Disparo Certero", "Flecha con puntería perfecta", 8, 12, 1.8));
        aprenderHabilidad(new HabilidadFisica("Lluvia de Flechas", "Múltiples disparos", 12, 18, 1.5));
    }

    @Override
    protected void alSubirNivel() 
    {
        setVidaMaxima(getVidaMaxima() + 10);
        setVidaActual(getVidaMaxima());
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
