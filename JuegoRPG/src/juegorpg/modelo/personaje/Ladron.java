package juegorpg.modelo.personaje;

import juegorpg.modelo.habilidad.HabilidadFisica;

public class Ladron extends Personaje
{
    private int esquiva;
    
    public Ladron(String nombre)
    {
        super(nombre, 80, 10, 4, 1);
        esquiva = 30;
        aprenderHabilidad(new HabilidadFisica("Apuñalar", "Ataque rápido por la espalda", 15, 10, 2.5));
        aprenderHabilidad(new HabilidadFisica("Golpe Silencioso", "Ataque sigiloso", 10, 8, 2.0));
    }

    
    @Override
    public void recibirDanio(int danio)
    {
        int numeroRandom = (int)(Math.random() * 100) + 1;
        
        if (numeroRandom <= esquiva) 
        {
            System.out.println(getNombre() + " esquivo el ataque!!");
        }
        else
        {
            super.recibirDanio(danio);
        }
    }
    
    @Override
    protected void alSubirNivel() 
    {
        setVidaMaxima(getVidaMaxima() + 8);
        setVidaActual(getVidaMaxima());
        setAtaque(getAtaque() + 2);
        setDefensa(getDefensa() + 1);
        esquiva += 3;
    
        if (esquiva > 60) 
        {
            esquiva = 60;
        }
    }

    @Override
    public int calcularDanioAtaque() 
    {
        return getAtaque();
    }

    @Override
    public String obtenerDescripcion() 
    {
        return "Ladron: mestro de la esquiva. Agil y escurridizo.";
    }

    public int getEsquiva() 
    {
        return esquiva;
    }

    public void setEsquiva(int esquiva) 
    {
        this.esquiva = esquiva;
    }
    
    @Override
    public String toString() 
    {
        return super.toString() + " | Esquiva: " + esquiva + "%";
    }
}
