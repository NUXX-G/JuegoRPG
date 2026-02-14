package juegorpg.modelo.habilidad;

public class HabilidadFisica extends Habilidad
{
    private double multiplicadorAtaque;
    
    public HabilidadFisica(String nombre, String descripcion, int danioBase, int costoMana, double multiplicadorAtaque) 
    {
        super(nombre, descripcion, danioBase, costoMana);
        this.multiplicadorAtaque = multiplicadorAtaque;
    }

    @Override
    public int calcularDanio(int ataqueUsuario) 
    {
        return (int)(getDanioBase() + (ataqueUsuario * multiplicadorAtaque));
    }

    public double getMultiplicadorAtaque() 
    {
        return multiplicadorAtaque;
    }

    public void setMultiplicadorAtaque(double multiplicadorAtaque) 
    {
        this.multiplicadorAtaque = multiplicadorAtaque;
    }
    
    @Override
    public String toString() 
    {
        return super.toString() + " | Tipo: Fisica | Multiplicador ATQ: " + multiplicadorAtaque;
    }
}
