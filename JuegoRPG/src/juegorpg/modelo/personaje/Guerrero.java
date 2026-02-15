package juegorpg.modelo.personaje;

import juegorpg.modelo.habilidad.HabilidadFisica;

public class Guerrero extends Personaje
{
    private int rabia;
    
    public Guerrero(String nombre) 
    {
        super(nombre, 120, 15, 8, 1);
        aprenderHabilidad(new HabilidadFisica("Golpe Devastador", "Golpe brutal con arma", 10, 15, 2.0));
        aprenderHabilidad(new HabilidadFisica("Embestida", "Carga con el escudo", 5, 10, 1.5));
    }


    @Override
    public void recibirDanio(int danio)
    {
        super.recibirDanio(danio);
        rabia += 10;
        
        if (rabia > 100) 
        {
            rabia = 100;
        }
    }
    
    @Override
    protected void alSubirNivel() 
    {
        setVidaMaxima(getVidaMaxima() + 80);
        setVidaActual(getVidaMaxima());
        setAtaque(getAtaque() + 8);
        setDefensa(getDefensa() + 4);
    }
    
    @Override
    public int calcularDanioAtaque() 
    {
        int ataqueReal = getAtaque() + (rabia / 10);
        rabia /= 2;
        
        return ataqueReal;
    }

    @Override
    public String obtenerDescripcion() 
    {
        return "Guerrero: especialista en combate cuerpo a cuerpo. Alta vida y defensa.";
    }

    public int getRabia() 
    {
        return rabia;
    }

    public void setRabia(int rabia) 
    {
        this.rabia = rabia;
    }
    
    @Override
    public String toString() 
    {
        return super.toString() + " | Rabia: " + rabia + "/100";
    }
}
