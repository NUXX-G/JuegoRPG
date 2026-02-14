package juegorpg.modelo.habilidad;

public class HabilidadMagica extends Habilidad
{
    private int bonusMagico;
    
    public HabilidadMagica(String nombre, String descripcion, int danioBase, int costoMana, int bonusMagico) 
    {
        super(nombre, descripcion, danioBase, costoMana);
        this.bonusMagico = bonusMagico;
    }

    @Override
    public int calcularDanio(int ataqueUsuario) 
    {
        return getDanioBase() + bonusMagico;
    }

    public int getBonusMagico() 
    {
        return bonusMagico;
    }

    public void setBonusMagico(int bonusMagico) 
    {
        this.bonusMagico = bonusMagico;
    }

    @Override
    public String toString() 
    {
        return super.toString() + " | Tipo: Magica | Bonus magico: " + bonusMagico; 
    }
}
