package juegorpg.modelo.personaje;

import juegorpg.modelo.habilidad.HabilidadMagica;

public class Mago extends Personaje
{
    private int manaMaximo;
    private int manaActual;
    
    public Mago(String nombre)
    {
        super(nombre, 70, 8, 3, 1);
        this.manaMaximo = 100;
        this.manaActual = manaMaximo;
        aprenderHabilidad(new HabilidadMagica("Bola de Fuego", "Lanza una esfera ardiente", 20, 25, 30));
        aprenderHabilidad(new HabilidadMagica("Rayo Arcano", "Descarga mágica concentrada", 15, 20, 20));
    }

    @Override
    protected void alSubirNivel() 
    {
        setVidaMaxima(getVidaMaxima() + 10);
        setVidaActual(getVidaMaxima());
        setAtaque(getAtaque() + 5);
        setDefensa(getDefensa() + 1);
        manaMaximo += 20;
        manaActual = manaMaximo;
    }

    @Override
    public int calcularDanioAtaque() 
    {
        int ataque = getAtaque();
        
        if (manaActual >= 20) 
        {
            ataque *= 3;
            manaActual -= 20;
        }
        
        return ataque;
    }

    @Override
    public String obtenerDescripcion() 
    {
        return "Mago: daño magico devastador. Fragil pero letal con mana";
    }

    public int getManaMaximo() 
    {
        return manaMaximo;
    }

    public void setManaMaximo(int manaMaximo) 
    {
        this.manaMaximo = manaMaximo;
    }

    public int getManaActual() 
    {
        return manaActual;
    }

    public void setManaActual(int manaActual) 
    {
        this.manaActual = manaActual;
    }

    @Override
    public String toString() 
    {
        return super.toString() + " | Mana: " + manaActual + "/" +manaMaximo; 
    }
    
}
